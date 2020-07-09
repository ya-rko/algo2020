import tree.AVLTree;
import tree.BTree;
import tree.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class SearchBTree {
    private static final int TREE_ELEMENT_COUNT = 100000;
    private static final int MAX_ELEMENT_VALUE = 1000000;
    private static final int ROOT_KEY = MAX_ELEMENT_VALUE / 2;

    public static void main(String[] args) {
        List<Integer> inputRandomOrderedNumbers = generateRandomOrderedNumbers(TREE_ELEMENT_COUNT, MAX_ELEMENT_VALUE);
        List<Integer> inputOrderedNumbers = generateOrderedNumbers(TREE_ELEMENT_COUNT);

        runTestBTree(inputRandomOrderedNumbers, inputOrderedNumbers);
        runTestAVLTree(inputRandomOrderedNumbers, inputOrderedNumbers);
    }


    private static void runTestBTree(List<Integer> inputRandomOrderedNumbers, List<Integer> inputOrderedNumbers) {
        System.out.println("BTree Test");

        System.out.println("Input random ordered numbers");
        BTree biTree1 = new BTree(ROOT_KEY, null);
        runInsertTest(biTree1, inputRandomOrderedNumbers);
        runSearchAllItemTest(biTree1, inputRandomOrderedNumbers);
        runSearch10percentTest(biTree1, inputRandomOrderedNumbers);
        runDelete10percentTest(biTree1, inputRandomOrderedNumbers);

        System.out.println("Input ordered numbers");
        BTree biTree2 = new BTree(ROOT_KEY, null);
        runInsertTest(biTree2, inputOrderedNumbers);
        runSearchAllItemTest(biTree2, inputOrderedNumbers);
        runSearch10percentTest(biTree2, inputOrderedNumbers);
        runDelete10percentTest(biTree2, inputOrderedNumbers);
    }


    private static void runTestAVLTree(List<Integer> inputRandomOrderedNumbers, List<Integer> inputOrderedNumbers) {
        System.out.println("AVLTree Test");

        System.out.println("Input random ordered numbers");
        AVLTree avlTree1 = new AVLTree(ROOT_KEY);
        runInsertTest(avlTree1 , inputRandomOrderedNumbers);
        runSearchAllItemTest(avlTree1, inputRandomOrderedNumbers);
        runSearch10percentTest(avlTree1 , inputRandomOrderedNumbers);
        runDelete10percentTest(avlTree1 , inputRandomOrderedNumbers);

        System.out.println("Input ordered numbers");
        AVLTree avlTree2 = new AVLTree(ROOT_KEY);
        runInsertTest(avlTree2, inputOrderedNumbers);
        runSearchAllItemTest(avlTree2, inputOrderedNumbers);
        runSearch10percentTest(avlTree2, inputOrderedNumbers);
        runDelete10percentTest(avlTree2, inputOrderedNumbers);
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


    private static List<Integer> generateRandomOrderedNumbers(int count, int maxElement) {
        Set<Integer> uniqNumbers = new HashSet<>();
        while (uniqNumbers.size() < count - 1) {
            int number = new Random().nextInt(maxElement);
            if (number != (maxElement / 2)) {
                uniqNumbers.add(number);
            }
        }
        return new ArrayList<>(uniqNumbers);
    }


    private static List<Integer> generateOrderedNumbers(int count) {
        Set<Integer> uniqNumbers = new HashSet<>();
        for (int num = 0; num < count; num++) {
            if (num != (count / 2)) {
                uniqNumbers.add(num);
            }
        }
        return new ArrayList<>(uniqNumbers);
    }
}