package algo.tasks;


import algo.tester.ITask;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class BitboardKing implements ITask {

    @Override
    public List<String> run(List<String> data) {
        final int kingBitIndex = Integer.parseInt(data.get(0));
        final BigInteger notA = new BigInteger("FEFEFEFEFEFEFEFE", 16);
        final BigInteger notH = new BigInteger("7F7F7F7F7F7F7F7F", 16);

        BigInteger kingPos = new BigInteger("1").shiftLeft(kingBitIndex);
        BigInteger p1 = checkRange(kingPos.and(notA).shiftRight(9));
        BigInteger p2 = checkRange(kingPos.shiftRight(8));
        BigInteger p3 = checkRange(kingPos.and(notH).shiftRight(7));
        BigInteger p4 = checkRange(kingPos.and(notA).shiftRight(1));
        BigInteger p6 = checkRange(kingPos.and(notH).shiftLeft(1));
        BigInteger p7 = checkRange(kingPos.and(notA).shiftLeft(7));
        BigInteger p8 = checkRange(kingPos.shiftLeft(8));
        BigInteger p9 = checkRange(kingPos.and(notH).shiftLeft(9));

        BigInteger kingMovesMask = p7 .or(p8).or(p9)
                               .or(p4)       .or(p6)
                               .or(p1).or(p2).or(p3);

        final int moveCount = kingMovesMask.bitCount();
        /*long kingMoves = kingMovesMask;
        while (kingMoves != 0) {
            kingMoves &= (kingMoves - 1);
            moveCount++;
        }*/

        List<String> result = new ArrayList<>();
        result.add(String.valueOf(moveCount));
        result.add(String.valueOf(kingMovesMask));

        return result;
    }

    private BigInteger checkRange(BigInteger value) {
        int c1 = value.compareTo(BigInteger.ZERO);
        int c2 = value.compareTo(new BigInteger("8000000000000000", 16));
        return c1 == -1 || c2 > 0
               ? BigInteger.ZERO
               : value;
    }
}
