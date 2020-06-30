package algo.array;


import algo.priorityqueue.PriorityQueue;

import java.util.Random;


public class Tester {
    public static void addValues(Array<String> array, int count) {
        long startTime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            array.add(i + "");
        }
        long duration = System.nanoTime() - startTime;
        System.out.println("Add values. Count: " + count + " (duration: " + duration + " ns)");
    }


    public static void addValueByIndex(Array<String> array, int index, String testDescription) {
        long startTime = System.nanoTime();
        array.add(index + "", index);
        long duration = System.nanoTime() - startTime;

        if (testDescription == null || testDescription.isEmpty()) {
            testDescription = "Add by index " + index;
        }
        System.out.println(testDescription + " (duration: " + duration + " ns)");
    }


    public static void getValues(Array<String> array, int count) {
        long startTime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            array.get(i);
        }
        long duration = System.nanoTime() - startTime;
        System.out.println("Get all values. Count: " + count + " (duration: " + duration + " ns)");
    }


    public static void getValueByIndex(Array<String> array, int index, String testDescription) {
        long startTime = System.nanoTime();
        array.get(index);
        long duration = System.nanoTime() - startTime;

        if (testDescription == null || testDescription.isEmpty()) {
            testDescription = "Get by index " + index;
        }
        System.out.println(testDescription + " (duration: " + duration + " ns)");
    }


    public static void removeValues(Array<String> array, int count) {
        long startTime = System.nanoTime();
        for (int i = count-1; i >= 0; i--) {
            array.remove(i);
        }
        long duration = System.nanoTime() - startTime;
        System.out.println("Remove all values. Count: " + count + " (duration: " + duration + " ns)");
    }


    public static void removeValueByIndex(Array<String> array, int index, String testDescription) {
        long startTime = System.nanoTime();
        array.remove(index);
        long duration = System.nanoTime() - startTime;

        if (testDescription == null || testDescription.isEmpty()) {
            testDescription = "Remove by index " + index;
        }
        System.out.println(testDescription + " (duration: " + duration + " ns)");
    }


    public static void addAtBegin(Array<String> array) {
        Tester.addValueByIndex(array, 0, "Add At Begin");
    }


    public static void addAtEnd(Array<String> array) {
        Tester.addValueByIndex(array, array.size(), "Add At End");
    }


    public static void addAtRandom(Array<String> array) {
        Tester.addValueByIndex(array, new Random().nextInt(array.size()), "Add At Random");
    }


    public static void getFirst(Array<String> array) {
        Tester.getValueByIndex(array, 0, "Get First");
    }


    public static void getLast(Array<String> array) {
        Tester.getValueByIndex(array, array.size() - 1, "Get Last");
    }


    public static void getRandom(Array<String> array) {
        Tester.getValueByIndex(array, new Random().nextInt(array.size()), "Get Random");
    }


    public static void removeFirst(Array<String> array) {
        Tester.removeValueByIndex(array, 0, "Remove First");
    }


    public static void removeLast(Array<String> array) {
        Tester.removeValueByIndex(array, array.size() - 1, "Remove Last");
    }


    public static void removeRandom(Array<String> array) {
        Tester.removeValueByIndex(array, new Random().nextInt(array.size()), "Remove Random");
    }


    public static void fullTestCaseSet(Array<String> array, int count) {
        System.out.println(array.toString() + " performance test. Count: " + count);

        Tester.addValues(array, count);
        Tester.getValues(array, count);

        Tester.addAtBegin(array);
        Tester.addAtEnd(array);
        Tester.addAtRandom(array);

        Tester.getFirst(array);
        Tester.getLast(array);
        Tester.getRandom(array);

        Tester.removeFirst(array);
        Tester.removeLast(array);
        Tester.removeRandom(array);
        Tester.removeValues(array, count - 3);

        System.out.println("");
    }


    public static void testArray(Array<String> array) {
        final String[] testData = {"1", "2", "3", "4", "5", "6"};
        final int[] testIndex = {1, 3, 4};
        final int[] testIndex2 = {0, 2, 5};

        for (int index : testIndex) {
            array.add(testData[index]);
        }

        for (int index2 : testIndex2) {
            array.add(testData[index2], index2);
        }

        for (int i = 0; i < testData.length; i++) {
            if (!testData[i].equals(array.get(i))) {
                System.out.println("TEST FAILED! Get test by index " + i);
            }
        }

        for (int i = testIndex2.length-1; i >= 0; i--) {
            array.remove(testIndex2[i]);
        }

        for (int i = 0; i < testIndex.length; i++) {
            if (!testData[testIndex[i]].equals(array.get(i))) {
                System.out.println("TEST FAILED! Get test after remove by index " + i);
            }
        }
    }


    public static void testPriorityQueue(PriorityQueue<String> priorityQueue) {
        String item = "";

        priorityQueue.enqueue(1, "11");
        priorityQueue.enqueue(1, "12");
        priorityQueue.enqueue(1, "13");

        item = priorityQueue.dequeue();
        assert item.equals("11");

        priorityQueue.enqueue(3, "31");
        priorityQueue.enqueue(3, "32");

        item = priorityQueue.dequeue();
        assert item.equals("31");

        priorityQueue.enqueue(2, "21");
        priorityQueue.enqueue(2, "22");

        item = priorityQueue.dequeue();
        assert item.equals("32");

        item = priorityQueue.dequeue();
        assert item.equals("21");

        item = priorityQueue.dequeue();
        assert item.equals("22");

        item = priorityQueue.dequeue();
        assert item.equals("12");

        item = priorityQueue.dequeue();
        assert item.equals("13");
    }

}
