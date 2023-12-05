package model;


import org.json.JSONObject;
import persistence.Writable;

// The entry class sets up an entry in which a user inputs the time
// as hours and minutes along with their entry
public class Entry implements Writable {


    private String entry;
    private int hours;
    private int minutes;

    // REQUIRES: hours must be an integer between 00-23
    //           minutes must be an integer between 00 and 59
    // MODIFIES: this
    // EFFECTS: creates a new entry with a date, time, and entry
    public Entry(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    //MODIFIES: this
    //EFFECTS: adds an entry
    public void addEntry(String entry) {
        this.entry = entry;


    }

    public int getHours() {
        return this.hours;
    }

    public int getMinutes() {
        return this.minutes;
    }


    public String getEntry() {
        return this.entry;
    }

    // EFFECTS: stores the elements of entry as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("hours", hours);
        json.put("minutes", minutes);
        json.put("entry", entry);
        return json;
    }


}
