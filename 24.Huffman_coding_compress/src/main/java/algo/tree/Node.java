package algo.tree;


public class Node implements Comparable<Node> {
    private static final String LEFT_PART_CODE = "0";
    private static final String RIGHT_PART_CODE = "1";

    private byte symbol;
    private final int frequency;
    private String code;
    private Node left;
    private Node right;


    public Node(byte symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }


    public Node(Node left, Node right) {
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }


    public String getCode() {
        return code;
    }


    public byte getSymbol() {
        return symbol;
    }


    public Node getLeft() {
        return left;
    }


    public Node getRight() {
        return right;
    }


    public boolean isCharNode() {
        return left == null && right == null;
    }

    public void computeCode(String code) {
        this.code = code;
        if (left != null) {
            left.computeCode(code + LEFT_PART_CODE);
        }
        if (right != null) {
            right.computeCode(code + RIGHT_PART_CODE);
        }
    }


    public Node searchChildNode(String code) {
        if (code == null) {
            return null;
        }

        if (LEFT_PART_CODE.equals(code)) {
            return left;
        }
        if (RIGHT_PART_CODE.equals(code)) {
            return right;
        }
        return this;
    }


    @Override
    public int compareTo(Node o) {
        return Integer.compare(frequency, o.frequency);
    }
}
