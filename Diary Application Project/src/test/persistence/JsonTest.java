package persistence;

import model.Journal;

import static org.junit.jupiter.api.Assertions.assertEquals;

// NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
public class JsonTest {

    protected void checkJournal(String title, int year, int month, int day, Journal j) {
        assertEquals(title, j.getTitle());
        assertEquals(year, j.getYear());
        assertEquals(month, j.getMonth());
        assertEquals(day, j.getDay());
    }

    protected void checkTheme(Boolean reflect, Boolean dayGoal, Boolean dream, Journal j) {
        assertEquals(reflect, j.getEntryTheme().getEveningReflection());
        assertEquals(dayGoal, j.getEntryTheme().getDayGoals());
        assertEquals(dream, j.getEntryTheme().getDreams());
    }

    protected void checkEntry(String entry, int hours, int minutes, Journal j) {
        assertEquals(entry, j.getEntryTheme().getEntry().getEntry());
        assertEquals(hours, j.getEntryTheme().getEntry().getHours());
        assertEquals(minutes, j.getEntryTheme().getEntry().getMinutes());
    }
}
