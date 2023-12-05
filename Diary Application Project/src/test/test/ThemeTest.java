package test;

import model.Theme;
import model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThemeTest {
    private Theme testMorning;
    private Theme testMorning2;
    private Theme testMorning3;
    private Theme testNight4;

    private Theme testEvening;
    private Theme testEvening2;
    private Theme testEvening3;
    private Theme testNight;
    private Theme testNight2;
    private Theme testNight3;


    private Entry entryMorningTest;
    private Entry entryMorningTest2;
    private Entry entryMorningTest3;
    private Entry entryDreamTest4;
    private Entry entryEveningReflectionTest;
    private Entry entryEveningReflectionTest2;
    private Entry entryEveningReflectionTest3;
    private Entry entryDreamsTest;
    private Entry entryDreamsTest2;
    private Entry entryDreamsTest3;



    @BeforeEach
    public void setup() {

        entryMorningTest = new Entry(8, 1);
        entryMorningTest.addEntry("Rise and Shine!");

        entryMorningTest2 = new Entry(16, 32);
        entryMorningTest2.addEntry("Slightly late...");

        entryMorningTest3 = new Entry(9, 1);
        entryMorningTest3.addEntry("Get up!");




        entryEveningReflectionTest = new Entry(17, 30);
        entryEveningReflectionTest.addEntry("Time to relax!");

        entryEveningReflectionTest2 = new Entry(18, 20);
        entryEveningReflectionTest2.addEntry("Time to watch youtube!");

        entryEveningReflectionTest3 = new Entry(23, 20);
        entryEveningReflectionTest3.addEntry("Almost bed time.");

        entryDreamsTest = new Entry(0, 15);
        entryDreamsTest.addEntry("Zzzz...");

        entryDreamsTest2 = new Entry(7, 59);
        entryDreamsTest2.addEntry("Still sleeping...");

        entryDreamsTest3 = new Entry(1, 10);
        entryDreamsTest3.addEntry("zzz...");

        entryDreamTest4 = new Entry(6, 2);
        entryDreamTest4.addEntry("Snoozing");


        testMorning = new Theme(entryMorningTest);
        testMorning2 = new Theme(entryMorningTest2);
        testMorning3 = new Theme(entryMorningTest3);


        testEvening = new Theme(entryEveningReflectionTest);
        testEvening2 = new Theme(entryEveningReflectionTest2);
        testEvening3 = new Theme(entryEveningReflectionTest3);

        testNight = new Theme(entryDreamsTest);
        testNight2 = new Theme(entryDreamsTest2);
        testNight3 = new Theme(entryDreamsTest3);
        testNight4 = new Theme(entryDreamTest4);


    }

    @Test
    public void testTheme() {
        assertFalse(testNight.getDreams());
        assertFalse(testNight.getDayGoals());
        assertFalse(testNight.getEveningReflection());
    }

    @Test
    public void testSortEntriesIntoDreamAndBound0() {
        testNight.sortEntriesIntoThemes();
        assertTrue(testNight.getDreams());
        assertFalse(testNight.getDayGoals());
        assertFalse(testNight.getEveningReflection());
    }

    @Test
    public void testSortEntriesIntoDreamMid6() {
        testNight4.sortEntriesIntoThemes();
        assertTrue(testNight4.getDreams());
        assertFalse(testNight4.getDayGoals());
        assertFalse(testNight4.getEveningReflection());
    }

    @Test
    public void testSortEntriesIntoDayGoal8() {
        testMorning.sortEntriesIntoThemes();
        assertFalse(testMorning.getDreams());
        assertTrue(testMorning.getDayGoals());
        assertFalse(testMorning.getEveningReflection());
    }

    @Test
    public void testSortEntriesIntoReflection() {
        testEvening.sortEntriesIntoThemes();
        assertFalse(testEvening.getDreams());
        assertFalse(testEvening.getDayGoals());
        assertTrue(testEvening.getEveningReflection());
    }

    @Test
    public void testSortEntriesIntoDreamsBoundary7() {
        testNight2.sortEntriesIntoThemes();
        assertTrue(testNight2.getDreams());
        assertFalse(testNight2.getDayGoals());
        assertFalse(testNight2.getEveningReflection());

    }
    @Test
    public void testSortEntriesIntoDreamsBoundary1() {
        testNight3.sortEntriesIntoThemes();
        assertTrue(testNight3.getDreams());
        assertFalse(testNight3.getDayGoals());
        assertFalse(testNight3.getEveningReflection());

    }

    @Test
    public void testSortEntriesIntoDayGoalBoundary16() {
        testMorning2.sortEntriesIntoThemes();
        assertFalse(testMorning2.getDreams());
        assertTrue(testMorning2.getDayGoals());
        assertFalse(testMorning2.getEveningReflection());

    }



    @Test
    public void testSortEntriesIntoDayGoalBoundary9() {
        testMorning3.sortEntriesIntoThemes();
        assertFalse(testMorning3.getDreams());
        assertTrue(testMorning3.getDayGoals());
        assertFalse(testMorning3.getEveningReflection());

    }
    @Test
    public void testSortEntriesEveningReflectionBoundary() {
        testEvening2.sortEntriesIntoThemes();
        assertFalse(testEvening2.getDreams());
        assertFalse(testEvening2.getDayGoals());
        assertTrue(testEvening2.getEveningReflection());

    }

    @Test
    public void testSortEntriesEveningReflectionBound23() {
        testEvening3.sortEntriesIntoThemes();
        assertFalse(testEvening3.getDreams());
        assertFalse(testEvening3.getDayGoals());
        assertTrue(testEvening3.getEveningReflection());

    }


}
