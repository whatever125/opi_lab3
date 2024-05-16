package junit.test;

import example.utils.PointChecker;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PointCheckerTest {
    @Test
    public void testHit() {
        boolean result;
        result = PointChecker.isInArea(2.0,0.0, 2.0 );
        assertTrue(result);
        result = PointChecker.isInArea(-2.0,0.0, 2.0 );
        assertTrue(result);
    }

    @Test
    public void testMiss() {
        boolean result;
        result = PointChecker.isInArea(10.0,0.0, 2.0 );
        assertFalse(result);
        result = PointChecker.isInArea(-20.0,10.0, 3.0 );
        assertFalse(result);
    }
}
