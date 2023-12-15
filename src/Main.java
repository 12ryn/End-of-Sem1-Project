import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


// Ryan Li
// Date of File Creation: 5th December 2023
// 11.11 Computer Science End of Semester Project
// So far Ryan Li is the only collaborator
// This project takes a user file, highlights and customizes the contents with color and text styles according to the user's preferences, and creates a new file with the changed content.

public class Main {

    public static void main(String[] args) {

        String bold = "\u001B[1m";
        // ANSI escape sequence for red color
        String colorOne = "\u001B[4m";
        // ANSI escape sequence for reset color
        String colorTwo = "\u001B[36m";
        String resetColor = "\u001B[0m";

        System.out.println(bold + colorOne + "This text exists." + colorTwo + " And this does too" + resetColor);
        System.out.println();
        System.out.println(bold + "This text will be printed in the default color.");

    }

    public static int countLinesInFile(String inputFilename) throws FileNotFoundException {

        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;

        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }

        scanner.close();
        return lineCount;

    }


}