import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class Admin extends User {
    static Scanner input = new Scanner(System.in);
    public Admin(String username, String password, String issue, String description, int votes, boolean voted) {
        super(username, password, issue, description, votes, voted);
    }
    public static Events.Event addEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Event Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Date of Event: ");
        String date = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        return new Events.Event(name, description, date, location);
    }
    public static void deletePost() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter message to delete:");
        String delete = input.nextLine();
        List<Chat> chats = Chat.getChats();
        for (int i = 0; i < chats.size(); i++) {
            if (chats.get(i).getMessage().equalsIgnoreCase(delete)) {
                chats.remove(i);
                System.out.println("Chat deleted.\n");
                return;
            }
        }
        System.out.println("No chat found with that message.\n");
    }
    public static void removeUser(List<User> users) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username to delete:");
        String usernameToDelete = input.nextLine();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equalsIgnoreCase(usernameToDelete)) {
                users.remove(i);
                System.out.println("User deleted.\n");
                return;
            }
        }
        System.out.println("No user found with that username.\n");
    }
    public static void resetVoting(List<User> users) {
        for (User user : users) {
            user.setIssue("N/A");
            user.setDescription("N/A");
            user.setVotes(0);
            user.setVoted(false);
        }
    }
}