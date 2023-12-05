package model;

import org.json.JSONObject;
import persistence.Writable;

// The Theme class takes an entry and classifies it as a Day-Goal entry,
// an evening reflection entry, or a dream. This is represented through booleans.
public class Theme implements Writable {
    private boolean dayGoals;
    private boolean eveningReflection;

    private boolean dream;

    private Entry entry;
    // REQUIRES: an entry with a valid time in hours and minutes
    // MODIFIES: this
    // EFFECTS: creates a new theme element that sets all theme states to false.

    public Theme(Entry entry) {
        this.entry = entry;
        dayGoals = false;
        eveningReflection = false;
        dream = false;


    }

    // REQUIRES: e.GetHours() >= 0 && e.GetHours() < 24
    // MODIFIES: this
    // EFFECTS: sets the theme of the entry to one of: Day Goals,
    //          evening reflection, or dream based on time of entry.
    public void sortEntriesIntoThemes() {
        int time = this.entry.getHours();
        if (time < 8) {
            this.dream = true;
            this.dayGoals = false;
            this.eveningReflection = false;
        } else if (time < 17) {
            this.dayGoals = true;
            this.dream = false;
            this.eveningReflection = false;
        } else {
            this.eveningReflection = true;
            this.dream = false;
            this.dayGoals = false;
        }

    }

    public boolean getDayGoals() {
        return this.dayGoals;
    }


    public boolean getDreams() {
        return this.dream;
    }

    public boolean getEveningReflection() {
        return this.eveningReflection;
    }

    public Entry getEntry() {
        return this.entry;
    }

    // EFFECTS: returns the elements of theme as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("theme", entry.toJson());
        json.put("boolDayGoal", dayGoals);
        json.put("boolReflect", eveningReflection);
        json.put("boolDream", dream);
        return json;
    }

}
