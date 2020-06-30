import algo.array.ArrayListWrapper;
import algo.array.FactorArray;
import algo.array.MatrixArray;
import algo.array.SingleArray;
import algo.array.Tester;
import algo.array.VectorArray;
import algo.priorityqueue.PriorityQueueImpl;


public class DataStructure {
    public static void main(String[] args) {
        SingleArray<String> singleArray = new SingleArray<>();
        Tester.testArray(singleArray);

        VectorArray<String> vectorArray = new VectorArray<>();
        Tester.testArray(vectorArray);

        FactorArray<String> factorArray = new FactorArray<>();
        Tester.testArray(factorArray);

        MatrixArray<String> matrixArray = new MatrixArray<>(2);
        Tester.testArray(matrixArray);

        PriorityQueueImpl<String> priorityQueue = new PriorityQueueImpl<>();
        Tester.testPriorityQueue(priorityQueue);

        final int[] testDataCount = {1000, 10000, 100000};
        for (int count : testDataCount) {
            SingleArray<String> singleArrayN = new SingleArray<>();
            Tester.fullTestCaseSet(singleArrayN, count);

            VectorArray<String> vectorArrayN = new VectorArray<>(1000);
            Tester.fullTestCaseSet(vectorArrayN, count);

            VectorArray<String> vectorArrayN2 = new VectorArray<>(10000);
            Tester.fullTestCaseSet(vectorArrayN2, count);

            VectorArray<String> vectorArrayN3 = new VectorArray<>(100000);
            Tester.fullTestCaseSet(vectorArrayN3, count);

            FactorArray<String> factorArrayN = new FactorArray<>();
            Tester.fullTestCaseSet(factorArrayN, count);

            MatrixArray<String> matrixArrayN = new MatrixArray<>(1000);
            Tester.fullTestCaseSet(matrixArrayN, count);

            MatrixArray<String> matrixArrayN2 = new MatrixArray<>(10000);
            Tester.fullTestCaseSet(matrixArrayN2, count);

            MatrixArray<String> matrixArrayN3 = new MatrixArray<>(100000);
            Tester.fullTestCaseSet(matrixArrayN3, count);

            ArrayListWrapper<String> arrayListWrapper = new ArrayListWrapper<>();
            Tester.fullTestCaseSet(arrayListWrapper, count);
        }
    }
}
