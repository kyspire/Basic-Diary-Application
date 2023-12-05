package model.comparator;

// This class implements the java comparator to compare the months from earliest to most recent
// Note: learned this code from https://www.freecodecamp.org

import model.Journal;

public class JournalMonthComparator implements java.util.Comparator<Journal> {
    @Override
    public int compare(Journal a, Journal b) {
        return a.getMonth() - b.getMonth();
    }
}
