 

# My Personal Project

## Self-Improvement Application

My personal project will be a self-improvement application
that serves to help people replace their bad habits and 
procrastination with productive and self-improving activities.
The application will be based on two aspects: 

- **Journaling**
- **Habit Tracking** 

The application will allow users to create a journal
to log their thoughts, stories, and ideas in one section
and have users fill out good/bad habits for the day using
a checklist. 

This application is targeted towards users around the ages
16-30 who are seeking a reform in the way they are living 
their lives currently. 

This project is of interest to me because over the past year,
I've realized that I procrastinate a lot, I over eat, and 
I don't exercise frequently enough. Creating this application
will help me visualize my thought processes and implement a
slow but steady change towards a better lifestyle. 

## User Stories 

- As a user, I want to be able to create a journal entry with a story and time of log in
- As a user, I want to be able to categorize my journals based on morning, afternoon, and night
- As a user, I want to be able to add a title and a date for my themed journal entry
- As a user, I want to be able to add an arbitrary amount of Journals to my Diary
- As a user, I want to be able to see a total list sorted by date from earliest to most recent
- As a user, I want to see my list of Journals with a specific theme in my Diary
- As a user, I want to print all of my titles and entries in my Diary
- As a user, I want to view an individual entry given the theme of the Journal
- As a user, I want to be able to have the option to save my Journal entries and reopen them at a later time.
- As a user, I want to be able to have the option to reload my previously saved Journal entries and edit them again.

# Instructions for Grader
- You can generate the first required action related to the user story adding multiple Journals to a Diary by inserting
the required int and String fields and clicking the "Confirm Diary Button" in the Log Entry area located on the top 
left of the GUI. 
- You can generate the second required action related to the user story by clicking one of either the "Display all
journals", "Ordered", "Day goals", "Reflections", or "Dreams" buttons located on the top right above a big 
JTextArea field. This creates a subset of journals based on their theme. 
- You can locate my visual component by looking at the bottom left of the GUI. 
- You can save the state of my application by clicking the Sava Data button near the bottom right.
- You can reload previously saved Journals by clicking the Load Data button near the bottom right.

## Phase 4: Task 2

Wed Nov 29 17:44:55 PST 2023
Added New Journal To Diary.

Wed Nov 29 17:45:01 PST 2023
Added New Journal To Diary.

Wed Nov 29 17:45:02 PST 2023
Displayed All Current Journals!

Wed Nov 29 17:45:04 PST 2023
Displayed subset of Journals based on Journal Date

Wed Nov 29 17:45:05 PST 2023
Displayed subset of Journals based on Day Goal Theme

Wed Nov 29 17:45:05 PST 2023
Displayed subset of Journals based on Reflection Theme

Wed Nov 29 17:45:06 PST 2023
Displayed subset of Journals based on Dream Theme

## Phase 4: Task 3
If I had more time to work on the project, I would refractor two main components: my model classes, and my GUI. 

The first component I would refactor is my classes. Currently, I have entry, theme, journal, and diary. But after implementing
phase 2 and 3, I realized that my entry and theme class could be built inside of the journal class. The
refracting I can do is make the implementation of entry in journal (and remove the entry class entirely) and 
make a method within Journal class that sets a theme for the entry (thus removing the need for the Theme class). 

The second component I would refactor is my DiaryAppGUI class. This class has refactored
methods, but there is still code all over the place (such as buttons, labels, etc.) If I had more time, I would 
work on grouping methods in a more systematic manner and organize the Swing components more neatly.

