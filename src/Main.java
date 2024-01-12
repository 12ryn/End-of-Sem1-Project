import java.io.*;
import java.util.*;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;


// Ryan Li
// Date of File Creation: 5th December 2023
// 11.11 Computer Science End of Semester Project
// So far Ryan Li is the only collaborator
// This project takes a user file, highlights and customizes the contents with color and text styles according to the user's preferences, and prints out the changed content.

public class Main {
    public static final String RESET = "\u001B[0m"; // "final" makes the variable universal and can be used anywhere in the code without more declaration & intialization
    public static final String BOLD = "\u001B[1m";
    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String BLACK = "\u001B[38;2;0;0;0m";
    public static final String RED = "\u001B[38;2;255;0;0m";
    public static final String ORANGE = "\u001B[38;2;255;165;0m";
    public static final String GREEN = "\u001B[38;2;0;255;0m";
    public static final String YELLOW = "\u001B[38;2;255;255;0m";
    public static final String BLUE = "\u001B[38;2;0;0;255m";
    public static final String MAGENTA = "\u001B[38;2;255;0;255m";
    public static final String CYAN = "\u001B[38;2;0;255;255m";
    public static final String WHITE = "\u001B[38;2;255;255;255m";
    public static final String iJKeywordsDataTypes = "\u001B[38;2;208;140;107m"; // orange thing
    public static final String iJBlue = "\u001B[38;2;88;172;244m"; // blue ish thing (method names but might not be used since hard to identify)
    public static final String iJComments = "\u001B[38;2;128;124;132"; // color of this very comment (sad af gray)
    public static final String iJStringCharLiteral = "\u001B[38;2;112;172;116"; // color of whenever there's anything in closed quotation marks ('single' or "double")
    public static final String iJNumbers = "\u001B[38;2;40;156;192"; // the very rare aqua color
    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to the Syntax Highlighter. This program takes your chosen file, highlights and customizes the contents with color and text styles according to the your preferences, printing out the changed content.");
        delayText();
        System.out.println("Please enter your correct and existing file path.");
        delayText();
        System.out.println("For example (Mac), \"\u001B[34m/Users/myName/Downloads/textName.txt\u001B[0m\"");
        delayText();
        System.out.println("For example (Windows), \"\u001B[35mC:/Users/explo/Downloads/textName.txt\u001B[0m\"");
        delayText();
        System.out.println("Make sure you allow IntelliJ to access your Downloads! I promise you this is not a virus");

        Scanner scanner = new Scanner(System.in);
        String srcFile = scanner.nextLine();

        String[] finalFile = readFile(srcFile);

        // /Users/rgl/Downloads/cs4.txt

        delayText();

        menuChoice();

        System.out.println(BOLD + "File exists. You may proceed." + RESET);

        System.out.println("Code success!");

