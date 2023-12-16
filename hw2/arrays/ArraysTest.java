package arrays;

import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author Brian Chiang
 */

public class ArraysTest {

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }
    @Test
    public void testRemove() {
        int[] testArray = new int[]{1, 2, 3, 4, 5};
        assertArrayEquals(new int[]{1, 2, 3}, Arrays.remove(testArray, 3, 2));
        assertArrayEquals(new int[]{}, Arrays.remove(testArray, 0, 5));
        assertArrayEquals(new int[]{4, 5}, Arrays.remove(testArray, 0, 3));
    }

    @Test
    public void testCatenate() {
        assertArrayEquals(new int[]{}, Arrays.catenate(new int[]{}, new int[]{}));
        assertArrayEquals(new int[]{1, 2, 3}, Arrays.catenate(new int[]{}, new int[]{1, 2, 3}));
        assertArrayEquals(new int[]{1, 2, 3}, Arrays.catenate(new int[]{1}, new int[]{2, 3}));
    }
}
