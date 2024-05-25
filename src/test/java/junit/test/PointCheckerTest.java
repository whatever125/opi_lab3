package junit.test;

import example.utils.PointChecker;
import org.junit.jupiter.api.*;
import java.lang.Math;

import static org.junit.jupiter.api.Assertions.*;

public class PointCheckerTest {
    @Test
    public void testHit1quarter() {
        boolean result;
        result = PointChecker.isInArea(0.5,0.5, 1.0);
        assertTrue(result);
        result = PointChecker.isInArea(0.5,1.0, 1.5);
        assertTrue(result);
        result = PointChecker.isInArea(1.0,1.0, 2.0);
        assertTrue(result);
        result = PointChecker.isInArea(1.0,1.0, 2.5);
        assertTrue(result);
        result = PointChecker.isInArea(3 / Math.sqrt(2),3 / Math.sqrt(2), 3.0);
        assertTrue(result);
    }

    @Test
    public void testMiss1quarter() {
        boolean result;
        result = PointChecker.isInArea(1 / Math.sqrt(2) + 1e-10,1 / Math.sqrt(2), 1.0);
        assertFalse(result);
        result = PointChecker.isInArea(100.0,1.0, 1.5);
        assertFalse(result);
        result = PointChecker.isInArea(Double.POSITIVE_INFINITY,2.0, 2.0);
        assertFalse(result);
        result = PointChecker.isInArea(1.0,2.5, 2.5);
        assertFalse(result);
        result = PointChecker.isInArea(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 3.0);
        assertFalse(result);
    }

    @Test
    public void testHit4quarter() {
        boolean result;
        result = PointChecker.isInArea(0.5,-0.5, 1.0);
        assertTrue(result);
        result = PointChecker.isInArea(1.5,-0.75, 1.5);
        assertTrue(result);
        result = PointChecker.isInArea(1e-10,-1.0, 2.0);
        assertTrue(result);
        result = PointChecker.isInArea(2.5,-1.0, 2.5);
        assertTrue(result);
        result = PointChecker.isInArea(3.0, -1.5, 3.0);
        assertTrue(result);
    }

    @Test
    public void testMiss4quarter() {
        boolean result;
        result = PointChecker.isInArea(0.5,-0.6, 1.0);
        assertFalse(result);
        result = PointChecker.isInArea(1.5,-0.7501, 1.5);
        assertFalse(result);
        result = PointChecker.isInArea(Double.POSITIVE_INFINITY,-1.0, 2.0);
        assertFalse(result);
        result = PointChecker.isInArea(1e-10,-1.25 - 1e-10, 2.5);
        assertFalse(result);
        result = PointChecker.isInArea(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 3.0);
        assertFalse(result);
    }

    @Test
    public void testHitYAxis() {
        boolean result;
        result = PointChecker.isInArea(0.0,0.0, 1.0);
        assertTrue(result);
        result = PointChecker.isInArea(0.0,1.5, 1.5);
        assertTrue(result);
        result = PointChecker.isInArea(0.0,-1.0, 2.0);
        assertTrue(result);
        result = PointChecker.isInArea(0.0,1.0, 2.5);
        assertTrue(result);
        result = PointChecker.isInArea(0.0, -1.5, 3.0);
        assertTrue(result);
    }

    @Test
    public void testMissYAxis() {
        boolean result;
        result = PointChecker.isInArea(0.0,1.0 + 1e10, 1.0);
        assertFalse(result);
        result = PointChecker.isInArea(0.0, Double.POSITIVE_INFINITY, 1.5);
        assertFalse(result);
        result = PointChecker.isInArea(0.0,-2.0 - 1e10, 2.0);
        assertFalse(result);
        result = PointChecker.isInArea(0.0,10.0, 2.5);
        assertFalse(result);
        result = PointChecker.isInArea(0.0, Double.NEGATIVE_INFINITY, 3.0);
        assertFalse(result);
    }
}
