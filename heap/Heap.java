package heap;

import java.util.ArrayList;

/**
 * @author Jacek Góraj
 */
public final class Heap {

    public final int infinity = 101;
    public final ArrayList<Integer> elements;
    public int length;

    public Heap() {
        this.elements = initElements();
        this.length = 0;
    }

    /**
     * Initialize list for keeping heap values
     *
     * @return list for elements
     */
    public ArrayList<Integer> initElements() {
        ArrayList<Integer> list = new ArrayList();
        list.add(infinity);
        return list;
    }

    /**
     * Fills heap with random values
     *
     * @param k number of values to put into heap
     */
    public void fillHeapWithValues(int k) {
        for (int i = 1; i <= k; i++) {
            elements.add(randValues());
            length++;
        }
        minHeapify(length);
    }

    public int randValues() {
        return (int) Math.floor(Math.random() * 100);
    }

    /**
     * Deletes element from heap if exists.
     *
     * @param i index of element to be removed
     * @exception IndexOutOfBoundsException if element is not found
     */
    public void delete(int i) {
        try {
            if (i > 0) {
                elements.remove(i);
                length--;
            }
        } catch (IndexOutOfBoundsException error) {
            System.out.println(error + ". Element does not exist. Won't remove it.");
        }
    }

    /**
     * Sorts heap by heapifying and removes the heap.
     *
     * @return list of sorted elements
     */
    public ArrayList<Integer> sort() {
        int iterations = length;
        ArrayList<Integer> list = new ArrayList();
        while (iterations - length != iterations) {
            int lastValue = elements.get(length);
            list.add(elements.get(1));
            elements.set(length, elements.get(1));
            elements.set(1, lastValue);
            length--;
            minHeapify(1);
        }
        return list;
    }

    /**
     * Creates minimum heap
     *
     * @param i element from which algorithm starts
     */
    public void minHeapify(int i) {
        for (int j = 1; j <= i; j++) {
            int iValue = elements.get(j);
            int leftValue = getLeftIndex(j);
            int rightValue = getRightIndex(j);
            int L = j;

            //which value is the lowest
            if (leftValue < iValue) {
                L = 2 * j;
                if (rightValue < leftValue) {
                    L = 2 * j + 1;
                }
            } else {
                if (rightValue < iValue) {
                    L = 2 * j + 1;
                }
            }

            //if son is lowest then swap and heapify
            if (L != j) {
                changeValues(L, j);
                minHeapify(L);
            }
        }
    }

    /**
     * Swaps two value in heap
     *
     * @param value1
     * @param value2
     */
    public void changeValues(int value1, int value2) {
        int helper = elements.get(value1);
        elements.set(value1, elements.get(value2));
        elements.set(value2, helper);
    }

    /**
     * Gets parent of i element
     *
     * @param i
     * @return
     */
    public int getParentIndex(int i) {
        return i / 2;
    }

    /**
     * Get left son of element i
     *
     * @param i
     * @return
     */
    public int getLeftIndex(int i) {
        if (length >= 2 * i) {
            return elements.get(2 * i);
        }
        return infinity;
    }

    /**
     * Get right son of element i
     *
     * @param i
     * @return
     */
    public int getRightIndex(int i) {
        if (length >= 2 * i + 1) {
            return elements.get(2 * i + 1);
        }
        return infinity;
    }

    /**
     * Decreases value of element i to k if k is lower than the value
     *
     * @param i
     * @param k
     */
    public void decreaseKey(int i, int k) {
        if (k < elements.get(i)) {
            elements.set(i, k);
            while ((i > 1) && (elements.get(getParentIndex(i)) > elements.get(i))) {
                int iValue = elements.get(i);
                elements.set(i, elements.get(getParentIndex(i)));
                elements.set(getParentIndex(i), iValue);
                i = getParentIndex(i);
            }
        }
    }

    /**
     * Extracts minimum value of heap and removes it
     *
     * @param heap
     * @return minimum value of heap
     */
    public int extractMin() {
        int minimumValue = minimum();
        delete(1);
        minHeapify(1);
        return minimumValue;
    }

    /**
     * Gets minimum value of heap
     *
     * @return minimum value of heap
     */
    public int minimum() {
        return elements.get(1);
    }

    /**
     * Adds element to heap
     *
     * @param value
     */
    public void insert(int value) {
        elements.add(value);
        length++;
    }

    /**
     * Prints heat to console
     *
     * @param message to show
     */
    public void show(String message) {
        System.out.println(message);
        show();
    }

    public void show() {
        System.out.print("[");
        for (int i = 1; i <= length; i++) {
            System.out.print(elements.get(i));
            if (i != length) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        System.out.println();
    }
}
