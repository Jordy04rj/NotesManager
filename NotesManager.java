import java.io.*;
import java.util.Scanner;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Notes Manager ===");
            System.out.println("1. Write a new note");
            System.out.println("2. View all notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    writeNote(sc);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println("Exiting Notes Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 3);

        sc.close();
    }

    // Function to write note
    private static void writeNote(Scanner sc) {
        System.out.println("Enter your note (type 'end' to finish):");
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            String line;
            while (!(line = sc.nextLine()).equalsIgnoreCase("end")) {
                writer.write(line + System.lineSeparator());
            }
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    // Function to read notes
    private static void readNotes() {
        System.out.println("\n=== All Notes ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isEmpty = true;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                isEmpty = false;
            }
            if (isEmpty) {
                System.out.println("[No notes available]");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found yet. Start by writing a note.");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }
}

