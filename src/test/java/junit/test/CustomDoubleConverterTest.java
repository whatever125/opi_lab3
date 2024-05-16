package junit.test;

import example.utils.CustomDoubleConverter;
import jakarta.faces.convert.ConverterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomDoubleConverterTest {
    @Test
    public void getAsObject() {
        final CustomDoubleConverter converter = new CustomDoubleConverter();

        assertEquals(1.0, converter.getAsObject(null, null, "1.0"));
        assertEquals(1.0, converter.getAsObject(null, null, "+1.0"));
        assertEquals(0.0, converter.getAsObject(null, null, "0.0"));
        assertEquals(-1.0, converter.getAsObject(null, null, "-1.0"));
        assertEquals(-1.0, converter.getAsObject(null, null, "-1,0"));
        assertEquals(0, converter.getAsObject(null, null, "+000000,00000"));

        assertThrows(ConverterException.class, () -> converter.getAsObject(null, null, "abc"));
        assertThrows(ConverterException.class, () -> converter.getAsObject(null, null, "null"));
        assertThrows(ConverterException.class, () -> converter.getAsObject(null, null, null));
        assertThrows(ConverterException.class, () -> converter.getAsObject(null, null, ""));
        assertThrows(ConverterException.class, () -> converter.getAsObject(null, null, "-"));
        assertThrows(ConverterException.class, () -> converter.getAsObject(null, null, "2.O"));
    }

    @Test
    public void getAsString() {
        final CustomDoubleConverter converter = new CustomDoubleConverter();

        assertEquals("", converter.getAsString(null, null, null));

        assertEquals("-1.0", converter.getAsString(null, null, -1.0));
        assertEquals("1.0", converter.getAsString(null, null, +1.0));
        assertEquals("1.0", converter.getAsString(null, null, 1.0));
    }
}