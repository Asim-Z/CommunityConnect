# Community Connect

A community platform where users can report local issues, vote on them, and communicate through events and chat.

## Features

- User registration and login
- Report and vote on community issues (potholes, power outages, etc.)
- Community events board
- Group chat (stores last 20 messages)
- Admin panel for moderation (hidden menu option)
- All data saved to text files

## Implementation

Built with 5 main classes:
- **User** - handles accounts, issue reporting, and voting
- **Admin** - extends User with moderation tools
- **Chat** - messaging system with timestamps
- **Events** - community event management
- **Main** - runs the program and menu system

Uses file I/O to save everything (users, events, chats) so data persists between sessions.

## Running the Program
```bash
javac *.java
java Main
```

## Usage

1. Register or login
2. Choose from main menu:
   - Report issues
   - Vote on issues
   - View events
   - Use chat
3. Type `1010` in main menu for admin access

## Technologies

Java, ArrayList, BufferedReader/Writer for file handling
