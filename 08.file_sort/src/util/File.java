package util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

public class File {

    public static int[] readNumbers(String fileName, int pos, int length) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream buffer = new BufferedInputStream(fis, length * 2);

        fis.getChannel().position(pos);

        byte[] bytes = new byte[length * 2];
        buffer.read(bytes, 0, length * 2);

        int[] numbers = new int[length];
        ByteBuffer numberBytes = ByteBuffer.allocate(length * 2)
                .order(ByteOrder.BIG_ENDIAN)
                .put(bytes);

        for (int i = 0; i < length; i++) {
            byte b1 = numberBytes.get(2 * i);
            byte b0 = numberBytes.get(2 * i + 1);
            numbers[i] = (b1 << 8) | (b0 & 0xff);
        }

        fis.close();

        return numbers;
    }


    public static void saveNumbers(String fileName, int[] numbers) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);

        for (int number : numbers) {
            byte[] numberBytes = ByteBuffer.allocate(2)
                    .order(ByteOrder.BIG_ENDIAN)
                        .putShort((short) number)
                    .array();
            fos.write(numberBytes);
        }

        fos.close();
    }


    public static void createGBFile(String fileName, int fileSize) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);

        for (int i = 0; i < fileSize; i++) {
            short number = (short) new Random().nextInt(1 << 15);
            byte[] numberBytes = ByteBuffer.allocate(2)
                    .order(ByteOrder.BIG_ENDIAN)
                    .putShort(number)
                    .array();
            fos.write(numberBytes);
        }

        fos.close();
    }
}
