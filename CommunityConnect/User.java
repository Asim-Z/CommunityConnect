import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
class User{
    private String username;
    private String password;
    private String issue;
    private String description;
    private int votes;
    private boolean voted = false;
    public User(String username, String password, String issue, String description, int votes, boolean voted) {
        this.username = username;
        this.password = password; 
        this.issue = issue;
        this.description = description;
        this.votes = votes;
        this.voted = voted;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword() {
        this.password = password;
    }
    public String getIssue() {
        return issue;
    }
    public void setIssue(String issue) {
        this.issue = issue;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getVotes() {
        return votes;
    }
    public void setVotes(int votes) {
        this.votes = votes;
    }
    public boolean getVoted() {
        return voted;
    }
    public void setVoted(boolean voted) {
        this.voted = voted;
    }
    public static void loadUsersFromFile(List<User> users) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    User user = new User(parts[0], parts[1], parts[2], parts[3], 
                                       Integer.parseInt(parts[4]), 
                                       Boolean.parseBoolean(parts[5]));
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing users file found or error reading file.");
        }
    }
    public static void saveUsersToFile(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (User user : users) {
                String line = user.getUsername() + "|" 
                            + user.getPassword() + "|" 
                            + user.getIssue() + "|" 
                            + user.getDescription() + "|" 
                            + user.getVotes() + "|" 
                            + user.getVoted();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing users to file: " + e.getMessage());
        }
        }
    public static void displayIssuesandMax(List<User> users) {
        System.out.println("                                                                ISSUES");
        System.out.println("\n" + "=".repeat(140));
        System.out.println("-" + "─".repeat(15) + "-" + "─".repeat(25) + "-" + "─".repeat(75) + "-" + "─".repeat(10) + "-");
        System.out.printf("│ %-13s │ %-23s │ %-73s │ %-8s │%n", "USERNAME", "ISSUE", "DESCRIPTION", "VOTES");
        System.out.println("-" + "─".repeat(15) + "-" + "─".repeat(25) + "-" + "─".repeat(75) + "-" + "─".repeat(10) + "-");
        int maxVotes = 0;
        String maxIssue = "No issues found";
        for (User is : users) {
            if (!is.getIssue().equalsIgnoreCase("N/A")) {
                String username = is.getUsername().length() > 13 ? 
                        is.getUsername().substring(0, 10) + "..." : 
                        is.getUsername();
                String iss = is.getIssue().length() > 23 ? 
                        is.getIssue().substring(0, 20) + "..." : 
                        is.getIssue();
                String description = is.getDescription().length() > 73 ? 
                        is.getDescription().substring(0, 70) + "..." : 
                        is.getDescription();
                String votes = String.valueOf(is.getVotes());
                System.out.printf("│ %-13s │ %-23s │ %-73s │ %-8s │%n", 
                        username, iss, description, votes);
                if (is.getVotes() > maxVotes) {
                    maxVotes = is.getVotes();
                    maxIssue = is.getIssue();
                }
            }
        }
        System.out.println("-"  + "─".repeat(15) + "-" + "─".repeat(25) + "-" + "─".repeat(75) + "-" + "─".repeat(10) + "-");
        System.out.println("=".repeat(140));
        System.out.println("\nIssue with most votes: " + maxIssue + " (" + maxVotes + " votes)");
    }
    public static void voteOnIssue(List<User> users, Scanner input, User CurrentUser) {
        if (CurrentUser != null && CurrentUser.getVoted() == true) {
            System.out.println("Your vote was previously counted.");
            return;
        }
        boolean matching = false;
        while (!matching) {
            System.out.print("Enter issue name to vote on (0 if not voting): ");
            String vote = input.nextLine();
            if (vote.equals("0")) {
                break;
            }
            for (User i : users) {
                if (vote.equalsIgnoreCase(i.getIssue()) && !i.getIssue().equalsIgnoreCase("N/A")) {
                    i.setVotes(i.getVotes() + 1);
                    if (CurrentUser != null) {
                        CurrentUser.setVoted(true);
                    }
                    System.out.println("Vote Counted");
                    matching = true;
                    break;
                }
            }
            if (!matching) {
                System.out.println("No matching issue found. Try again.");
            }
        }
    }
    public static void createIssue(List<User> users, Scanner input, User CurrentUser) {
        System.out.print("Enter Issue Title: ");
        String issue = input.nextLine();
        boolean duplicate = false;
        for (User is : users) {
            if (issue.equalsIgnoreCase(is.getIssue())) {
                System.out.println("Issue Already Posted.");
                duplicate = true;
                break;
            }
        }
        if (!duplicate) {
            System.out.print("Enter Issue Description: ");
            String description = input.nextLine();
            CurrentUser.setIssue(issue);
            CurrentUser.setDescription(description);
        }
    }
    public static User createUser(List<User> users, Scanner input) {
        boolean unique = false;
        String username = "";
        while (!unique) {
            System.out.println("Enter Username:");
            username = input.nextLine();
            unique = true;
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    System.out.println("User Taken. Make Another User.");
                    unique = false;
                    break;
                }
            }
        }
        System.out.println("Enter Password:");
        String password = input.nextLine();
        User newUser = new User(username, password, "N/A", "N/A", 0, false);
        users.add(newUser);
        return newUser;
    }
    public static User loginUser(List<User> users, Scanner input) {
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Enter Username:");
            String userInput = input.nextLine();
            System.out.println("Enter Password:");
            String passInput = input.nextLine();
            for (User user : users) {
                if (user.getUsername().equals(userInput) && user.getPassword().equals(passInput)) {
                    return user;
                }
            }
            System.out.println("Invalid login, try again.");
        }
        return null;
    }
}