import java.util.*
import java.util.stream.IntStream
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.math.pow

class TOTP {

    // https://medium.com/@rakesh.open.source/time-based-one-time-password-totp-java-implementation-82a472bd6bf9
    /*
     * The Base32 encoding scheme, as defined in RFC 4648, intentionally omits
     * the numbers 0, 1, 8, and 9 to reduce the possibility of human
     * misinterpretation
     * and transcription errors.
     * Reference: https://datatracker.ietf.org/doc/html/rfc4648
     */
    private val BASE32_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray()

    // ASCII character set defines 128 unique values
    private val BITS_LOOKUP = IntArray(128)

    init {
        for (i in BASE32_CHARS.indices) {
            BITS_LOOKUP[BASE32_CHARS[i].code] = i
        }
    }

    fun encodeBase32(input: String): String {
        val encoded = StringBuilder()
        var buffer = 0
        var bufferLength = 0

        for (b in input.toByteArray()) {
            buffer = buffer shl 8 // Shift the buffer 8 bits to the left
            buffer = buffer or (b.toInt() and 0xFF) // Add the byte to the buffer
            bufferLength += 8 // Increase the buffer length by 8 bits

            /*
             * Base32 encoding uses a 5-bit group from the available data.
             * So, we check if we have at least 5 bits in our buffer.
             * We then extract the top 5 bits for Base32 encoding.
             */
            while (bufferLength >= 5) {
                val index = (buffer shr (bufferLength - 5)) and 0x1F
                encoded.append(BASE32_CHARS[index])
                bufferLength -= 5 // Decrease the buffer length by 5 bits
            }
        }

        /*
         * Padding is necessary for Base32 encoding to ensure the encoded output
         * is a multiple of 8 characters, making it easier to decode later.
         * The '=' character is used as a padding character.
         */
        while (encoded.length % 8 != 0) {
            encoded.append('=')
        }

        return encoded.toString()
    }

    fun decodeBase32(base32: String): String {
        var base32 = base32
        base32 = base32.uppercase(Locale.getDefault()).replace("[=]".toRegex(), "") // Remove padding characters
        val decoded = StringBuilder()
        var buffer = 0
        var bufferLength = 0

        for (c in base32.toCharArray()) {
            buffer = buffer shl 5
            buffer =
                buffer or BITS_LOOKUP[c.code] // Uses a lookup table to convert Base32 characters back to their binary representation.
            bufferLength += 5

            // Extracts 8-bit groups from the buffer to reconstruct the original data.
            while (bufferLength >= 8) {
                val b = (buffer shr (bufferLength - 8)).toByte()
                decoded.append(Char(b.toUShort()))
                bufferLength -= 8
            }
        }

        return decoded.toString()
    }

    fun generateTOTP(secretKey: String, timeInterval: Long): String {
        var timeInterval = timeInterval
        try {
            val decodedKey = decodeBase32(secretKey).toByteArray()
            val timeIntervalBytes = ByteArray(8)

            /*
             * In RFC 4226, it's specified that the counter value
             * (which, in the case of TOTP, is derived from the current time)
             * should be represented in big-endian format when used as input for the HMAC
             * computation.
             * Reference: https://datatracker.ietf.org/doc/html/rfc4226
             */

            // Convert the timeInterval into its byte representation
            for (i in 7 downTo 0) {
                // Extract the least significant byte from timeInterval
                timeIntervalBytes[i] = (timeInterval and 0xFFL).toByte()
                // Right shift to process the next byte
                timeInterval = timeInterval shr 8
            }

            // Using the HMAC-SHA1 algorithm to hash the time interval with the secret key.
            val hmac = Mac.getInstance(HMAC_ALGO)
            hmac.init(SecretKeySpec(decodedKey, HMAC_ALGO))
            val hash = hmac.doFinal(timeIntervalBytes)

            /*
             * The line offset = hash[hash.length - 1] & 0xF; is used to determine the
             * offset into the HMAC hash
             * from which a 4-byte dynamic binary code will be extracted to generate the
             * TOTP.
             * This method of determining the offset is specified in the TOTP (RFC 6238) and
             * HOTP (RFC 4226) standards.
             */
            val offset = hash[hash.size - 1].toInt() and 0xF

            /*
             * The expression hash[offset] & 0x7F uses the hexadecimal value 0x7F to mask
             * the most significant bit (MSB) of the byte at hash[offset],
             * ensuring it's set to 0. The reason for this is to make sure that the
             * resulting 32-bit integer
             * (binaryCode) is treated as a positive number. Reference TOTP (RFC 6238)
             */
            val mostSignificantByte = ((hash[offset].toInt() and 0x7F) shl 24).toLong()
            val secondMostSignificantByte = ((hash[offset + 1].toInt() and 0xFF) shl 16).toLong()
            val thirdMostSignificantByte = ((hash[offset + 2].toInt() and 0xFF) shl 8).toLong()
            val leastSignificantByte = (hash[offset + 3].toInt() and 0xFF).toLong()

            // Extracting a 4-byte dynamic binary code from the hash.
            val binaryCode = (mostSignificantByte
                    or secondMostSignificantByte
                    or thirdMostSignificantByte
                    or leastSignificantByte)

            // Convert the binary code to a 6-digit TOTP by taking a modulo operation with 10^6.
            val totp: Int = (binaryCode % 10.0.pow(TOTP_LENGTH)).toInt()
            return String.format("%0" + TOTP_LENGTH + "d", totp) // Making sure length is equal to TOTP_LENGTH
        } catch (e: Exception) {
            throw RuntimeException("Failed to generate TOTP", e)
        }
    }

    fun generateTOTP(secretKey: String): String {
        val timeInterval = System.currentTimeMillis() / 1000 / TIME_STEP
        return generateTOTP(secretKey, timeInterval)
    }

    fun validateTOTP(secretKey: String, inputTOTP: String): Boolean {
        val timeInterval = System.currentTimeMillis() / 1000 / TIME_STEP

        // Check TOTP for current, previous, and next intervals
        val matches = IntStream.of(-1, 0, 1)
            .anyMatch { i: Int ->
                generateTOTP(secretKey, timeInterval + i) == inputTOTP
            }
        return matches
        /*
        if (matches)
            return true
        return false
         */
    }

    companion object {
        private const val HMAC_ALGO = "HmacSHA1"
        private const val TOTP_LENGTH = 6 // length of TOTP will be 6 digits
        private const val TIME_STEP = 30 // 30 seconds
    }
}

fun main() {
    val originalSecretKey = "IForgotMyPassword" // :)
    val encodedSecretKey = TOTP().encodeBase32(originalSecretKey)
    val decodedSecretKey = TOTP().decodeBase32(encodedSecretKey)

    println("Original String : $originalSecretKey")
    println("Base32 Encoded String: $encodedSecretKey")
    println("Base32 Decoded String: $decodedSecretKey")

    val secret = encodedSecretKey // This should be Base32 encoded
    val freshTOTP = TOTP().generateTOTP(secret)
    println("Generated TOTP: $freshTOTP")
    println("Is valid? " + TOTP().validateTOTP(secret, freshTOTP))

    Thread.sleep(120000) // 2 minutes waiting
    println("Is valid? " + TOTP().validateTOTP(secret, freshTOTP))
}