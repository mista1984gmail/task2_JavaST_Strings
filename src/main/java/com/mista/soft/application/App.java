package com.mista.soft.application;

import com.mista.soft.model.Paragraph;
import com.mista.soft.model.Sentence;
import com.mista.soft.model.TextAll;
import com.mista.soft.service.TextServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
@Slf4j
public class App {
    public static final TextServiceImpl TEXT_SERVICE = new TextServiceImpl();
    public static String TEXT ="";
    public static void main(String[] args) {

        int userInput = 0;

        Scanner scanner = new Scanner(System.in);
        do {
            // instructions
            log.info("Enter 0 to exit the application");//exiting the application
            log.info("Enter 1 to enter file");//
            log.info("Enter 2 to show text info");//
            log.info("Enter 3 to show all text");//
            log.info("Enter 4 to show all sentences");//
            log.info("Enter 5 to show all words");//
            log.info("Enter 6 to sort sentences in ascending order of length");//task2JavaST Strings #2
            log.info("Enter 7 to sort words of the text starting with vowels in alphabetical order according to the first consonant of the word.");//task2JavaST Strings #8
            log.info("Enter 8 to print the words of the text in alphabetical order first\n" +
                    " letter. Words beginning with a new letter should be printed on the red line.");//task2JavaST Strings #6

            System.out.println("_______________________________________________________________________");
            // reading input
            userInput = scanner.nextInt();

            // choosing option
            switch (userInput) {
                case 0:
                    log.info("Goodbye!");
                    break;
                case 1:
                    enterFileWithText();
                    break;
                case 2:
                    TEXT_SERVICE.infoAboutText(TEXT);
                    break;
                case 3:
                    TEXT_SERVICE.showAllText(TEXT);
                    break;
                case 4:
                    showAllSentencesInText();
                    break;
                case 5:
                    showAllWordsInText();
                    break;
                case 6:
                    sortSentencesInAscendingOrderOfLength();
                    break;
                case 7:
                    sortWordsOfTextStartingWithVowels();
                    break;
                case 8:
                    printWords();
                    break;
                default:
                    log.info("There is no such option, please choose another option.");
            }

        } while (userInput != 0);
    }

    private static void printWords() {
        TextAll textAll = new TextAll(TEXT);
        TEXT_SERVICE.printWordsOnAlphabet(textAll);

    }

    private static void sortWordsOfTextStartingWithVowels() {
        TextAll textAll = new TextAll(TEXT);
        TEXT_SERVICE.sortWordsStartingWithVowels(textAll);
    }

    private static void sortSentencesInAscendingOrderOfLength() {
        TextAll textAll = new TextAll(TEXT);
        TEXT_SERVICE.sortSentencesOfLength(textAll);
    }

    private static void showAllWordsInText() {
        TextAll textAll = new TextAll(TEXT);
        TEXT_SERVICE.showAllWords(textAll);
    }

    private static void showAllSentencesInText() {
        TextAll textAll = new TextAll(TEXT);
        TEXT_SERVICE.showAllSentence(textAll);
    }

    private static void enterFileWithText() {
        Scanner scannerForFile = new Scanner(System.in);
        log.info("Enter file path or use the default file and then type \"default\"");
        //C:\\Users\\USER\\Desktop\\text.txt
        String path=scannerForFile.nextLine();
        if (path.equals("default")){
            String PATH = "src\\main\\java\\com\\mista\\soft\\resources\\text.txt";
            TEXT=initialization(PATH);
        }
        else {
            TEXT=initialization(path);
        }
    }

    private static String initialization(final String path) {
        String text = "";
        try {
            FileInputStream inFile = new FileInputStream(path);
            byte[] str = new byte[inFile.available()];
            inFile.read(str);
            text = new String(str); // String with all text
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("File downloaded");
        return text;
    }
}
