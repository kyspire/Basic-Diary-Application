package model.comparator;


import model.Journal;

// This class implements the java comparator to compare the years from earliest to most recent
// Note: learned this code from https://www.freecodecamp.org
public class JournalYearComparator implements java.util.Comparator<Journal> {
    @Override
    public int compare(Journal a, Journal b) {
        return a.getYear() - b.getYear();
    }

}
