package algo;


import algo.tasks.BitboardKing;
import algo.tasks.BitboardKnight;
import algo.tester.ITask;
import algo.tester.Tester;


public class Bits {
    private static final String BITBOARD_KING_TEST_DATA_PATH = "c:\\Otus\\homework\\03.bits\\src\\testdata\\1.Bitboard_King";
    private static final String BITBOARD_KNIGHT_TEST_DATA_PATH = "c:\\Otus\\homework\\03.bits\\src\\testdata\\2.Bitboard_Knight";

    public static void main(String[] args) {

        ITask bitboardKing = new BitboardKing();
        Tester tester = new Tester(bitboardKing, BITBOARD_KING_TEST_DATA_PATH);
        tester.RunTest();

        ITask bitboardKnight = new BitboardKnight();
        Tester tester2 = new Tester(bitboardKnight, BITBOARD_KNIGHT_TEST_DATA_PATH);
        tester2.RunTest();
    }
}
