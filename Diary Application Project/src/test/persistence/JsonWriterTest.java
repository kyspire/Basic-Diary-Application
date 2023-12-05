package persistence;


import model.Diary;
import model.Entry;
import model.Theme;
import model.Journal;
import org.junit.jupiter.api.Test;
import java.util.List;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterErrorFile() {
        try {
            Diary dy = new Diary();
            JsonWriter writer = new JsonWriter("./data/my\0DIANIWAMDA.md");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDiary() {
        try {
            Diary dy = new Diary();
            JsonWriter writer = new JsonWriter("./data/DiaryJsonWriterTestEmptyDiary.md");
            writer.open();
            writer.write(dy);
            writer.close();

            JsonReader reader = new JsonReader("./data/DiaryJsonWriterTestEmptyDiary.md");
            dy = reader.read();
            assertEquals(0, dy.getAllJournals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterRegularDiary() {
        try {
            Diary dy = new Diary();

            Entry e1 = new Entry(11, 57);
            e1.addEntry("Test3");
            Theme t1 = new Theme(e1);
            t1.sortEntriesIntoThemes();
            Journal j1 = new Journal(t1);
            j1.setMonth(9);
            j1.setYear(2023);
            j1.setTitle("Test3");
            j1.setDay(26);
            dy.addJournal(j1);

            Entry e2 = new Entry(11, 58);
            e2.addEntry("Test4");
            Theme t2 = new Theme(e2);
            t2.sortEntriesIntoThemes();
            Journal j2 = new Journal(t2);
            j2.setMonth(9);
            j2.setYear(2023);
            j2.setTitle("Test4");
            j2.setDay(26);
            dy.addJournal(j2);

            JsonWriter writer = new JsonWriter("./data/DiaryJsonWriterTestRegularDiary.md");
            writer.open();
            writer.write(dy);
            writer.close();

            JsonReader reader = new JsonReader("./data/DiaryJsonWriterTestRegularDiary.md");
            dy = reader.read();

            List<Journal> journals = dy.getAllJournals();
            assertEquals(2, journals.size());

            checkJournal("Test3", 2023, 9, 26, journals.get(0));
            checkTheme(false, true, false, journals.get(0));
            checkEntry("Test3", 11, 57, journals.get(0));

            checkJournal("Test4", 2023, 9, 26, journals.get(1));
            checkTheme(false, true, false, journals.get(1));
            checkEntry("Test4", 11, 58, journals.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
