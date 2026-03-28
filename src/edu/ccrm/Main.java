package edu.ccrm;

import edu.ccrm.cli.CLIMenu;
import edu.ccrm.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        // Enable assertions
        boolean assertionsEnabled = false;
        assert assertionsEnabled = true;
        
        if (assertionsEnabled) {
            System.out.println("Assertions are enabled");
        } else {
            System.out.println("Assertions are disabled - enable with -ea VM option");
        }

        try {
            // Initialize configuration (Singleton)
            AppConfig config = AppConfig.getInstance();
            
            // Start CLI interface
            CLIMenu menu = new CLIMenu();
            menu.start();
            
        } catch (Exception e) {
            System.err.println("Application failed to start: " + e.getMessage());
            e.printStackTrace();
        }

        // Demonstrate Java platform differences
        printPlatformInfo();
    }

    private static void printPlatformInfo() {
        System.out.println("\n=== Java Platform Information ===");
        System.out.println("Java SE (Standard Edition): General purpose programming");
        System.out.println("Java ME (Micro Edition): Embedded and mobile devices");
        System.out.println("Java EE (Enterprise Edition): Enterprise applications");
        System.out.println("This application uses: Java SE");
    }
}