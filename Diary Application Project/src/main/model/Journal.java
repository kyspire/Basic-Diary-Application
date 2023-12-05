package model;

import org.json.JSONObject;
import persistence.Writable;

// The journal class creates a Journal with a title, date, and themed entry.
// This class is mainly used to package elements together into a Journal.
public class Journal implements Writable {



    private Entry myEntry;

    private Theme entryTheme;

    private String title;

    private int month;

    private int day;

    private int year;

    // REQUIRES: month must be an integer between 1-12
    //           day must be between 1-31 depending on the month
    //           year must be > 0 and reasonable (like 2023)
    // MODIFIES: this
    // EFFECTS: instantiates the Journal with default parameters
    public Journal(Theme t) {
        this.myEntry = t.getEntry();
        this.entryTheme = t;
        this.title = "";
        this.month = 1;
        this.day = 1;
        this.year = 1;

    }

    public void setTitle(String s) {
        this.title = s;
    }

    public void setMonth(int month) {
        this.month = month;

    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Entry getMyEntry() {
        return this.myEntry;
    }

    public Theme getEntryTheme() {
        return this.entryTheme;
    }

    public String getTitle() {
        return this.title;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    // EFFECTS: stores the elements of Journal as a Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("journal", entryTheme.toJson());
        json.put("title", title);
        json.put("month", month);
        json.put("day", day);
        json.put("year", year);
        return json;
    }


}