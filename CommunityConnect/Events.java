import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class Events {
    private static List<Event> eventList = new ArrayList<>();
    String name;
    String description;
    String date;
    String location;
    public Events(String name, String description, String date, String location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
    }
    @Override
    public String toString() {
        return "Name: " + name + "\n" +
               "Description: " + description + "\n" +
               "Date: " + date + "\n" +
               "Location: " + location;
    }
    public static void addEvent(Event e) {
    eventList.add(e);
    }
    public static void displayEvents() {
        System.out.println("                                                            COMMUNITY EVENTS");
        System.out.println("\n" + "=".repeat(140));
        
        if (eventList.isEmpty()) {
            System.out.println("┌" + "─".repeat(138) + "┐");
            System.out.printf("│ %-136s │%n", "No events to display.");
            System.out.println("└" + "─".repeat(138) + "┘");
            System.out.println("=".repeat(140));
            return;
        }
        
        System.out.println("-" + "─".repeat(20) + "-" + "─".repeat(30) + "-" + "─".repeat(50) + "-" + "─".repeat(25) + "-");
        System.out.printf("│ %-18s │ %-28s │ %-48s │ %-23s │%n", "TITLE", "LOCATION", "DESCRIPTION", "DATE");
        System.out.println("-" + "─".repeat(20) + "-" + "─".repeat(30) + "-" + "─".repeat(50) + "-" + "─".repeat(25) + "-");
        
        for (Event event : eventList) {
            String eventName = event.getTitle().length() > 18 ? 
                    event.getTitle().substring(0, 15) + "..." : 
                    event.getTitle();
            String location = event.getLocation().length() > 28 ? 
                    event.getLocation().substring(0, 25) + "..." : 
                    event.getLocation();
            String description = event.getDescription().length() > 48 ? 
                    event.getDescription().substring(0, 45) + "..." : 
                    event.getDescription();
            String date = String.valueOf(event.getDate());
            date = date.length() > 23 ? 
                    date.substring(0, 20) + "..." : 
                    date;
            
            System.out.printf("│ %-18s │ %-28s │ %-48s │ %-23s │%n", 
                    eventName, location, description, date);
        }
        
        System.out.println("-" + "─".repeat(20) + "-" + "─".repeat(30) + "-" + "─".repeat(50) + "-" + "─".repeat(25) + "-");
        System.out.println("=".repeat(140));
    }
    public static List<Event> getEventList() {
         return eventList;
    }
    public static void saveEventsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt"))) {
            List<Events.Event> events = Events.getEventList();
            for (Events.Event event : events) {
                String line = event.getTitle() + "|" 
                            + event.getDate() + "|" 
                            + event.getLocation() + "|" 
                            + event.getDescription();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing events to file: " + e.getMessage());
        }
    }
    public static void loadEventsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("events.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    Events.Event event = new Events.Event(parts[0], parts[3], parts[1], parts[2]);
                    Events.addEvent(event);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing events file found or error reading file.");
        }
    }
    public static class Event {
        private String title;
        private String description;
        private String date;
        private String location;
        
        public Event(String title, String description, String date, String location) {
            this.title = title;
            this.description = description;
            this.date = date;
            this.location = location;
        }
        public String getTitle() {
            return title;
        }
        public String getDescription() {
            return description;
        }
        public String getDate() {
            return date;
        }
        public String getLocation() {
            return location;
        }
        @Override
        public String toString() {
            return "Title: " + title + "\n" +
                   "Description: " + description + "\n" +
                   "Date: " + date + "\n" +
                   "Location: " + location;
        }
    }
}