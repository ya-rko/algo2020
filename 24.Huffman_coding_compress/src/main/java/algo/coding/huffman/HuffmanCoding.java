package algo.coding.huffman;


import algo.coding.util.Bits;
import algo.tree.Node;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class HuffmanCoding {

    public static byte[] encode(byte[] input, FrequencyTable frequencyTable) {
        Map<Byte, Node> byteNodes = createByteCodeMap(frequencyTable);

        StringBuilder encodedString = new StringBuilder();
        for (byte symbol : input) {
            encodedString.append(byteNodes.get(symbol).getCode());
        }

        int lackBitsCount = Byte.SIZE - encodedString.length() % Byte.SIZE;
        StringBuilder alignedEncodedString = Bits.alignZeroIntoEnd(encodedString.toString());
        StringBuilder alignedAddedZeroCount = Bits.alignZeroIntoBegin(Integer.toBinaryString(lackBitsCount));

        alignedEncodedString.insert(0, alignedAddedZeroCount);

        return new BigInteger(alignedEncodedString.toString(), 2).toByteArray();
    }


    public static byte[] decode(byte[] encodedInput, FrequencyTable frequencyTable) {
        Node root = createCodeTree(frequencyTable);

        byte[] addedZeroByte = Arrays.copyOfRange(encodedInput, 0, 1);
        int addedZeroCount = new BigInteger(addedZeroByte).intValue();
        byte[] encodedBody = Arrays.copyOfRange(encodedInput, 1, encodedInput.length);

        StringBuilder encodedBodyAsString = Bits.alignZeroIntoBegin(new BigInteger(encodedBody).toString(2));
        encodedBodyAsString.delete(encodedBodyAsString.length() - addedZeroCount, encodedBodyAsString.length());

        Node node = root;
        List<Byte> decodedBytes = new ArrayList<>();
        for (int i = 0; i < encodedBodyAsString.length(); i++) {
            Node nextNode = node.searchChildNode(root.getCode() + encodedBodyAsString.charAt(i));
            if (nextNode.isCharNode()) {
                decodedBytes.add(nextNode.getSymbol());
                node = root;
            } else {
                node = nextNode;
            }
        }

        ByteArrayOutputStream bo = new ByteArrayOutputStream(decodedBytes.size());
        decodedBytes.forEach(bo::write);
        return bo.toByteArray();
    }


    private static Node createCodeTree(FrequencyTable frequencyTable) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : frequencyTable.getFrequencyTable().entrySet()) {
            final Byte symbol = entry.getKey();
            final Integer frequency = entry.getValue();
            priorityQueue.add(new Node(symbol, frequency));
        }

        while (priorityQueue.size() > 1) {
            Node first = priorityQueue.poll();
            Node second = priorityQueue.poll();
            priorityQueue.add(new Node(first, second));
        }
        Node root = priorityQueue.poll();
        if (root != null) {
            root.computeCode("");
        }

        return root;
    }


    private static Map<Byte, Node> createByteCodeMap(FrequencyTable frequencyTable) {
        Map<Byte, Node> byteNodes = new HashMap<>();

        class TravelTree {
            public void run(Node node) {
                if (node.isCharNode()) {
                    byteNodes.put(node.getSymbol(), node);
                    return;
                }
                run(node.getLeft());
                run(node.getRight());
            }
        }

        Node root = createCodeTree(frequencyTable);
        new TravelTree().run(root);
        return byteNodes;
    }
}
