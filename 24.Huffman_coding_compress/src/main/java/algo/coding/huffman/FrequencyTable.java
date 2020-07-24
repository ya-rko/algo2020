package algo.coding.huffman;


import algo.coding.util.Bits;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;


public class FrequencyTable {
    private static final int FREQUENCY_TABLE_SIZE_LENGTH = Integer.BYTES;
    private static final int TABLE_ENTRY_LENGTH = Byte.BYTES + Integer.BYTES;

    private Map<Byte, Integer> frequencyTable = new HashMap<>();


    public static FrequencyTable create(byte[] input) {
        return new FrequencyTable(input);
    }


    public static FrequencyTable deserialize(byte[] encodedInput) {
        Map<Byte, Integer> frequencyTable = new HashMap<>();
        final int frequencyTableSize = Bits.getInt(encodedInput, 0);

        int itemStartIndex = FREQUENCY_TABLE_SIZE_LENGTH;
        for (int i = 0; i < frequencyTableSize; i++) {
            byte symbol = encodedInput[itemStartIndex];
            int frequency = Bits.getInt(encodedInput, itemStartIndex + Byte.BYTES);
            frequencyTable.put(symbol, frequency);

            itemStartIndex += TABLE_ENTRY_LENGTH;
        }
        return new FrequencyTable(frequencyTable);
    }


    private FrequencyTable(Map<Byte, Integer> frequencyTable) {
        this.frequencyTable = frequencyTable;
    }


    private FrequencyTable(byte[] input) {
        for (byte symbol : input) {
            if (frequencyTable.containsKey(symbol)) {
                frequencyTable.put(symbol, frequencyTable.get(symbol) + 1);
            } else {
                frequencyTable.put(symbol, 1);
            }
        }
    }


    public Map<Byte, Integer> getFrequencyTable() {
        return frequencyTable;
    }


    public int byteSize() {
        return FREQUENCY_TABLE_SIZE_LENGTH + (frequencyTable.size() * TABLE_ENTRY_LENGTH);
    }


    public byte[] serialize() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(FREQUENCY_TABLE_SIZE_LENGTH + (frequencyTable.size() * TABLE_ENTRY_LENGTH))
                .order(ByteOrder.BIG_ENDIAN)
                .putInt(frequencyTable.size());

        for (Map.Entry<Byte, Integer> entry : frequencyTable.entrySet()) {
            final Byte symbol = entry.getKey();
            final Integer frequency = entry.getValue();
            byteBuffer.put(symbol).putInt(frequency);
        }

        return byteBuffer.array();
    }
}