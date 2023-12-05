package test;

import model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EntryTest {
    private Entry test;

    @BeforeEach
    public void setup() {
        test = new Entry(1,1);

    }

    @Test
    public void testEntry() {
        assertEquals(1, test.getHours());
        assertEquals(1, test.getMinutes());
    }

    @Test
    public void testAddEntryEmpty() {
        test.addEntry("");
        assertEquals("", test.getEntry());
    }

    @Test
    public void testAddEntry() {
        test.addEntry("Hello World! This is my first entry.");
        assertEquals("Hello World! This is my first entry.", test.getEntry());
    }

}
