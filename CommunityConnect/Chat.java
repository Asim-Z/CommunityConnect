import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
class Chat {
    private String senderName;
    private String message;
    private LocalDate date;
    private static List<Chat> chats = new ArrayList<>();
    public Chat(String senderName, String message, LocalDate date) {
        this.senderName = senderName;
        this.message = message;
        this.date = date;
        chats.add(this);
    }
    public String getSenderName() {
        return senderName;
    }
    public String getMessage() {
        return message;
    }
    public LocalDate getDate() {
        return date;
    }
    public static List<Chat> getChats() {
        return chats;
    }
    public static void addNewMessage(String senderName) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your message (or 0 to exit):");
            String message = input.nextLine();
            if (message.equals("0")) {
                break;
            }
            Chat newChat = new Chat(senderName, message, LocalDate.now());
            if (chats.size() > 20) {
                chats.remove(0);
            }
        }
    }

    public static void displayChats() {
        System.out.println("                                                            COMMUNITY CHAT");
        System.out.println("\n" + "=".repeat(140));
        System.out.println("-" + "─".repeat(20) + "-" + "─".repeat(80) + "-" + "─".repeat(25) + "-");
        System.out.printf("│ %-18s │ %-78s │ %-23s │%n", "SENDER NAME", "MESSAGE", "DATE");
        System.out.println("-" + "─".repeat(20) + "-" + "─".repeat(80) + "-" + "─".repeat(25) + "-");
        for (Chat chat : chats) {
            String senderName = chat.getSenderName().length() > 18 ? 
                    chat.getSenderName().substring(0, 15) + "..." : 
                    chat.getSenderName();
            String message = chat.getMessage().length() > 78 ? 
                    chat.getMessage().substring(0, 75) + "..." : 
                    chat.getMessage();
            String date = String.valueOf(chat.getDate());
            date = date.length() > 23 ? 
                    date.substring(0, 20) + "..." : 
                    date;
            System.out.printf("│ %-18s │ %-78s │ %-23s │%n", 
                    senderName, message, date);
        }
        System.out.println("-" + "─".repeat(20) + "-" + "─".repeat(80) + "-" + "─".repeat(25) + "-");
        System.out.println("=".repeat(140));
    }
    public static void loadChatsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("chats.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    new Chat(parts[0], parts[1], LocalDate.parse(parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing chats file found or error reading file.");
        }
    }
    public static void saveChatsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("chats.txt"))) {
            List<Chat> chats = Chat.getChats();
            for (Chat chat : chats) {
                String line = chat.getSenderName() + "|" 
                            + chat.getMessage().replace("|", " ") + "|"
                            + chat.getDate().toString();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing chats to file: " + e.getMessage());
        }
    }
}