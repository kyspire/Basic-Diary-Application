package persistence;

import model.Diary;
import model.Entry;
import model.Journal;
import model.Theme;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
public class JsonReader {

    private String source;

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Diary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDiary(jsonObject);
    }

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // EFFECTS: parses Diary from JSON object and returns it
    private Diary parseDiary(JSONObject jsonObject) {
        Diary dy = new Diary();
        addJournals(dy, jsonObject);
        return dy;
    }

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // MODIFIES: dy
    // EFFECTS: parses journals from JSON object and adds them to Diary
    private void addJournals(Diary dy, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("journals");
        for (Object json : jsonArray) {
            JSONObject nextJournal = (JSONObject) json;
            addJournal(dy, nextJournal);
        }
    }


    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // MODIFIES: dy
    // EFFECTS: parses Journal from JSON object and adds it to Diary
    private void addJournal(Diary dy, JSONObject jsonObject) {

        JSONObject theJournal = jsonObject.getJSONObject("journal");

        JSONObject theTheme = theJournal.getJSONObject("theme");
        String entry = theTheme.getString("entry");
        int hours = theTheme.getInt("hours");
        int minutes = theTheme.getInt("minutes");

        Entry e = new Entry(hours, minutes);
        e.addEntry(entry);
        Theme t = new Theme(e);
        t.sortEntriesIntoThemes();
        Journal j = new Journal(t);
        j.setTitle(jsonObject.getString("title"));
        j.setYear(jsonObject.getInt("year"));
        j.setDay(jsonObject.getInt("day"));
        j.setMonth(jsonObject.getInt("month"));

        dy.addJournal(j);
    }
}
