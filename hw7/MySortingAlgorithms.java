import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Note that every sorting algorithm takes in an argument k. The sorting 
 * algorithm should sort the array from index 0 to k. This argument could
 * be useful for some of your sorts.
 *
 * Class containing all the sorting algorithms from 61B to date.
 *
 * You may add any number instance variables and instance methods
 * to your Sorting Algorithm classes.
 *
 * You may also override the empty no-argument constructor, but please
 * only use the no-argument constructor for each of the Sorting
 * Algorithms, as that is what will be used for testing.
 *
 * Feel free to use any resources out there to write each sort,
 * including existing implementations on the web or from DSIJ.
 *
 * All implementations except Counting Sort adopted from Algorithms,
 * a textbook by Kevin Wayne and Bob Sedgewick. Their code does not
 * obey our style conventions.
 */
public class MySortingAlgorithms {

    /**
     * Java's Sorting Algorithm. Java uses Quicksort for ints.
     */
    public static class JavaSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            Arrays.sort(array, 0, k);
        }

        @Override
        public String toString() {
            return "Built-In Sort (uses quicksort for ints)";
        }
    }

    /** Insertion sorts the provided data. */
    public static class InsertionSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            int ele, a;
            for (int i = 1; i < k; i++) {
                ele = array[i];
                a = i - 1;
                while (a >= 0 && array[a] > ele) {
                    array[a + 1] = array[a];
                    a--;
                }
                array[a + 1] = ele;
            }
        }

        @Override
        public String toString() {
            return "Insertion Sort";
        }
    }

    /**
     * Selection Sort for small K should be more efficient
     * than for larger K. You do not need to use a heap,
     * though if you want an extra challenge, feel free to
     * implement a heap based selection sort (i.e. heapsort).
     */
    public static class SelectionSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            for (int i = 0; i < k; i++) {
                int minIndex = min(array, k, i);
                swap(array, i, minIndex);
            }
        }

        @Override
        public String toString() {
            return "Selection Sort";
        }

        /** Returns minimum element's index from J to K in ARRAY.*/
        private int min(int[] array, int k, int j) {
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int in = j; in < k; in++) {
                if (array[in] < min) {
                    min = array[in];
                    index = in;
                }
            }
            return index;
        }
    }

    /** Your mergesort implementation. An iterative merge
      * method is easier to write than a recursive merge method.
      * Note: I'm only talking about the merge operation here,
      * not the entire algorithm, which is easier to do recursively.
      */
    public static class MergeSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            sort(array, 0, k - 1);
        }

        /** Sorts ARRAY from L to R. */
        public void sort(int[] array, int l, int r) {
            if (l < r) {
                int mid = Math.floorDiv(l + r, 2);
                sort(array, l, mid);
                sort(array, mid + 1, r);
                merge(array, l, mid, r);
            }
        }

        /** Merges two arrays. */
        private void merge(int[] array, int l, int m, int r) {
            int len1 = m - l + 1;
            int len2 = r - m;

            int[] L = new int[len1];
            int[] R = new int[len2];

            for (int i = 0; i < len1; i++) {
                L[i] = array[l + i];
            }
            for (int j = 0; j < len2; j++) {
                R[j] = array[m + 1 + j];
            }

            int i = 0, j = 0;
            int k = l;
            while (i < len1 && j < len2) {
                if (L[i] <= R[j]) {
                    array[k] = L[i];
                    i++;
                } else {
                    array[k] = R[j];
                    j++;
                }
                k++;
            }

            for (; i < len1; i++, k++) {
                array[k] = L[i];
            }
            for (; j < len2; j++, k++) {
                array[k] = R[j];
            }
        }

        @Override
        public String toString() {
            return "Merge Sort";
        }
    }

    /**
     * Your Counting Sort implementation.
     * You should create a count array that is the
     * same size as the value of the max digit in the array.
     */
    public static class CountingSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
        }

        // may want to add additional methods

        @Override
        public String toString() {
            return "Counting Sort";
        }
    }

    /** Your Heapsort implementation.
     */
    public static class HeapSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
        }

        @Override
        public String toString() {
            return "Heap Sort";
        }
    }

    /** Your Quicksort implementation.
     */
    public static class QuickSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
        }

        @Override
        public String toString() {
            return "Quicksort";
        }
    }

    /* For radix sorts, treat the integers as strings of x-bit numbers.  For
     * example, if you take x to be 2, then the least significant digit of
     * 25 (= 11001 in binary) would be 1 (01), the next least would be 2 (10)
     * and the third least would be 1.  The rest would be 0.  You can even take
     * x to be 1 and sort one bit at a time.  It might be interesting to see
     * how the times compare for various values of x. */

    /**
     * LSD Sort implementation.
     */
    public static class LSDSort implements SortingAlgorithm {
        @Override
        public void sort(int[] a, int k) {
            int longest = longest(a, k);
            Queue<Integer>[] buckets = new Queue[10];
            for (int i = 0; i < 10; i++) {
                buckets[i] = new LinkedList<>();
            }
            int f = 1;
            while (longest != 0) {
                for (int i = 0; i < k; i++) {
                    int item = a[i];
                    int bucket = Math.floorDiv(item, f) % 10;
                    buckets[bucket].add(item);
                }
                f *= 10;
                longest--;
                int index = 0;
                for (Queue<Integer> bucket : buckets) {
                    while (!bucket.isEmpty()) {
                        a[index++] = bucket.remove();
                    }
                }
            }
        }

        /** Returns length of the longest term of A to k. */
        private int longest(int[] a, int k) {
            int longest = 0;
            for (int i = 0; i < k; i++) {
                int f = 10;
                int length = 1;
                while (Math.floorDiv(a[i], f) != 0) {
                    length++;
                    f *= 10;
                }
                if (length > longest) {
                    longest = length;
                }
            }
            return longest;
        }

        @Override
        public String toString() {
            return "LSD Sort";
        }
    }

    /**
     * MSD Sort implementation.
     */
    public static class MSDSort implements SortingAlgorithm {
        @Override
        public void sort(int[] a, int k) {
        }

        @Override
        public String toString() {
            return "MSD Sort";
        }
    }

    /** Exchange A[I] and A[J]. */
    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
