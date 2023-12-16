package image;

import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author Brian Chiang
 */

public class MatrixUtilsTest {
    /** FIXME
     */
    double[] rowOne = new double[]{1000000, 1000000, 1000000, 1000000};
    double[] rowTwo = new double[]{1000000, 75990, 30003, 1000000};
    double[] rowThree = new double[]{1000000, 30002, 103046, 1000000};
    double[] rowFour = new double[]{1000000, 29515, 38273, 1000000};
    double[] rowFive = new double[]{1000000, 73403, 35399, 1000000};
    double[] rowSix = new double[]{1000000, 1000000, 1000000, 1000000};
    double[][] testMain = new double[][]{rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix};

    @Test
    public void testAccumulateVertical() {
        double[] rowone = {1000000, 1000000, 1000000, 1000000};
        double[] rowtwo = {1000000, 75990, 30003, 1000000};
        double[][] testOne = new double[][]{rowone, rowtwo};

        double[] expectedone = {1000000, 1000000, 1000000, 1000000};
        double[] expectedtwo = {2000000, 1075990, 1030003, 2000000};
        double[][] expectedOne = new double[][]{expectedone, expectedtwo};

        assertArrayEquals(expectedOne, MatrixUtils.accumulateVertical(testOne));

        double[] a = new double[]{1000000, 1000000, 1000000, 1000000};
        double[] b = {2000000, 1075990, 1030003, 2000000};
        double[] c = {2075990, 1060005, 1133049, 2030003};
        double[] d = {2060005, 1089520, 1098278, 2133049};
        double[] e = {2089520, 1162923, 1124919, 2098278};
        double[] f = {2162923, 2124919, 2124919, 2124919};
        double[][] expectedMain = new double[][]{a, b, c, d, e, f};

        assertArrayEquals(expectedMain, MatrixUtils.accumulateVertical(testMain));
    }

    @Test
    public void testAccumulate() {
        double[][] test = new double[][]{{}};
        double[][] expect = new double[][]{{}};

        assertArrayEquals(expect, MatrixUtils.accumulate(test, MatrixUtils.Orientation.VERTICAL));

        assertArrayEquals(expect, MatrixUtils.accumulate(test, MatrixUtils.Orientation.HORIZONTAL));

        double[] one = new double[]{1000000, 1000000, 1000000, 1000000};
        double[] two = {2000000, 1075990, 1030003, 2000000};
        double[] three = {2075990, 1060005, 1133049, 2030003};
        double[] four = {2060005, 1089520, 1098278, 2133049};
        double[] five = {2089520, 1162923, 1124919, 2098278};
        double[] six = {2162923, 2124919, 2124919, 2124919};
        double[][] expM = new double[][]{one, two, three, four, five, six};

        double[][] hMain = MatrixUtils.transpose(testMain); 

        assertArrayEquals(expM, MatrixUtils.accumulate(hMain, MatrixUtils.Orientation.HORIZONTAL));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MatrixUtilsTest.class));
    }
}
