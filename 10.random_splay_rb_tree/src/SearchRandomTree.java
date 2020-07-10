import tree.RandomTree;
import tree.SplayTree;
import tree.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class SearchRandomTree {
    private static final int TREE_ELEMENT_COUNT = 100_000;
    private static final int MAX_ELEMENT_VALUE = 1_000_000;

    public static void main(String[] args) {
        List<Integer> inputOrderedNumbers = generateOrderedNumbers(TREE_ELEMENT_COUNT);
        List<Integer> inputRandomOrderedNumbers = generateRandomOrderedNumbers(TREE_ELEMENT_COUNT, MAX_ELEMENT_VALUE);

        runTestRandomTree(inputRandomOrderedNumbers, inputOrderedNumbers);
        runTestSplayTree(inputRandomOrderedNumbers, inputOrderedNumbers);
    }


    private static void runTestRandomTree(List<Integer> inputRandomOrderedNumbers, List<Integer> inputOrderedNumbers) {
        System.out.println("RandomTree Test");

        System.out.println("Input random numbers [count=" + (inputRandomOrderedNumbers.size() + 1) + "]");
        RandomTree randomTree1 = new RandomTree(0);
        runInsertTest(randomTree1, inputRandomOrderedNumbers);
        runSearchAllItemTest(randomTree1, inputRandomOrderedNumbers);
        runSearch10percentTest(randomTree1, inputRandomOrderedNumbers);
        runDelete10percentTest(randomTree1, inputRandomOrderedNumbers);

        System.out.println("Input ordered numbers [count=" + (inputOrderedNumbers.size() + 1) + "]");
        RandomTree randomTree = new RandomTree(0);
        runInsertTest(randomTree, inputOrderedNumbers);
        runSearchAllItemTest(randomTree, inputOrderedNumbers);
        runSearch10percentTest(randomTree, inputOrderedNumbers);
        runDelete10percentTest(randomTree, inputOrderedNumbers);
    }


    private static void runTestSplayTree(List<Integer> inputRandomOrderedNumbers, List<Integer> inputOrderedNumbers) {
        System.out.println("SplayTree Test");

        System.out.println("Input random numbers [count=" + (inputRandomOrderedNumbers.size() + 1) + "]");
        SplayTree splayTree = new SplayTree(MAX_ELEMENT_VALUE / 2);
        runInsertTest(splayTree, inputRandomOrderedNumbers);
        runSearchAllItemTest(splayTree, inputRandomOrderedNumbers);
        runSearch10percentTest(splayTree, inputRandomOrderedNumbers);
        runDelete10percentTest(splayTree, inputRandomOrderedNumbers);

        System.out.println("Input ordered numbers [count=" + (inputOrderedNumbers.size() + 1) + "]");
        SplayTree splayTree2 = new SplayTree(MAX_ELEMENT_VALUE / 2);
        runInsertTest(splayTree2, inputOrderedNumbers);
        runSearchAllItemTest(splayTree2, inputOrderedNumbers);
        runSearch10percentTest(splayTree2, inputOrderedNumbers);
        runDelete10percentTest(splayTree2, inputOrderedNumbers);
    }


    private static void runInsertTest(Tree tree, List<Integer> inputNumbers) {
        long startTime = System.nanoTime();
        for (Integer number : inputNumbers) {
            tree.insert(number);
        }
        long endTime = System.nanoTime();

        System.out.println("Insert duration: " + (endTime - startTime) + " ns");
        }


    private static void runSearch10percentTest(Tree tree, List<Integer> inputNumbers) {
        long startTime = System.nanoTime();
        for (int i = 0; i < inputNumbers.size() * 0.1; i++) {
            tree.search(inputNumbers.get(new Random().nextInt(inputNumbers.size())));
        }
        long endTime = System.nanoTime();

        System.out.println("Search 10% item duration: " + (endTime - startTime) + " ns");
    }


    private static void runSearchAllItemTest(Tree tree, List<Integer> inputNumbers) {
        long startTime = System.nanoTime();

        boolean isAllItemSearched = false;
        for (Integer number : inputNumbers) {
            isAllItemSearched = tree.search(number);
            if (!isAllItemSearched) {
                break;
            }
        }
        long endTime = System.nanoTime();

        if (isAllItemSearched) {
            System.out.println("Search all item duration: " + (endTime - startTime) + " ns");
        } else {
            System.out.println("ERROR: Not all item was added into tree");
        }
    }


    private static void runDelete10percentTest(Tree tree, List<Integer> inputNumbers) {
        long startTime = System.nanoTime();
        for (int i = 0; i < inputNumbers.size() * 0.1; i++) {
            tree.remove(inputNumbers.get(new Random().nextInt(inputNumbers.size())));
        }
        long endTime = System.nanoTime();

        System.out.println("Delete 10% items duration: " + (endTime - startTime) + " ns");
    }


    private static List<Integer> generateOrderedNumbers(int count) {
        Set<Integer> uniqNumbers = new HashSet<>();
        for (int num = 1; num < count; num++) {
            uniqNumbers.add(num);
        }
        return new ArrayList<>(uniqNumbers);
    }


    private static List<Integer> generateRandomOrderedNumbers(int count, int maxElement) {
        Set<Integer> uniqNumbers = new HashSet<>();
        while (uniqNumbers.size() < count - 1) {
            int number = new Random().nextInt(maxElement);
            if (number != (MAX_ELEMENT_VALUE / 2)) {
                uniqNumbers.add(number);
            }
        }
        return new ArrayList<>(uniqNumbers);
    }

}