import static org.junit.Assert.*;
import org.junit.Test;

public class MultiArrTest {

    @Test
    public void testMaxValue() {
        int[][] test1 = {{1, 4, 5}, {2, 3, 7}, {9, 8, 5, 0}, {10, 13}};
        int[][] test2 = {{-1, -10, 0}, {6}, {-5, -4}};
        assertEquals(13, MultiArr.maxValue(test1));
        assertEquals(6, MultiArr.maxValue(test2));
    }

    @Test
    public void testAllRowSums() {
        int[][] test1 = {{1, 4, 5}, {2, 3, 7}, {9, 8, 5, 0}, {10, 13}};
        int[][] test2 = {{-1, -10, 0}, {6}, {-5, -4}};
        int[] result1 = {10, 12, 22, 23};
        int[] result2 = {-11, 6, -9};
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(MultiArrTest.class));
    }
}
