import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.lang.Thread;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Ryan Li
// Date of File Creation: 5th December 2023
// 11.11 Computer Science End of Semester Project
// So far Ryan Li is the only collaborator
// This project takes a user file, highlights and customizes the contents with color and text styles according to the user's preferences, and creates a new file with the changed content.

public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        System.out.println("Welcome to the Syntax Highlighter. This program takes your chosen file, highlights and customizes the contents with color and text styles according to the your preferences, creating a new file with the changed content.");
        delayText();
        System.out.println("Please enter your correct and existing file path.");
        delayText();
        System.out.println("For example, \"\u001B[34m/Users/myName/Downloads/textName.txt\u001B[0m\"");
        delayText();
        System.out.println("Make sure you allow IntelliJ to access your Downloads! I promise you this is not a virus");

        Scanner scanner = new Scanner(System.in);
        String srcFile = scanner.nextLine();

        String[] finalFile = readFile(srcFile);

        delayText();

        System.out.println("\u001B[1mFile exists. You may proceed.\u001B[0m");

        menuMessage();

        int menuChoice = scanner.nextInt();

    }

    public static int countLinesInFile(String inputFileName) throws FileNotFoundException {

        File file = new File(inputFileName);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;

        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }

        scanner.close();
        return lineCount;

    }

    public static String[] readFile(String inputFileName) throws FileNotFoundException {

        File file = new File(inputFileName);
        Scanner scanner = new Scanner(file);

        int numberOfLinesInFile = countLinesInFile(inputFileName);
        String[] data = new String[numberOfLinesInFile];
        int index = 0;

        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextLine();
        }
        scanner.close();

        return data;

    }

    public static void menuMessage() {

        String color = "\u001B[38;5;208mc" + "\u001B[31mo" + "\u001B[35ml" + "\u001B[34mo" + "\u001B[32mr\u001B[0m";
        delayText();
        System.out.println("Please choose your customization options:");
        delayText();
        System.out.println("To use the default IntelliJ color options, Type 1");
        delayText();
        System.out.println("To access the text stylizing menu, Type 2 (\u001B[1mBold\u001B[0m, \u001B[4mUnderline\u001B[0m, \u001B[3mItalic\u001B[0m)");
        delayText();
        System.out.println("To access the " + color + " menu, Type 3");

    }

    public static void delayText(){
        // try-catch block used as Thread.sleep causes an error when written by itself
        try { // we write the code that might cause the exception in the try block.

            Thread.sleep(1000);

        } catch (InterruptedException e) { // InterruptedException = an interruption which causes the program to stop

            e.printStackTrace(); // e = variable for exception || printStackTrace = telling program to print a message that we were interrupted, and therefore bypass it as the program is aware

        }

    }

    public static void tokenize(){

        System.out.println("");

    }

    public void defaultColors(String[] inputFileName) throws FileNotFoundException{

        String[] keywords = readFile("keywordList.txt"); // compact all keywords into txt file then into array (i found a list)
        String[] dataTypes = readFile("dataTypeList.txt"); // compact all data types into txt file then into array (primitive/reference)
        String[] main = readFile(Arrays.toString(inputFileName)); //orange = \u001B[38;5;202m

        for(int i = 0; i < main.length; i++){

            for(int a = 0; a < keywords.length; a++){

                if(main[i].equals(keywords[a])){

                    main[i] = "\u001B[38;5;202m" + main[i]; //h

                }

            }

            for(int b = 0; b < dataTypes.length; b++){

                if(main[i].equals(dataTypes[b])){

                    main[i] = "\u001B[38;5;8m" + main[i];

                }

            }

        }



    }
    public void colorMenu(String[] inputFileName) throws FileNotFoundException {

        System.out.println("Please choose what you would like to customize.")
        delayText();
        System.out.println("Type the corresponding word below.\n1: Java Keywords\n2: Data Types\n3: String Literals\n4: Character Literals\n5: Numbers\n6: Comments\n7: Annotations");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        String[] keywords = readFile("keywordList.txt"); // compact all keywords into txt file then into array (i found a list)
        String[] dataTypes = readFile("dataTypeList.txt"); // compact all data types into txt file then into array (primitive/reference)
        String[] main = readFile(Arrays.toString(inputFileName));

        boolean hasKeywords = false;

        if (choice == 1) {

            for (int i = 0; i < main.length; i++) {

                for (int a = 0; a < keywords.length; a++) {

                    if ()
                }

            }
        }


    }

}
/*    public static String[] tokenizer(String inputFileName) throws FileNotFoundException{

        StringTokenizer st = new StringTokenizer("hello this is test");

        while (st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }

    }


} */