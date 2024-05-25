package junit.test;

import example.utils.PointChecker;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PointCheckerTest {

    @Test
    public void testMiss2quarter(){
        boolean result;
        result = PointChecker.isInArea(-2.00, 2.01, 1.00);
        assertFalse(result);
        result = PointChecker.isInArea(-3.00, 2.30, 1.5);
        assertFalse(result);
        result = PointChecker.isInArea(-1.00, 2.30, 2.0);
        assertFalse(result);
        result = PointChecker.isInArea(-4.00, 2.30, 2.5);
        assertFalse(result);
        result = PointChecker.isInArea(-3.67, 0.34, 3.00);
        assertFalse(result);

    }
    @Test
    public void testHit3quarter(){
        boolean result;
        result = PointChecker.isInArea(-0.5, -0.5, 1.0);
        assertTrue(result);
        result = PointChecker.isInArea(-1.20, -0.30, 1.5);
        assertTrue(result);
        result = PointChecker.isInArea(-1.99, -0.01, 2.0);
        assertTrue(result);
        result = PointChecker.isInArea(-0.3, -2.1, 2.5);
        assertTrue(result);
        result = PointChecker.isInArea(-0.4, -2.3, 3.0);
        assertTrue(result);
    }
    @Test
    public void testMiss3quarter(){
        boolean result;
        result = PointChecker.isInArea(-0.6, -0.6, 1.0);
        assertFalse(result);
        result = PointChecker.isInArea(-1.3, -0.5, 1.5);
        assertFalse(result);
        result = PointChecker.isInArea(-1.5, -1.6, 2.0);
        assertFalse(result);
        result = PointChecker.isInArea(-1.8, -2.0, 2.5);
        assertFalse(result);
        result = PointChecker.isInArea(-1.6, -1.5, 3.0);
        assertFalse(result);
    }
    @Test
    public void testHitXAxis(){
        boolean result;
        result = PointChecker.isInArea(-1.0, -0.0, 1.0);
        assertTrue(result);
        result = PointChecker.isInArea(-1.5, -0.0, 1.5);
        assertTrue(result);
        result = PointChecker.isInArea(-2.0, -0.0, 2.0);
        assertTrue(result);
        result = PointChecker.isInArea(-2.5, -0.0, 2.5);
        assertTrue(result);
        result = PointChecker.isInArea(-3.0, -0.0, 3.0);
        assertTrue(result);
        result = PointChecker.isInArea(1.0, -0.0, 1.0);
        assertTrue(result);
        result = PointChecker.isInArea(1.5, -0.0, 1.5);
        assertTrue(result);
        result = PointChecker.isInArea(2.0, -0.0, 2.0);
        assertTrue(result);
        result = PointChecker.isInArea(2.5, -0.0, 2.5);
        assertTrue(result);
        result = PointChecker.isInArea(3.0, -0.0, 3.0);
        assertTrue(result);
    }
    @Test
    public void testXMissAxis(){
        boolean result;
        result = PointChecker.isInArea(Double.NEGATIVE_INFINITY, -0.0, 1.0);
        assertFalse(result);
        result = PointChecker.isInArea(Double.POSITIVE_INFINITY, -0.0, 1.5);
        assertFalse(result);
        result = PointChecker.isInArea(-2.1, -0.0, 2.0);
        assertFalse(result);
        result = PointChecker.isInArea(-2.6, -0.0, 2.5);
        assertFalse(result);
        result = PointChecker.isInArea(Double.NEGATIVE_INFINITY, -0.0, 3.0);
        assertFalse(result);
        result = PointChecker.isInArea(1.1, -0.0, 1.0);
        assertFalse(result);
        result = PointChecker.isInArea(1.6, -0.0, 1.5);
        assertFalse(result);
        result = PointChecker.isInArea(Double.POSITIVE_INFINITY, -0.0, 2.0);
        assertFalse(result);
        result = PointChecker.isInArea(2.6, -0.0, 2.5);
        assertFalse(result);
        result = PointChecker.isInArea(3.0001, -0.0, 3.0);
        assertFalse(result);
    }
}
