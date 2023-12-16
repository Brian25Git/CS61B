package lists;

/* NOTE: The file Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2, Problem #1. */

import image.In;

/** List problem.
 *  @author Brian Chiang
 */
class Lists {


    /* B. */
    /** Return the list of lists formed by breaking up L into "natural runs":
     *  that is, maximal strictly ascending sublists, in the same order as
     *  the original.  For example, if L is (1, 3, 7, 5, 4, 6, 9, 10, 10, 11),
     *  then result is the four-item list
     *            ((1, 3, 7), (5), (4, 6, 9, 10), (10, 11)).
     *  Destructive: creates no new IntList items, and may modify the
     *  original list pointed to by L. */
    static IntListList naturalRuns(IntList L) {

        // Feel free to ignore this skeleton and start fresh
        // if that's more your vibe

        int counter = 0;
        IntList i = L;
        IntListList result = new IntListList();
        IntListList tracker = result;
        if (L == null || L.tail == null) {
            result = new IntListList(L, null);
        } else {
            while (L != null) {
                while (i.tail.tail != null) {
                    if (i.head >= i.tail.head) {
                        if (result.head == null) {
                            result = new IntListList(L, result);
                            tracker = result;
                        } else {
                            tracker.tail = new IntListList(L, null);
                            tracker = tracker.tail;
                        }
                        L = i.tail;
                        i.tail = null;
                        i = L;
                        counter++;
                    } else {
                        i = i.tail;
                    }
                }
                if (i.head >= i.tail.head) {
                    tracker.tail = new IntListList(L, null);
                    tracker = tracker.tail;
                    L = i.tail;
                    i.tail = null;
                    i = L;
                }
                if (counter == 0) {
                    tracker = new IntListList(L, null);
                    result = tracker;
                } else {
                    tracker.tail = new IntListList(L, null);
                }
                L = null;

            }
        }
        return result;
    }

    /** Same as above, but a recursive version.
     *
     *  If you choose to go with the recursive skeleton, make sure to change the
     *  name from naturalRunsRecursive to naturalRuns, and delete the iterative
     *  skeleton. Otherwise, our autograder will grade the iterative version above.
     * */
    static IntListList naturalRunsRecursive(IntList L) {
        if (L == null) {
            return null; // Should you replace me?
        } else {
            // FIXME: Add some lines here...
            //
            //
            // return new IntListList(L, rest); <- You might want this return statement...
            //                                    but how should you define "rest"?
            return null; // FIXME: REPLACE ME!
        }
    }

    /** Recursive helper method, if you'd like.
     *
     *  Assuming L is not null, returns the last element of L in which
     *  the value of L.head increases from the previous element (the
     *  end of the list if L is entirely in strictly ascending order).  */
    private static IntList endOfRun(IntList L) {
        while (L.tail != null && L.tail.head > L.head) {
            L = L.tail;
        }
        return L;
    }
}
