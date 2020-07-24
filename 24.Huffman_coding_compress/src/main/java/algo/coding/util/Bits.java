package algo.coding.util;


import org.omg.CORBA.WStringSeqHelper;
import org.omg.PortableInterceptor.ServerRequestInfo;

public class Bits {
    public static int getInt(byte[] bytes, int offset) {
        return  ((bytes[offset    ]       ) << 24) |
                ((bytes[offset + 1] & 0xFF) << 16) |
                ((bytes[offset + 2] & 0xFF) <<  8) |
                ((bytes[offset + 3] & 0xFF));
    }


    public static char getChar(byte[] bytes, int offset) {
        return (char) ((bytes[offset    ] << 8) |
                       (bytes[offset + 1] & 0xFF));
    }


    public static StringBuilder alignZeroIntoEnd(String input) {
        StringBuilder output = new StringBuilder(input);
        int lackBitsCount;
        if ((lackBitsCount = (Byte.SIZE - input.length() % Byte.SIZE)) != Byte.SIZE) {
            output.append(new String(new char[lackBitsCount]).replace('\0', '0'));
        }

        return output;
    }


    public static StringBuilder alignZeroIntoBegin(String input) {
        StringBuilder output = new StringBuilder(input);
        int lackBitsCount;
        if ((lackBitsCount = (Byte.SIZE - input.length() % Byte.SIZE)) != Byte.SIZE) {
            output.insert(0, new String(new char[lackBitsCount]).replace('\0', '0'));
        }

        return output;
    }
}
