package lists;

import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *
 *  @author Brian Chiang
 */

public class ListsTest {

    @Test
    public void basicRunsTest() {
        IntList input = IntList.list(1, 2, 3, 1, 2);
        IntList run1 = IntList.list(1, 2, 3);
        IntList run2 = IntList.list(1, 2);
        IntListList result = IntListList.list(run1, run2);
        assertEquals(input, result);
    }
    @Test
    public void testNaturalRuns() {
        IntList testOne = IntList.list(new int[] {1, 3, 5, 7, 9, 11, 13, 15});
        IntListList expectedOne = IntListList.list(new int[][]{{1, 3, 5, 7, 9, 11, 13, 15}});
        assertEquals(expectedOne, Lists.naturalRuns(testOne));

        IntList testTwo = IntList.list(new int[] {2});
        IntListList expectedTwo = IntListList.list(new int[][] {{2}});
        assertEquals(expectedTwo, Lists.naturalRuns(testTwo));

        IntList testThree = IntList.list(new int[] {5, 4, 3, 2, 1});
        IntListList expectedThree = IntListList.list(new int[][] {{5}, {4}, {3}, {2}, {1}});
        assertEquals(expectedThree, Lists.naturalRuns(testThree));

        IntList testFour = IntList.list(new int[] {1, 2, 3, 2, 1, 0});
        IntListList expectedFour = IntListList.list(new int[][] {{1, 2, 3}, {2}, {1}, {0}});
        assertEquals(expectedFour, Lists.naturalRuns(testFour));

        IntList testFive = IntList.list(new int[] {5, 4, 1, 2, 3});
        IntListList expectedFive = IntListList.list(new int[][] {{5}, {4}, {1, 2, 3}});
        assertEquals(expectedFive, Lists.naturalRuns(testFive));

        IntList testSix = IntList.list(new int[] {});
        IntListList expectedSix = IntListList.list(new int[][] {{}});
        assertEquals(expectedSix, Lists.naturalRuns(testSix));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ListsTest.class));
    }
}
