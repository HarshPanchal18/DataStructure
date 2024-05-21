import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.stream.IntStream;

// https://medium.com/@rakesh.open.source/time-based-one-time-password-totp-java-implementation-82a472bd6bf9

public class TimeBasedOTP {

    /*
     * The Base32 encoding scheme, as defined in RFC 4648, intentionally omits
     * the numbers 0, 1, 8, and 9 to reduce the possibility of human
     * misinterpretation
     * and transcription errors.
     * Reference: https://datatracker.ietf.org/doc/html/rfc4648
     */
    private static final char[] BASE32_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();

    // ASCII character set defines 128 unique values
    private static final int[] BITS_LOOKUP = new int[128];

    private static final String HMAC_ALGO = "HmacSHA1";
    private static final int TOTP_LENGTH = 6; // length of TOTP will be 6 digits
    private static final int TIME_STEP = 30; // 30 seconds

    static {
        for (int i = 0; i < BASE32_CHARS.length; i++) {
            BITS_LOOKUP[BASE32_CHARS[i]] = i;
        }
    }

    public static String encodeBase32(String input) {
        StringBuilder encoded = new StringBuilder();
        int buffer = 0;
        int bufferLength = 0;

        for (byte b : input.getBytes()) {
            buffer <<= 8; // Shift the buffer 8 bits to the left
            buffer |= b & 0xFF; // Add the byte to the buffer
            bufferLength += 8; // Increase the buffer length by 8 bits

            /*
             * Base32 encoding uses a 5-bit group from the available data.
             * So, we check if we have at least 5 bits in our buffer.
             * We then extract the top 5 bits for Base32 encoding.
             */
            while (bufferLength >= 5) {
                int index = (buffer >> (bufferLength - 5)) & 0x1F;
                encoded.append(BASE32_CHARS[index]);
                bufferLength -= 5; // Decrease the buffer length by 5 bits
            }
        }

        /*
         * Padding is necessary for Base32 encoding to ensure the encoded output
         * is a multiple of 8 characters, making it easier to decode later.
         * The '=' character is used as a padding character.
         */
        while (encoded.length() % 8 != 0) {
            encoded.append('=');
        }

        return encoded.toString();
    }

    public static String decodeBase32(String base32) {
        base32 = base32.toUpperCase().replaceAll("[=]", ""); // Remove padding characters
        StringBuilder decoded = new StringBuilder();
        int buffer = 0;
        int bufferLength = 0;

        for (char c : base32.toCharArray()) {
            buffer <<= 5;
            buffer |= BITS_LOOKUP[c]; // Uses a lookup table to convert Base32 characters back to their binary
                                      // representation.
            bufferLength += 5;

            // Extracts 8-bit groups from the buffer to reconstruct the original data.
            while (bufferLength >= 8) {
                byte b = (byte) (buffer >> (bufferLength - 8));
                decoded.append((char) b);
                bufferLength -= 8;
            }
        }

        return decoded.toString();
    }

    public static String generateTOTP(String secretKey, long timeInterval) {
        try {
            byte[] decodedKey = decodeBase32(secretKey).getBytes();
            byte[] timeIntervalBytes = new byte[8];

            /*
             * In RFC 4226, it's specified that the counter value
             * (which, in the case of TOTP, is derived from the current time)
             * should be represented in big-endian format when used as input for the HMAC
             * computation.
             * Reference: https://datatracker.ietf.org/doc/html/rfc4226
             */

            // Convert the timeInterval into its byte representation
            for (int i = 7; i >= 0; i--) {
                // Extract the least significant byte from timeInterval
                timeIntervalBytes[i] = (byte) (timeInterval & 0xFF);
                // Right shift to process the next byte
                timeInterval >>= 8;
            }

            // Using the HMAC-SHA1 algorithm to hash the time interval with the secret key.
            Mac hmac = Mac.getInstance(HMAC_ALGO);
            hmac.init(new SecretKeySpec(decodedKey, HMAC_ALGO));
            byte[] hash = hmac.doFinal(timeIntervalBytes);

            /*
             * The line offset = hash[hash.length - 1] & 0xF; is used to determine the
             * offset into the HMAC hash
             * from which a 4-byte dynamic binary code will be extracted to generate the
             * TOTP.
             * This method of determining the offset is specified in the TOTP (RFC 6238) and
             * HOTP (RFC 4226) standards.
             */
            int offset = hash[hash.length - 1] & 0xF;

            /*
             * The expression hash[offset] & 0x7F uses the hexadecimal value 0x7F to mask
             * the most significant bit (MSB) of the byte at hash[offset],
             * ensuring it's set to 0. The reason for this is to make sure that the
             * resulting 32-bit integer
             * (binaryCode) is treated as a positive number. Reference TOTP (RFC 6238)
             */
            long mostSignificantByte = (hash[offset] & 0x7F) << 24;
            long secondMostSignificantByte = (hash[offset + 1] & 0xFF) << 16;
            long thirdMostSignificantByte = (hash[offset + 2] & 0xFF) << 8;
            long leastSignificantByte = hash[offset + 3] & 0xFF;

            // Extracting a 4-byte dynamic binary code from the hash.
            long binaryCode = mostSignificantByte
                    | secondMostSignificantByte
                    | thirdMostSignificantByte
                    | leastSignificantByte;

            // Convert the binary code to a 6-digit TOTP by taking a modulo operation with
            // 10^6.
            int totp = (int) (binaryCode % Math.pow(10, TOTP_LENGTH));
            return String.format("%0" + TOTP_LENGTH + "d", totp); // Making sure length is equal to TOTP_LENGTH

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate TOTP", e);
        }
    }

    public static String generateTOTP(String secretKey) {
        long timeInterval = System.currentTimeMillis() / 1000 / TIME_STEP;
        return generateTOTP(secretKey, timeInterval);
    }

    public static boolean validateTOTP(String secretKey, String inputTOTP) {
        long timeInterval = System.currentTimeMillis() / 1000 / TIME_STEP;

        // Check TOTP for current, previous, and next intervals
        boolean matches = IntStream.of(-1, 0, 1)
                .anyMatch(i -> generateTOTP(secretKey, timeInterval + i).equals(inputTOTP));
        if (matches) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        String orginalSecretKey = "IForgotMyPassword"; // :)
        String encodedSecretKey = encodeBase32(orginalSecretKey);
        String decodedSecretKey = decodeBase32(encodedSecretKey);

        System.out.println("Original String : " + orginalSecretKey);
        System.out.println("Base32 Encoded String: " + encodedSecretKey);
        System.out.println("Base32 Decoded String: " + decodedSecretKey);

        String secret = encodedSecretKey; // This should be Base32 encoded
        String freshTOTP = generateTOTP(secret);
        System.out.println("Generated TOTP: " + freshTOTP);
        System.out.println("Is valid? " + validateTOTP(secret, freshTOTP));

        Thread.sleep(120000); // 2 minutes waiting
        System.out.println("Is valid? " + validateTOTP(secret, freshTOTP));
    }
}