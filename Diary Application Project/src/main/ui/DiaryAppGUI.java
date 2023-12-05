package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// The DiaryAppGUI creates a basic graphical user interface that represents my Diary Application.
// It has a section where the user can input their entry along with the time and date. It has another
// section that shows the Journals in the Diary depending on the theme the user chooses. Lastly, they can save
// and load data whenever the application is running.
public class DiaryAppGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/savedJournals.md";
    private Diary diary;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextField hourField;
    private JTextField minuteField;
    private JTextField titleField;

    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JTextArea entryField;

    private JTextArea displayAllJournals;

    // EFFECTS: constructs a Diary with a GUI component.
    public DiaryAppGUI() {
        super("Diary GUI");
        this.diary = new Diary();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        JPanel panel = new JPanel();
        panelAndFrameSetup(panel);
        createSubsetJournalButtons(panel);
        createLogEntryComponents(panel);

        createSaveAndLoadButtons(panel);

        JLabel diaryButton = new JLabel();
        ImageIcon diaryImage = new ImageIcon("data/diaryImage.jpeg");
        Image image = diaryImage.getImage();
        Image newimg = image.getScaledInstance(600, 200,  java.awt.Image.SCALE_SMOOTH);
        diaryImage = new ImageIcon(newimg);
        diaryButton.setIcon(diaryImage);
        diaryButton.setBounds(50,450, 600, 200);
        panel.add(diaryButton);


        add(panel);
        setVisible(true);

    }

    // EFFECTS: create the save and load buttons for the GUI
    private void createSaveAndLoadButtons(JPanel panel) {
        JButton save = new JButton("Save Data");
        save.setBounds(700, 625, 100, 30);
        save.setActionCommand("save");
        save.addActionListener(this);
        panel.add(save);

        JButton load = new JButton("Load Data");
        load.setBounds(900, 625, 100, 30);
        load.setActionCommand("load");
        load.addActionListener(this);
        panel.add(load);
    }

    // EFFECTS: creates label, button, and field components for the LogEntry section
    private void createLogEntryComponents(JPanel panel) {
        createHourComponents(panel);
        createMinuteComponents(panel);
        createEntryComponents(panel);
        createTitleComponents(panel);
        createYearComponents(panel);
        createMonthComponents(panel);
        createDayComponents(panel);
        createConfirmDiaryButton(panel);
    }

    // EFFECTS: creates a confirm diary button
    private void createConfirmDiaryButton(JPanel panel) {
        JButton confirm = new JButton("Confirm Diary Entry");
        confirm.setBounds(250, 400, 200, 20);
        confirm.addActionListener(this);
        confirm.setActionCommand("Confirm");
        panel.add(confirm);
    }

    // EFFECTS: creates a day label and field
    private void createDayComponents(JPanel panel) {
        JLabel dayLabel = new JLabel("Enter the day 00-31");
        dayLabel.setBounds(550, 300, 150, 20);
        panel.add(dayLabel);

        dayField = new JTextField(10);
        dayField.setBounds(550, 320, 150, 20);
        panel.add(dayField);
    }

    // EFFECTS: creates a month label and field
    private void createMonthComponents(JPanel panel) {
        JLabel monthLabel = new JLabel("Enter the month 01-12");
        monthLabel.setBounds(350, 300, 150, 20);
        panel.add(monthLabel);

        monthField = new JTextField(10);
        monthField.setBounds(350, 320, 150, 20);
        panel.add(monthField);
    }

    // EFFECTS: creates a year label and field
    private void createYearComponents(JPanel panel) {
        JLabel yearLabel = new JLabel("Enter the year");
        yearLabel.setBounds(170, 300, 150, 20);
        panel.add(yearLabel);

        yearField = new JTextField(10);
        yearField.setBounds(170, 320, 150, 20);
        panel.add(yearField);
    }

    // EFFECTS: creates a title level and field
    private void createTitleComponents(JPanel panel) {
        JLabel titleLabel = new JLabel("Enter the title");
        titleLabel.setBounds(10, 300, 100, 20);
        panel.add(titleLabel);

        titleField = new JTextField(10);
        titleField.setBounds(10, 320, 100, 20);
        panel.add(titleField);
    }

    // EFFECTS: creates an entry label and field
    private void createEntryComponents(JPanel panel) {
        JLabel entryLabel = new JLabel("Enter entry here");
        Dimension entrySize = entryLabel.getPreferredSize();
        entryLabel.setBounds(305,80,entrySize.width,entrySize.height);
        panel.add(entryLabel);

        entryField = new JTextArea(50,10);
        entryField.setBounds(250, 105, 250, 150);
        entryField.setLineWrap(true);
        entryField.setWrapStyleWord(true);
        add(entryField);
    }

    // EFFECTS: creates a minute label and field
    private void createMinuteComponents(JPanel panel) {
        JLabel minuteLabel = new JLabel("Enter minute 00-59");
        Dimension minuteSize = minuteLabel.getPreferredSize();
        minuteLabel.setBounds(550,0, minuteSize.width, minuteSize.height);
        panel.add(minuteLabel);

        minuteField = new JTextField(10);
        minuteField.setBounds(550, 25, 125, 30);
        panel.add(minuteField);
    }

    // EFFECTS: creates an hour label and field
    private void createHourComponents(JPanel panel) {
        JLabel hourLabel = new JLabel("Enter hour 00-23");
        Dimension hourSize = hourLabel.getPreferredSize();
        hourLabel.setBounds(100,0,hourSize.width,hourSize.height);
        panel.add(hourLabel);

        hourField = new JTextField(10);
        hourField.setBounds(100, 25, 125, 30);
        panel.add(hourField);
    }

    // EFFECTS: sets up the panel and some  DiaryGUI frame functions
    private void panelAndFrameSetup(JPanel panel) {
        getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,700);
        panel.setLayout(null);
    }

    // EFFECTS: creates the components for finding a subset of journals given a theme
    private void createSubsetJournalButtons(JPanel panel) {
        createDisplayJournalPanel(panel);
        createClearButton(panel);
        createOrderedButton(panel);
        createDayGoalButton(panel);
        createReflectionsButton(panel);
        createDreamsButton(panel);
        createDisplayAllJournalsButton(panel);
        createExitButton(panel);
        createDisplayAllJournalsTextField(panel);
    }

    // EFFECTS: creates a display journal field
    private void createDisplayAllJournalsTextField(JPanel panel) {
        displayAllJournals = new JTextArea(80, 40);
        displayAllJournals.setBounds(750,200, 400,400);
        displayAllJournals.setLineWrap(true);
        displayAllJournals.setWrapStyleWord(true);
        panel.add(displayAllJournals);
    }

    // EFFECTS: creates an exit button
    private void createExitButton(JPanel panel) {
        JButton exit = new JButton("Exit Diary");
        exit.setBounds(1100, 625, 100, 30);
        exit.setActionCommand("exit");
        exit.addActionListener(this);
        panel.add(exit);
    }

    // EFFECTS: creates a display all journals button
    private void createDisplayAllJournalsButton(JPanel panel) {
        JButton displayAllJournals = new JButton("Display All Journals");
        displayAllJournals.setBounds(850, 150, 200, 50);
        displayAllJournals.setActionCommand("displayAllJournals");
        displayAllJournals.addActionListener(this);
        panel.add(displayAllJournals);
    }

    // EFFECTS: creates a subset for dream journals button
    private void createDreamsButton(JPanel panel) {
        JButton dreams = new JButton("Dreams");
        dreams.setBounds(1100, 75, 100, 30);
        dreams.setActionCommand("dreams");
        dreams.addActionListener(this);
        panel.add(dreams);
    }

    // EFFECTS: creates a subset for reflection journal button
    private void createReflectionsButton(JPanel panel) {
        JButton reflections = new JButton("Reflections");
        reflections.setBounds(1000, 75, 100, 30);
        reflections.setActionCommand("reflections");
        reflections.addActionListener(this);
        panel.add(reflections);
    }

    // EFFECTS: creates a subset for day goal journal button
    private void createDayGoalButton(JPanel panel) {
        JButton dayGoals = new JButton("Day Goals");
        dayGoals.setBounds(900, 75, 100, 30);
        dayGoals.setActionCommand("dayGoals");
        dayGoals.addActionListener(this);
        panel.add(dayGoals);
    }

    // EFFECTS: creates a subset for ordered journals button
    private void createOrderedButton(JPanel panel) {
        JButton ordered = new JButton("Ordered");
        ordered.setBounds(800, 75, 100, 30);
        ordered.setActionCommand("Ordered");
        ordered.addActionListener(this);
        panel.add(ordered);
    }

    // EFFECTS: creates a clear text button
    private void createClearButton(JPanel panel) {
        JButton clear = new JButton("clear text");
        clear.setBounds(750, 160, 100, 30);
        clear.setActionCommand("Clear");
        clear.addActionListener(this);
        panel.add(clear);
    }

    // EFFECTS: creates a display subset of journal label
    private static void createDisplayJournalPanel(JPanel panel) {
        JLabel displayJournal = new JLabel("Find your Subset of Journals Sorted by Theme");
        displayJournal.setBounds(800, 50, 500, 30);
        panel.add(displayJournal);
    }

    // EFFECTS: based on the action event performed, run the corresponding code:
    // Clear ~ sets the display field to empty string
    // Ordered ~ outputs the list in order, empty string otherwise
    // day goals ~ outputs the day goal themed journals, "No journals!" otherwise
    // reflections ~ outputs the reflection themed journals, "No Journals!" otherwise
    // dreams ~ outputs the dream themed journals, "No Journals!" otherwise
    // load ~ loads previously saved data
    // save ~ saves currently inputted data
    // display all journals ~ displays all journals saved
    // exit ~ closes the program
    // confirm ~ creates a journal entry

    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Clear":
                displayAllJournals.setText("");
                break;
            case "Ordered":
                reOrderTheJournals();
                break;
            case "dayGoals":
                resortByDayGoals();
                break;
            case "reflections":
                resortByReflections();
                break;
            case "dreams":
                resortByDreams();
                break;
            case "load":
                loadData();
                break;
            case "save":
                saveData();
                break;
            case "displayAllJournals":
                allJournalsDisplay();
                break;
            case "exit":
                setVisible(false);
                String events = "";
                for (Event next : EventLog.getInstance()) {
                    events += next.getDate() + "\n" + next.getDescription() + "\n\n";
                }
                System.out.println(events);
                System.exit(0);
                break;
            case "Confirm":
                createJournalEntry();
                resetFields();
                break;
        }

    }

    // EFFECTS: Displays all Journals on GUI
    private void allJournalsDisplay() {
        String display = "";
        List<Journal> allJournals = this.diary.getAllJournals();
        for (Journal j : allJournals) {
            display = display + j.getTitle() + " " + j.getDay()
                    + "/" + j.getMonth() + "/" + j.getYear() + "\n"
                    + j.getMyEntry().getEntry() + "\n~~~~~~~~\n";
        }
        displayAllJournals.setText(display);
    }

    // EFFECTS:  saves all current Journals
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(diary);
            jsonWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS:  Loads previously saved data
    private void loadData() {
        try {
            diary = jsonReader.read();
        } catch (IOException ex) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: displays resorted journals by theme dream
    private void resortByDreams() {
        String dream = "";
        List<Journal> dreamJournals = this.diary.sortDreamJournals();
        if (dreamJournals.size() != 0) {
            for (Journal j1 : dreamJournals) {
                dream = dream + j1.getTitle() + " " + j1.getDay()
                        + "/" + j1.getMonth() + "/" + j1.getYear() + "\n"
                        + j1.getMyEntry().getEntry() + "\n~~~~~~~~\n";
            }
            displayAllJournals.setText(dream);
        } else {
            displayAllJournals.setText("No Journals!");
        }
    }

    //EFFECTS:  displays resorted journals by theme reflection
    private void resortByReflections() {
        String reflect = "";
        List<Journal> reflectJournals = this.diary.sortEveningReflectionJournals();
        if (reflectJournals.size() != 0) {
            for (Journal j1 : reflectJournals) {
                reflect = reflect + j1.getTitle() + " " + j1.getDay()
                        + "/" + j1.getMonth() + "/" + j1.getYear() + "\n"
                        + j1.getMyEntry().getEntry() + "\n~~~~~~~~\n";
            }
            displayAllJournals.setText(reflect);
        } else {
            displayAllJournals.setText("No Journals!");
        }
    }

    //EFFECTS:  displays resorted journals by theme goals
    private void resortByDayGoals() {
        String day = "";
        List<Journal> dayJournals = this.diary.sortDayGoalJournals();
        if (dayJournals.size() != 0) {
            for (Journal j1 : dayJournals) {
                day = day + j1.getTitle() + " " + j1.getDay()
                        + "/" + j1.getMonth() + "/" + j1.getYear() + "\n"
                        + j1.getMyEntry().getEntry() + "\n~~~~~~~~\n";
            }
            displayAllJournals.setText(day);
        } else {
            displayAllJournals.setText("No Journals!");
        }
    }

    // EFFECTS: displays resorted journals by date
    private void reOrderTheJournals() {
        String order = "";
        List<Journal> orderedJournals = this.diary.sortAllJournalsByDate();

        for (Journal j1 : orderedJournals) {
            order = order + j1.getTitle() + " " + j1.getDay()
                    + "/" + j1.getMonth() + "/" + j1.getYear() + "\n"
                    + j1.getMyEntry().getEntry() + "\n~~~~~~~~\n";
        }
        displayAllJournals.setText(order);
        return;
    }

    // REQUIRES: valid inputs for all fields
    // MODIFIES: this
    // EFFECTS: takes the user input from the GUI and creates a new Journal entry
    private void createJournalEntry() {
        Entry entry = new Entry(Integer.parseInt(hourField.getText()), Integer.parseInt(minuteField.getText()));
        entry.addEntry(entryField.getText());
        Theme theme = new Theme(entry);
        theme.sortEntriesIntoThemes();
        Journal journal = new Journal(theme);
        journal.setTitle(titleField.getText());
        journal.setYear(Integer.parseInt(yearField.getText()));
        journal.setDay(Integer.parseInt(dayField.getText()));
        journal.setMonth(Integer.parseInt(monthField.getText()));
        this.diary.addJournal(journal);
    }

    // EFFECTS: reset the log entry fields to empty string
    private void resetFields() {
        hourField.setText("");
        minuteField.setText("");
        entryField.setText("");
        titleField.setText("");
        yearField.setText("");
        dayField.setText("");
        monthField.setText("");
    }

    // EFFECTS: starts up the Diary App GUI.
    public static void main(String[] args) {
        new DiaryAppGUI();

    }
}