        tokenize("/Users/rgl/IdeaProjects/RGL Syntax Highlighter/testDoc");

    }

    public static void tokensDefault() throws FileNotFoundException {

        boolean isKeyword = true;

        BufferedReader bR = null;
        Scanner scanner = new Scanner(System.in);

        String[] keywords = readFile("/Users/rgl/IdeaProjects/RGL Syntax Highlighter/keywordList");

        try{

            System.out.println("Enter file path: ");
            String filePath = scanner.next();

            bR = new BufferedReader(new FileReader(filePath));
            StringBuilder sB = new StringBuilder();

            // initialize BufferedReader to read the file given by the user

            String line = bR.readLine();

            while(!line.isEmpty()){

                StringTokenizer sT = new StringTokenizer(line);

                while(sT.hasMoreTokens()){

                    String token = sT.nextToken();

                    if(token.charAt(token.length()-1) != ';'){

                        for (int a = 0; a < keywords.length; a++) {

                            if (token.equals(keywords[a])) {

                                token = iJKeywordsDataTypes + keywords[a] + RESET;

                            }

                        }

                    }




                }

            }




        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }


    public static void delayText() {

        // try-catch block used as Thread.sleep causes an error when written by itself
        try { // we write the code that might cause the exception in the try block.

            Thread.sleep(1000);

        } catch (InterruptedException e) { // InterruptedException = an interruption which causes the program to stop

            e.printStackTrace(); // e = variable for exception || printStackTrace = telling program to print a message that we were interrupted, and therefore bypass it as the program is aware

        }

    }

    public static void tokenize(String file) throws IOException {

        List<String> lines = readLines(file); // separates and puts lines of the input file into a list
        List<String> tokenized = tokenizeToLines(lines);
        printLines(tokenized);

    }

    public static List<String> readLines(String file) throws IOException { // separates and puts lines of a file into a list

        List<String> lines = new ArrayList<>(); // create new 2d array (a list) which stores arrays (array of arrays)

        BufferedReader br = new BufferedReader(new FileReader(file)); // (More efficient way of reading files through bR and fR)

        String line; // declares a String object - to use in the loop later

        while ((line = br.readLine()) != null) { // under the condition that a line of words exist in the file as the bufferedReader goes down the file

            lines.add(line); // add the entire line to the array of arrays (lines is the name of the list, line is the temporary name we use to name the actual line of words)

        }

        return lines; // the list is made

    }

    public static List<String> tokenizeToLines(List<String> lines) throws FileNotFoundException { // we give this file the list made by the readLines method

        List<String> tokenized = new ArrayList<>(); // create a new list

        for (String line : lines) { // declare a new string with a temporary name holder "line" within the given list of "lines" from the readLines method

            String tokens = tokenizeToWords(line); // tokenize each line indo words through tokenizeLine method (we are tokenizing "line")
            tokenized.add(tokens); // add each token to the newly created list

        }

        return tokenized;

    }

    public static String tokenizeToWords(String line) throws FileNotFoundException { // we are given a line of words from the tokenizeLines method

        String[] tokenizer = line.split("\\s+"); // splits line String object, \\s+ denotes the delimiter as any blank space
        StringBuilder sb = new StringBuilder(); // create new StringBuilder object, "builds" string

        String[] keywords = readFile("/Users/rgl/IdeaProjects/RGL Syntax Highlighter/keywordList");

        for (String tokens : tokenizer) {

            sb.append(tokens).append(" ");

        }

        // the part where we go through EVERY WORD *CRUCIAL* AND ALLOCATE EVERY COLOR

        for (int i = 0; i < tokenizer.length - 1; i++) { // enhanced version of "for(int i = 0; i < tokenizer.length; i++)" temporarily set each element in array tokenizer[] for comparison (enhanced for loop only can be used in arrays)

            for (int a = 0; a < keywords.length; a++) {

                if (tokenizer[i].equals(keywords[i])) {

                    tokenizer[i] = iJKeywordsDataTypes + tokenizer[i] + RESET;
                    sb.append(tokenizer[i]).append(" ");

                } else {

                    sb.append(tokenizer[i]).append(" ");

                }

            }


            if (intChecker(tokenizer[i])) { // intChecker(tokenizer[i]) == true is simplified, do not need the "== true"

                tokenizer[i] = iJNumbers + tokenizer[i] + RESET;
                sb.append(tokenizer[i]).append(" ");

            } else {

                sb.append(tokenizer[i]).append(" ");

            }

            String last = tokenizer[tokenizer.length - 1];

            if (!last.isEmpty() && last.charAt(last.length() - 1) == ';') {

                last = last.substring(0, last.length() - 1);

                if (intChecker(last)) { // intChecker(tokenizer[i]) == true is simplified, do not need the "== true"

                    last = iJNumbers + last + RESET;
                    sb.append(last).append(";");

                } else {

                    sb.append(last).append(";");

                }


            }


            return sb.toString();


            // check last token of line has a ";" as we will need to first remove that before being able to code


        }

        return line;

    }
    public static boolean intChecker(String str) {

        try { // attempt to change the string to an int

            Integer.parseInt(str);

            return true; // if it works return true;

        } catch (NumberFormatException e) { // and if it cannot be parsed to an int, "catch" and evade this exception where the string cannot reformat properly; allow the code to run

            return false; // it doesn't work so return false

        }
    }
    public static void printLines (List<String> lines) {

        for (String line : lines) {
            System.out.println(line);
        }

    }

    public static int countLinesInFile (String inputFileName) throws FileNotFoundException {

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
    public static String[] readFile (String inputFileName) throws FileNotFoundException {

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

    public static void menuChoice() throws FileNotFoundException {

        menuMessage();

        Scanner scanner = new Scanner(System.in);

        int menuChoice = scanner.nextInt();

        if(menuChoice == 1){

            tokensDefault();

        } else if (menuChoice == 2){

            // textStylingMethod();

        } else if(menuChoice == 3){

            // coloringMethod();

        } else {

            System.out.println("Invalid choice. Please pick again.");

        }

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

    /* public static void defaultColors(String[] inputFileName) throws FileNotFoundException{

        String[] keywords = readFile("keywordList.txt"); // compact all keywords into txt file then into array (i found a list)
        String[] dataTypes = readFile("dataTypeList.txt"); // compact all data types into txt file then into array (primitive/reference)
        String[] main = readFile(Arrays.toString(inputFileName)); //orange = \u001B[38;5;202m
        String delimiter = " "; // so tokenizer knows when to split words into tokens

        for(int i = 0; i < main.length; i++){

            StringTokenizer token = new StringTokenizer(main[i], delimiter);
            String[] temp = new String[token.countTokens()];


            while(token.hasMoreTokens()){

                temp[]

            }

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

    public static void colorMenu(String[] inputFileName) throws FileNotFoundException {

        System.out.println("Please choose what you would like to customize.");
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


    } */


}







