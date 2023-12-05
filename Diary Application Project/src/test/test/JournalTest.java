package test;

import model.Entry;
import model.Journal;
import model.Theme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JournalTest {
    private Journal testJournal;
    private Theme testTheme;
    private Entry testEntry;

    @BeforeEach
    public void setup() {
        testEntry = new Entry(1,1);
        testEntry.addEntry("Hello World");
        testTheme = new Theme(testEntry);
        testJournal = new Journal(testTheme);
    }

    @Test
    public void testJournal() {
        assertEquals(testEntry, testJournal.getMyEntry());
        assertEquals(testTheme, testJournal.getEntryTheme());
        assertEquals(1, testJournal.getYear());
        assertEquals(1, testJournal.getMonth());
        assertEquals(1, testJournal.getDay());
        assertEquals("", testJournal.getTitle());

    }
}
