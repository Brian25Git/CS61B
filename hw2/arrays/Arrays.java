package arrays;

/* NOTE: The file Arrays/Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2 */

/** Array utilities.
 *  @author Brian Chiang
 */
class Arrays {

    /* C1. */
    /** Returns a new array consisting of the elements of A followed by the
     *  the elements of B. */
    static int[] catenate(int[] A, int[] B) {
        int[] answer = new int[A.length + B.length];
        for (int i = 0; i < A.length + B.length; i++) {
            if (i < A.length) {
                answer[i] = A[i];
            } else {
                answer[i] = B[i - A.length];
            }
        }
        return answer;
    }

    /* C2. */
    /** Returns the array formed by removing LEN items from A,
     *  beginning with item #START. If the start + len is out of bounds for our array, you
     *  can return null.
     *  Example: if A is [0, 1, 2, 3] and start is 1 and len is 2, the
     *  result should be [0, 3]. */
    static int[] remove(int[] A, int start, int len) {
        if (start + len > A.length) {
            return null;
        }
        int[] result = new int[A.length - len];
        for (int i = 0; i < start; i++) {
            result[i] = A[i];
        }
        for (int j = len + start; j < A.length; j++)
            result[j - len] = A[j];
        return result;
    }
}
