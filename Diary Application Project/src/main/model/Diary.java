package model;

import model.comparator.JournalDayComparator;
import model.comparator.JournalMonthComparator;
import model.comparator.JournalYearComparator;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// The Diary class stories an arbitrary list of Journal entries.
// If the user wants, they can sort the list of Journals by time or theme.
public class Diary implements Writable {

    private List<Journal> allJournals;

    // MODIFIES: this
    // EFFECTS: Instantiates an empty list of Journals
    public Diary() {
        this.allJournals = new ArrayList<>();
    }

    public void addJournal(Journal j) {
        this.allJournals.add(j);
        EventLog.getInstance().logEvent(new Event("Added New Journal To Diary."));
    }

    public List<Journal> getAllJournals() {
        List<Journal> all;
        all = this.allJournals;
        EventLog.getInstance().logEvent(new Event("Displayed All Current Journals!"));
        return all;

    }

    // EFFECTS: Takes the total list of Journals and outputs the journals themed as a day goal.
    public List<Journal> sortDayGoalJournals() {
        List<Journal> dayGoalJournals = new ArrayList<>();
        for (Journal j : this.allJournals) {
            Theme dayState = j.getEntryTheme();
            if (dayState.getDayGoals()) {
                dayGoalJournals.add(j);
            }

        }
        EventLog.getInstance().logEvent(new Event("Displayed subset of Journals based on Day Goal Theme"));
        return dayGoalJournals;
    }


    // EFFECTS: Takes the total list of Journals and outputs the journals themed as an evening reflection.
    public List<Journal> sortEveningReflectionJournals() {
        List<Journal> eveningReflectionJournals = new ArrayList<>();
        for (Journal j : this.allJournals) {
            Theme eveningReflection = j.getEntryTheme();
            if (eveningReflection.getEveningReflection()) {
                eveningReflectionJournals.add(j);
            }
        }
        EventLog.getInstance().logEvent(new Event("Displayed subset of Journals based on Reflection Theme"));
        return eveningReflectionJournals;
    }

    // EFFECTS: Takes the total list of Journals and outputs the journals themed as a dream.
    public List<Journal> sortDreamJournals() {
        List<Journal> dreamJournals = new ArrayList<>();
        for (Journal j : this.allJournals) {
            Theme dreams = j.getEntryTheme();
            if (dreams.getDreams()) {
                dreamJournals.add(j);
            }
        }
        EventLog.getInstance().logEvent(new Event("Displayed subset of Journals based on Dream Theme"));
        return dreamJournals;
    }

    // EFFECTS: sorts Journals from earliest entry to most recent entry based on year and month.
    public List<Journal> sortAllJournalsByDate() {
        List<Journal> journals = this.allJournals;
        Collections.sort(journals, new JournalDayComparator());
        Collections.sort(journals, new JournalMonthComparator());
        Collections.sort(journals, new JournalYearComparator());
        EventLog.getInstance().logEvent(new Event("Displayed subset of Journals based on Journal Date"));
        return journals;

    }

    // EFFECTS: returns Diary as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("journals", journalsToJson());
        return json;
    }

    // EFFECTS: returns journals in this Diary as a JSON array
    private JSONArray journalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Journal j : allJournals) {
            jsonArray.put(j.toJson());
        }

        return jsonArray;
    }





}
