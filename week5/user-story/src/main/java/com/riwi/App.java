package com.riwi;

import com.riwi.talent.model.DatabaseConnection;
import com.riwi.talent.view.ConsoleView;

// Application entry point.
// Two responsibilities only: initialize the database, then start the UI.
public class App {
    public static void main(String[] args) {
        // Step 1: create the employees table in the H2 in-memory database
        DatabaseConnection.initializeSchema();

        // Step 2: hand control to the console interface
        new ConsoleView().start();
    }
}