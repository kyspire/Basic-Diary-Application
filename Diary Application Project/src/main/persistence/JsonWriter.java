package persistence;

import model.Diary;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


// referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // MODIFIES: this
    // EFFECTS: writes JSON representation of Diary to file
    public void write(Diary dy) {
        JSONObject json = dy.toJson();
        saveToFile(json.toString(TAB));
    }

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
