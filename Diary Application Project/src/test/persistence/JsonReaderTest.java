package persistence;


import model.Diary;
import model.Journal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNoFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            Diary dy = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDiary() {
        JsonReader reader = new JsonReader("./data/DiaryJsonReaderTestEmptyDiary.md");
        try {
            Diary dy = reader.read();
            assertEquals(0, dy.getAllJournals().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderRegularDiary() {
        JsonReader reader = new JsonReader("./data/DiaryJsonReaderTestRegularDiary.md");
        try {
            Diary dy = reader.read();
            List<Journal> journals = dy.getAllJournals();
            assertEquals(2, journals.size());
            checkJournal("Test1", 2023, 9, 26, journals.get(0));
            checkTheme(false, true, false, journals.get(0));
            checkEntry("Test example 1.", 10, 23, journals.get(0));

            checkJournal("Test2", 2023, 9, 26, journals.get(1));
            checkTheme(false, true, false, journals.get(1));
            checkEntry("Test 2!", 15, 0, journals.get(1));


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
