import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        boolean running = true;
        List<User> users = new ArrayList<>();
        User.loadUsersFromFile(users);
        Events.loadEventsFromFile();
        Chat.loadChatsFromFile();
        System.out.println("------ Welcome to Community Connect! ------");
        System.out.println("  1) Register");
        System.out.println("  2) Login");
        System.out.println("  3) Exit");
        int choice1 = input.nextInt();
        input.nextLine();
        User CurrentUser = null;
        switch (choice1) {
            case 1:
                CurrentUser = User.createUser(users, input);
                break;
            case 2: 
                CurrentUser = User.loginUser(users, input); 
                break;
            case 3:
                System.exit(0);
        }
        while (running) {
            System.out.println("\n------ Main Menu ------");            
            System.out.println("  1) Report Issue");
            System.out.println("  2) Display/Vote on Issues");
            System.out.println("  3) Community Events");
            System.out.println("  4) Community Chat");
            System.out.println("  5) Exit");
            int choice2 = input.nextInt();
            input.nextLine(); 
            switch (choice2) {
                case 1:
                    User.createIssue(users, input, CurrentUser);
                    break;
                case 2:
                    User.displayIssuesandMax(users);
                    User.voteOnIssue(users, input, CurrentUser);
                    break;
                case 3:
                    Events.displayEvents();
                    break;
                case 4:
                    Chat.displayChats();
                    Chat.addNewMessage(CurrentUser.getUsername());
                    break;
                case 5:
                    User.saveUsersToFile(users);
                    Events.saveEventsToFile();
                    Chat.saveChatsToFile();
                    running = false;
                    break;
                case 1010:
                    if (CurrentUser.getUsername().equalsIgnoreCase("FaizanRK") || CurrentUser.getUsername().equalsIgnoreCase("Asim12")) {
                        System.out.println("\n------ Admin Controls ------");
                        System.out.println("  1) Add Event");
                        System.out.println("  2) Remove User");
                        System.out.println("  3) Delete Post");
                        System.out.println("  4) Reset Voting");
                        System.out.println("  5) Exit");
                        int choice3 = input.nextInt();
                        input.nextLine();
                        switch (choice3) {
                            case 1:
                                Events.addEvent(Admin.addEvent());
                                break;
                            case 2:
                                Admin.removeUser(users);
                                break;
                            case 3:
                                Admin.deletePost();
                                break;
                            case 4:
                                Admin.resetVoting(users);
                                break;
                            default:
                                break;
                        }
                    } else {
                        break;
                    }                        
            }
        }
        input.close();
    }
}