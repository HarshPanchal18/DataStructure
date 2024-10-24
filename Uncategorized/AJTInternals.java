package Uncategorized;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class AJTInternals {
    public static void main(String[] args) {
        // ioStream();
        // fileIO();
        byteIO();
        // threading();
    }

    public static void ioStream() {
        byte[] array;

        try {
            File inputFile = new File("input.txt");
            InputStream input = new FileInputStream(inputFile);

            System.out.println("Available bytes: " + input.available());

            array = new byte[input.available()];
            input.read(array); // Reads some number of bytes from the input stream and stores them into the
                               // buffer array

            String data = new String(array);
            System.out.println("Data read from file: " + data);

            byte[] dataBytes = data.getBytes();

            File outputFile = new File("output.txt");
            OutputStream output = new FileOutputStream(outputFile);

            output.write(dataBytes);

            System.out.println("Data is written to a file");

            input.close();
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fileIO() {
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            System.out.println("Data in file: ");

            /*
             * int i = fis.read();
             * while (i != -1) {
             * System.out.println((char) i);
             *
             * i = fis.read(); // Read next byte from file
             * }
             */

            System.out.println("Available bytes at the beginning: " + fis.available());
            fis.read();
            fis.read();
            fis.read();

            System.out.println("Available bytes at the beginning: " + fis.available());
            fis.skip(5); // Skip 5 bytes
            System.out.println("Available bytes at the beginning: " + fis.available());

            FileOutputStream fos = new FileOutputStream("output.txt");

            String data = "This is a line of text inside the file.";
            byte[] array = data.getBytes();

            fos.write(array);
            System.out.println("Written to file...");

            fis.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void byteIO() {
        String data = "This is data.";
        byte[] array = { 1, 2, 3, 4 };
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(array);
            System.out.println("The bytes are: " + bais.available());

            for (int i = 0; i < array.length; i++) {
                System.out.println(bais.read());
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(data.getBytes());

            String streamData = baos.toString();
            System.out.println("Output stream: " + streamData);

            byte[] byteData = baos.toByteArray();
            System.out.println("Byte stream: " + byteData);
            for (int i = 0; i < byteData.length; i++) {
                System.out.print((char) byteData[i]);
            }

            String stringData = baos.toString();
            System.out.println("String stream: " + stringData);

            bais.close();
            baos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void threading() {
    }
}