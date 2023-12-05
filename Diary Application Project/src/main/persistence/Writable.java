package persistence;

import org.json.JSONObject;


// referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
public interface Writable {
    //EFFECTS: returns this as a JSON object.
    JSONObject toJson();
}
