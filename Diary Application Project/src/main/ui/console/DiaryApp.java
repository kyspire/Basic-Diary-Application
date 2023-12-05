package ui.console;

import model.Diary;
import model.Entry;
import model.Journal;
import model.Theme;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DiaryApp {
    static Scanner scanner;
    private static final String JSON_STORE = "./data/savedJournals.md";
    private Diary diary;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs my Diary application
    public DiaryApp() {
        scanner = new Scanner(System.in);
        diary = new Diary();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runDiary();
    }


    // EFFECTS: Welcomes the user to the application. It asks the user if they would like
    //          to enter Journals to their Diary. After, the application asks if they would
    //          like to see their previous Journals based on a given theme of their decision.
    public void runDiary() {
        System.out.println("Welcome to your Journal!");
        loadPreviousDiary();
        addJournalsToDiary();
        accessSingleJournalsByTheme();
        accessAllTitles();
        saveDiary();
        System.out.println("Goodbye!");
        System.exit(0);
    }



    // EFFECTS: welcomes the user to the application



    // Effects: With prompts and user responses, the entry with time in hours,
    //          minutes, and an entry is created.
    public Entry createEntry() {
        System.out.println("Please enter the hour (00-23)");
        int hour = Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter the minute (00-59)");
        int minute = Integer.parseInt(scanner.nextLine());

        Entry e = new Entry(hour, minute);

        System.out.println("Add your entry!");
        String entry = scanner.nextLine();
        e.addEntry(entry);

        return e;
    }

    // EFFECTS: takes the created entry and sorts the entry into a theme.
    public Theme setTheme(Entry e) {
        System.out.println("Okay! Setting the theme...");
        Theme t = new Theme(e);
        t.sortEntriesIntoThemes();

        return t;
    }

    // EFFECTS: After sorting the theme, it creates a Journal with a date in years, month, and day,
    //          along with the themed entry and its time of log in.
    public Journal createJournal(Theme t) {
        Journal j = new Journal(t);
        System.out.println("Now for the finishing touches!");
        System.out.println("Please enter the title");
        String title = scanner.nextLine();
        j.setTitle(title);

        System.out.println("Please enter the year (ex. 2007)");
        int year = Integer.parseInt(scanner.nextLine());
        j.setYear(year);

        System.out.println("Please enter the month (01-12)");
        int month = Integer.parseInt(scanner.nextLine());
        j.setMonth(month);

        System.out.println("Please enter the day (01-31 depending on the chosen month)");
        int day = Integer.parseInt(scanner.nextLine());
        j.setDay(day);

        return j;
    }

    // EFFECTS: adds an arbitrary amount of Journals into a Diary until
    //          the user wants to stop.
    public void addJournalsToDiary() {
        System.out.println("Would you like to log an entry? yes/no");
        String choice = scanner.nextLine();

        while (choice.equals("yes")) {
            Entry userEntry = createEntry();
            Theme entryTheme = setTheme(userEntry);
            Journal journalEntry = createJournal(entryTheme);
            this.diary.addJournal(journalEntry);
            System.out.println("Would you like to make a new entry? yes/no");
            choice = scanner.nextLine();
        }
    }

    // EFFECTS: after entering x Journals into the Diary, the app asks if the user wants to access
    //          their saved journal entries based on category.
    @SuppressWarnings("methodlength")
    public void accessSingleJournalsByTheme() {
        System.out.println("Would you like to access your current Journals for this session? yes/no");
        String decide = scanner.nextLine();
        while (decide.equals("yes")) {
            System.out.println("Which theme would you like to access? "
                    + "all/all in order/day goals/reflections/dreams");
            String decideTheme = scanner.nextLine();
            switch (decideTheme) {
                case "all":
                    viewSingleAllJournals();
                    break;
                case "all in order":
                    viewSingleOrderedJournals();
                    break;
                case "day goals":
                    viewSingleDayJournals();
                    break;
                case "reflections":
                    viewSingleReflections();
                    break;
                case "dreams":
                    viewSingleDreams();
                    break;

                default:
                    System.out.println("Not an option!");
            }
            System.out.println("Would you like to access another theme? yes/no");
            decide = scanner.nextLine();
        }
    }
    // REQUIRES: valid index user input based on size of Journals
    // EFFECTS: gets all the journals and tells the user the amount of all Journals.
    //          Given a valid index input, the user can view their Journal entry.

    public void viewSingleAllJournals() {
        List<Journal> viewAll = diary.getAllJournals();
        int size = diary.getAllJournals().size();

        if (size == 0) {
            System.out.println("No Journals!");
        } else {
            System.out.println("You have " + size + " total journals!");
            System.out.println("Please type the index of the journal you want to view (0-" + (size - 1) + ")");
            int index = Integer.parseInt(scanner.nextLine());
            Journal viewJournal = viewAll.get(index);
            Entry viewEntry = viewJournal.getMyEntry();
            System.out.println("The date is " + viewJournal.getMonth()
                    + "/"
                    + viewJournal.getDay()
                    + "/"
                    + viewJournal.getYear());
            System.out.println("The time is " + viewEntry.getHours() + ":" + viewEntry.getMinutes());
            System.out.print(viewJournal.getTitle());
            System.out.println(viewEntry.getEntry());
        }
    }

    // REQUIRES: valid index user input based on size of Journals
    // EFFECTS: gets all the journals in order and tells the user the amount of all Journals.
    //          Given a valid index input, the user can view their Journal entry.
    public void viewSingleOrderedJournals() {
        List<Journal> viewAllInOrder = diary.sortAllJournalsByDate();

        int size = diary.getAllJournals().size();

        if (size == 0) {
            System.out.println("No Journals!");
        } else {
            System.out.println("You have " + size + " total journals in order!");
            System.out.println("Please type the index of the journal you want to view (0-" + (size - 1) + ")");
            int index = Integer.parseInt(scanner.nextLine());
            Journal viewJournal = viewAllInOrder.get(index);
            Entry viewEntry = viewJournal.getMyEntry();
            System.out.println("The date is " + viewJournal.getMonth()
                    + "/"
                    + viewJournal.getDay()
                    + "/"
                    + viewJournal.getYear());
            System.out.println("The time is " + viewEntry.getHours() + ":" + viewEntry.getMinutes());
            System.out.print(viewJournal.getTitle());
            System.out.println(viewEntry.getEntry());
        }
    }

    // REQUIRES: valid index user input based on size of Journals
    // EFFECTS: gets all the  day journals and tells the user the amount of day Journals.
    //          Given a valid index input, the user can view their Journal entry.
    public void viewSingleDayJournals() {

        List<Journal> viewAllInOrder = diary.sortDayGoalJournals();

        int size = viewAllInOrder.size();

        if (size == 0) {
            System.out.println("No Journals!");
        } else {
            System.out.println("You have " + size + " day journals!");
            System.out.println("Please type the index of the journal you want to view (0-" + (size - 1) + ")");
            int index = Integer.parseInt(scanner.nextLine());
            Journal viewJournal = viewAllInOrder.get(index);
            Entry viewEntry = viewJournal.getMyEntry();
            System.out.println("The date is " + viewJournal.getMonth()
                    + "/"
                    + viewJournal.getDay()
                    + "/"
                    + viewJournal.getYear());
            System.out.println("The time is " + viewEntry.getHours() + ":" + viewEntry.getMinutes());
            System.out.print(viewJournal.getTitle());
            System.out.println(viewEntry.getEntry());
        }
    }

    // REQUIRES: valid index user input based on size of Journals
    // EFFECTS: gets all the reflection journals and tells the user the amount of reflection Journals.
    //          Given a valid index input, the user can view their Journal entry.
    public void viewSingleReflections() {

        List<Journal> viewAllInOrder = diary.sortEveningReflectionJournals();

        int size = viewAllInOrder.size();

        if (size == 0) {
            System.out.println("No Journals!");
        } else {
            System.out.println("You have " + size + " reflection journals!");
            System.out.println("Please type the index of the journal you want to view (0-" + (size - 1) + ")");
            int index = Integer.parseInt(scanner.nextLine());
            Journal viewJournal = viewAllInOrder.get(index);
            Entry viewEntry = viewJournal.getMyEntry();
            System.out.println("The date is " + viewJournal.getMonth()
                    + "/"
                    + viewJournal.getDay()
                    + "/"
                    + viewJournal.getYear());
            System.out.println("The time is " + viewEntry.getHours() + ":" + viewEntry.getMinutes());
            System.out.print(viewJournal.getTitle());
            System.out.println(viewEntry.getEntry());
        }
    }

    // REQUIRES: valid index user input based on size of Journals
    // EFFECTS: gets all the dream journals and tells the user the amount of dream Journals.
    //          Given a valid index input, the user can view their Journal entry.
    public void viewSingleDreams() {

        List<Journal> viewAllInOrder = diary.sortDreamJournals();

        int size = viewAllInOrder.size();

        if (size == 0) {
            System.out.println("No Journals!");
        } else {
            System.out.println("You have " + size + " dream journals!");
            System.out.println("Please type the index of the journal you want to view (0-" + (size - 1) + ")");
            int index = Integer.parseInt(scanner.nextLine());
            Journal viewJournal = viewAllInOrder.get(index);
            Entry viewEntry = viewJournal.getMyEntry();
            System.out.println("The date is " + viewJournal.getMonth()
                    + "/"
                    + viewJournal.getDay()
                    + "/"
                    + viewJournal.getYear());
            System.out.println("The time is " + viewEntry.getHours() + ":" + viewEntry.getMinutes());
            System.out.print(viewJournal.getTitle());
            System.out.println(viewEntry.getEntry());
        }
    }

    // REQUIRES: non-empty storeJournals
    // EFFECTS: prints out all of the titles and entries.
    public void accessAllTitles() {
        System.out.println("Would you like to see the titles and entries of all Journals? yes/no");
        String next = scanner.nextLine();

        if (next.equals("yes")) {
            for (Journal j : this.diary.getAllJournals()) {
                System.out.println(j.getTitle());
                System.out.println(j.getMyEntry().getEntry());
                System.out.println("~~~~~~");
            }
        }
    }

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // EFFECTS: saves the workroom to file
    private void saveDiary() {
        System.out.println("Would you like to save your Journals? yes/no");
        String decide = scanner.nextLine();

        if (decide.equals("yes")) {
            try {
                jsonWriter.open();
                jsonWriter.write(diary);
                jsonWriter.close();
                System.out.println("Saved diary to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // NOTE: referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git starter
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadPreviousDiary() {
        System.out.println("Would you like to load your previous diary? yes/no");
        String decide = scanner.nextLine();
        if (decide.equals("yes")) {
            try {
                diary = jsonReader.read();
                System.out.println("Loaded previous diary from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }


}




