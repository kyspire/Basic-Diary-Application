package model.comparator;

import model.Journal;

// This class implements the java comparator to compare the days from earliest to most recent
// Note: learned this code from https://www.freecodecamp.org
public class JournalDayComparator implements java.util.Comparator<Journal> {
    @Override
    public int compare(Journal a, Journal b) {
        return a.getDay() - b.getDay();
    }
}