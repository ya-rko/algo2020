package algo.tasks;

import algo.tester.ITask;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BitboardKnight implements ITask {

    @Override
    public List<String> run(List<String> data) {
        final int knightBitIndex = Integer.parseInt(data.get(0));
        final BigInteger notA = new BigInteger("FEFEFEFEFEFEFEFE", 16);
        final BigInteger notB = new BigInteger("FDFDFDFDFDFDFDFD", 16);
        final BigInteger notH = new BigInteger("7F7F7F7F7F7F7F7F", 16);
        final BigInteger notG = new BigInteger("BFBFBFBFBFBFBFBF", 16);

        BigInteger knightPos = new BigInteger("1").shiftLeft(knightBitIndex);
        BigInteger p1 = checkRange(knightPos.and(notA).shiftLeft(15));
        BigInteger p2 = checkRange(knightPos.and(notH).shiftLeft(17));
        BigInteger p3 = checkRange(knightPos.and(notA).and(notB).shiftLeft(6));
        BigInteger p4 = checkRange(knightPos.and(notH).and(notG).shiftLeft(10));
        BigInteger p5 = checkRange(knightPos.and(notA).shiftRight(10));
        BigInteger p6 = checkRange(knightPos.and(notH).and(notG).shiftRight(6));
        BigInteger p7 = checkRange(knightPos.and(notA).and(notB).shiftRight(17));
        BigInteger p8 = checkRange(knightPos.and(notH).shiftRight(15));

        BigInteger knightMovesMask = p1.or(p2)
                                .or(p3).or(p4)
                                .or(p5).or(p6)
                                .or(p7).or(p8);

        final int moveCount = knightMovesMask.bitCount();

        List<String> result = new ArrayList<>();
        result.add(String.valueOf(moveCount));
        result.add(String.valueOf(knightMovesMask));

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
