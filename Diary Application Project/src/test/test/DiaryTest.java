package test;

import model.Diary;
import model.Entry;
import model.Journal;
import model.Theme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiaryTest {
    private Theme testMorning;
    private Theme testMorning2;
    private Theme testEvening;
    private Theme testNight;
    private Entry entryMorningTest;
    private Entry entryMorningTest2;
    private Entry entryEveningReflectionTest;
    private Entry entryDreamsTest;

    private Journal testJournalMorning;
    private Journal testJournalMorning2;
    private Journal testJournalEvening;
    private Journal testJournalNight;

    private Diary testDiary;

    public void setupEntries() {
        entryMorningTest = new Entry(8, 1);
        entryMorningTest.addEntry("Rise and Shine!");

        entryEveningReflectionTest = new Entry(17, 30);
        entryEveningReflectionTest.addEntry("Time to relax!");

        entryDreamsTest = new Entry(2, 15);
        entryDreamsTest.addEntry("Zzzz...");

        entryMorningTest2 = new Entry(9, 59);
        entryMorningTest2.addEntry("Breakfast.");

    }

    public void setupThemes() {
        testMorning = new Theme(entryMorningTest);
        testMorning.sortEntriesIntoThemes();

        testMorning2 = new Theme(entryMorningTest2);
        testMorning2.sortEntriesIntoThemes();

        testEvening = new Theme(entryEveningReflectionTest);
        testEvening.sortEntriesIntoThemes();

        testNight = new Theme(entryDreamsTest);
        testNight.sortEntriesIntoThemes();
    }

    public void setupJournals() {
        testJournalMorning = new Journal(testMorning);
        testJournalMorning.setTitle("My goals for today.");
        testJournalMorning.setYear(2021);
        testJournalMorning.setMonth(5);
        testJournalMorning.setDay(1);

        testJournalMorning2 = new Journal(testMorning2);
        testJournalMorning2.setTitle("My goals for today.");
        testJournalMorning2.setYear(2023);
        testJournalMorning2.setMonth(7);
        testJournalMorning2.setDay(9);

        testJournalEvening = new Journal(testEvening);
        testJournalEvening.setTitle("Reflection of my day.");
        testJournalEvening.setYear(2022);
        testJournalEvening.setMonth(2);
        testJournalEvening.setDay(20);

        testJournalNight = new Journal(testNight);
        testJournalNight.setTitle("My dreams last night!");
        testJournalNight.setYear(2023);
        testJournalNight.setMonth(7);
        testJournalNight.setDay(30);

    }

    @BeforeEach
    public void setup() {

        setupEntries();
        setupThemes();
        setupJournals();

        testDiary = new Diary();
    }

    @Test
    public void testEmptyDiary() {
        List<Journal> testEmpty = new ArrayList<>();
        assertEquals(testEmpty, testDiary.getAllJournals());
        assertEquals(0, testDiary.getAllJournals().size());
    }

    @Test
    public void testManyJournalsInDiary() {
        List<Journal> testList = new ArrayList<>();
        testList.add(testJournalEvening);
        testList.add(testJournalMorning);
        testList.add(testJournalNight);

        testDiary.addJournal(testJournalEvening);
        testDiary.addJournal(testJournalMorning);
        testDiary.addJournal(testJournalNight);

        assertEquals(testList, testDiary.getAllJournals());
        assertEquals(3, testDiary.getAllJournals().size());

    }

    @Test
    public void testSortDayGoalJournalsNoDayGoals() {
        testDiary.addJournal(testJournalNight);
        testDiary.addJournal(testJournalNight);
        testDiary.addJournal(testJournalEvening);

        List<Journal> expected = new ArrayList<>();
        assertEquals(expected, testDiary.sortDayGoalJournals());
        assertEquals(0, testDiary.sortDayGoalJournals().size());
    }


    @Test
    public void testSortDayGoalJournalsOneDayGoal() {
        testDiary.addJournal(testJournalNight);
        testDiary.addJournal(testJournalNight);
        testDiary.addJournal(testJournalEvening);
        testDiary.addJournal(testJournalMorning);

        List<Journal> expected = new ArrayList<>();
        expected.add(testJournalMorning);
        assertEquals(expected, testDiary.sortDayGoalJournals());
        assertEquals(1, testDiary.sortDayGoalJournals().size());
        assertTrue(testDiary.sortDayGoalJournals().contains(testJournalMorning));

    }

    @Test
    public void testSortDayGoalJournalsMultipleDayGoals() {
        testDiary.addJournal(testJournalMorning);
        testDiary.addJournal(testJournalNight);
        testDiary.addJournal(testJournalNight);
        testDiary.addJournal(testJournalMorning2);
        testDiary.addJournal(testJournalEvening);
        testDiary.addJournal(testJournalMorning);

        List<Journal> expected = new ArrayList<>();
        expected.add(testJournalMorning);
        expected.add(testJournalMorning2);
        expected.add(testJournalMorning);
        assertEquals(expected, testDiary.sortDayGoalJournals());
        assertEquals(3, testDiary.sortDayGoalJournals().size());
        assertTrue(testDiary.sortDayGoalJournals().contains(testJournalMorning));
        assertTrue(testDiary.sortDayGoalJournals().contains(testJournalMorning2));

    }

    @Test
    public void testSortEveningReflectionNone() {
        testDiary.addJournal(testJournalNight);
        testDiary.addJournal(testJournalMorning2);
        testDiary.addJournal(testJournalMorning);

        List<Journal> expected = new ArrayList<>();
        assertEquals(expected, testDiary.sortEveningReflectionJournals());
        assertEquals(0, testDiary.sortEveningReflectionJournals().size());

    }

    @Test
    public void testSortEveningReflectionMultiple() {
        testDiary.addJournal(testJournalNight);
        testDiary.addJournal(testJournalMorning2);
        testDiary.addJournal(testJournalEvening);
        testDiary.addJournal(testJournalMorning);
        testDiary.addJournal(testJournalEvening);

        List<Journal> expected = new ArrayList<>();
        expected.add(testJournalEvening);
        expected.add(testJournalEvening);
        assertEquals(expected, testDiary.sortEveningReflectionJournals());
        assertEquals(2, testDiary.sortEveningReflectionJournals().size());
        assertTrue(testDiary.sortEveningReflectionJournals().contains(testJournalEvening));

    }

    @Test
    public void testSortDreamsNone() {
        testDiary.addJournal(testJournalEvening);
        testDiary.addJournal(testJournalMorning2);
        testDiary.addJournal(testJournalMorning);

        List<Journal> expected = new ArrayList<>();
        assertEquals(expected, testDiary.sortDreamJournals());
        assertEquals(0, testDiary.sortDreamJournals().size());

    }

    @Test
    public void testSortDreamsMultiple() {
        testDiary.addJournal(testJournalEvening);
        testDiary.addJournal(testJournalNight);
        testDiary.addJournal(testJournalMorning2);
        testDiary.addJournal(testJournalMorning);
        testDiary.addJournal(testJournalNight);

        List<Journal> expected = new ArrayList<>();
        expected.add(testJournalNight);
        expected.add(testJournalNight);
        assertEquals(expected, testDiary.sortDreamJournals());
        assertEquals(2, testDiary.sortDreamJournals().size());

    }

    @Test
    public void testSortAllJournalsByDate() {
        testDiary.addJournal(testJournalEvening);
        testDiary.addJournal(testJournalNight);
        testDiary.addJournal(testJournalMorning2);
        testDiary.addJournal(testJournalMorning);

        List<Journal> expected = new ArrayList<>();
        expected.add(testJournalMorning);
        expected.add(testJournalEvening);
        expected.add(testJournalMorning2);
        expected.add(testJournalNight);

        assertEquals(expected, testDiary.sortAllJournalsByDate());

    }

    @Test
    public void testSortAllJournalsEmpty() {


        List<Journal> expected = new ArrayList<>();

        assertEquals(expected, testDiary.sortAllJournalsByDate());

    }





}
