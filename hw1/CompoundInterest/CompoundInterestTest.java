import static org.junit.Assert.*;
import org.junit.Test;

public class CompoundInterestTest {

    @Test
    public void testNumYears() {
        /** Sample assert statement for comparing integers.
        assertEquals(0, 0); */
        assertEquals(1, CompoundInterest.numYears(2023));
        assertEquals(3, CompoundInterest.numYears(2025));
        assertEquals(-1, CompoundInterest.numYears(2021));
    }

    @Test
    public void testFutureValue() {
        // When working with decimals, we often want to specify a certain
        // range of "wiggle room", or tolerance. For example, if the answer
        // is 5.04, but anything between 5.02 and 5.06 would be okay too,
        // then we can do assertEquals(5.04, answer, .02).

        // The variable below can be used when you write your tests.
        double tolerance = 0.01;
        assertEquals(12.544, CompoundInterest.futureValue(10, 12, 2024), tolerance);
        assertEquals(10.000, CompoundInterest.futureValue(10, 0, 2031), tolerance);
        assertEquals(19.8, CompoundInterest.futureValue(20, -1, 2023), tolerance);
    }

    @Test
    public void testFutureValueReal() {
        double tolerance = 0.01;
        assertEquals(11.8026496, CompoundInterest.futureValueReal(10, 12, 2024, 3), tolerance);
        assertEquals(295712.29, CompoundInterest.futureValueReal(1000000, 0, 2062, 3), tolerance);
        assertEquals(19.404, CompoundInterest.futureValueReal(20, -1, 2023, 2), tolerance);
    }


    @Test
    public void testTotalSavings() {
        double tolerance = 0.01;
        assertEquals(16550, CompoundInterest.totalSavings(5000, 2024, 10), tolerance);
        assertEquals(210, CompoundInterest.totalSavings(100,2023, 10), tolerance);
    }

    @Test
    public void testTotalSavingsReal() {
        double tolerance = 0.01;
        assertEquals(15571.895, CompoundInterest.totalSavingsReal(5000, 2024, 10, 3), tolerance);
        assertEquals(203.7, CompoundInterest.totalSavingsReal(100,2023, 10, 3), tolerance);
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(CompoundInterestTest.class));
    }
}
