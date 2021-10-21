package com.mista.soft.service;

import com.mista.soft.model.Paragraph;
import com.mista.soft.model.Sentence;
import com.mista.soft.model.TextAll;
import jdk.nashorn.internal.runtime.regexp.RegExp;
import jdk.nashorn.internal.runtime.regexp.RegExpMatcher;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextServiceImpl implements TextService{
    private static final Logger LOGGER = LoggerFactory.getLogger(TextServiceImpl.class);
    @Override
    public List<Paragraph> splitTextIntoParagraphs(String text) {
        //LOGGER.debug("Trying to fill in the text into paragraphs");
        String [] mussParagraph= text.split("\n");//divide into paragraphs
        List<Paragraph> listParagraph = new ArrayList<>();

        for (int i = 0; i < mussParagraph.length; i++) {
            listParagraph.add(new Paragraph(mussParagraph[i]));
        }
        //LOGGER.debug("Divided the text into paragraphs");
        return listParagraph;
    }

    @Override
    public List<Sentence> splitTextIntoSentences(Paragraph paragraph) {
        //LOGGER.debug("Trying divide paragraphs into sentences");
        String paragraphText = paragraph.getText();
        paragraphText=paragraphText.replaceAll("[-.?!)(,:]{0,3}$","");
        paragraphText=paragraphText.replaceAll("[\\s]+$","");
        String [] mussSentence= paragraphText.split("[.]|[!]|[?]");//divide into paragraph
        List<Sentence> listSentence = new ArrayList<>();

        for (int i = 0; i < mussSentence.length; i++) {
            listSentence.add(new Sentence(mussSentence[i].replaceFirst("^\\s++","")));
        }
        //LOGGER.debug("Divided paragraphs into sentences");
        return listSentence;
    }

    @Override
    public List<String> splitSentenceIntoWords(Sentence sentence) {
        //LOGGER.debug("Trying divide sentences into words");
        String sentenceText = sentence.getText();
        sentenceText=sentenceText.replaceAll("[-.?!)(,:]{0,1}$","");//remove the last punctuation mark
        String [] arrayString=sentenceText.split("[-.?!)(,:]{0,1}\\s");//divide into words
        List<String> listWord = new ArrayList<>();
        for (int i = 0; i < arrayString.length; i++) {
            listWord.add(arrayString[i]);
        }
        //LOGGER.debug("Split sentences into words");
        return listWord;
    }

    @Override
    public void infoAboutText(String text) {
        int numberOfParagraphs=0;
        int numberOfSentences=0;
        int numberOfWords=0;
        LOGGER.info("Info about text.");
        List<Paragraph> paragraphList=splitTextIntoParagraphs(text);
        numberOfParagraphs=paragraphList.size();

        for (Paragraph paragraph:paragraphList) {
            List<Sentence>sentenceList=splitTextIntoSentences(paragraph);
            numberOfSentences+=sentenceList.size();
            for (Sentence sentence:sentenceList) {
                List<String>stringList=splitSentenceIntoWords(sentence);
                numberOfWords+= stringList.size();
            }
        }
        LOGGER.info("Paragraphs in text {}.",numberOfParagraphs);
        LOGGER.info("Sentences in text {}.",numberOfSentences);
        LOGGER.info("Words in text {}.",numberOfWords);
    }

    @Override
    public void showAllText(String text) {
        LOGGER.info("All Text.");
        TextAll textAll = new TextAll(text);
        System.out.println((textAll.getText()));
    }

    @Override
    public void showAllSentence(TextAll textAll) {
        LOGGER.info("All sentences.");
        String string = textAll.getText();

        List<Paragraph> paragraphList=splitTextIntoParagraphs(string);

        for (Paragraph paragraph:paragraphList) {
            List<Sentence>sentenceList=splitTextIntoSentences(paragraph);
            sentenceList.stream().forEach(c-> System.out.println(c));

        }
    }

    @Override
    public void showAllWords(TextAll textAll) {
        LOGGER.info("All words.");
        String string = textAll.getText();
        List<Paragraph> paragraphList=splitTextIntoParagraphs(string);
        for (Paragraph paragraph:paragraphList) {
            List<Sentence>sentenceList=splitTextIntoSentences(paragraph);

            for (Sentence sentence:sentenceList) {
                List<String>stringList=splitSentenceIntoWords(sentence);

                stringList.stream().forEach(c-> System.out.println(c));
            }
        }
    }

    @Override
    public void sortSentencesOfLength(TextAll textAll) {
        LOGGER.info("Sort sentences in ascending order of length.");
        String string = textAll.getText();
        List<Paragraph> paragraphList=splitTextIntoParagraphs(string);
        List<Sentence>allSentencesList=new ArrayList<>();
        for (Paragraph paragraph:paragraphList) {
            List<Sentence>sentenceList=splitTextIntoSentences(paragraph);
            for (Sentence sentence:sentenceList) {
                List<String>stringList=splitSentenceIntoWords(sentence);
                sentence.setWordList(stringList);
                allSentencesList.add(sentence);
            }
        }
        Collections.sort(allSentencesList, new Comparator<Sentence>() {
            @Override
            public int compare(Sentence o1, Sentence o2) {
                if (o1.getWordList().size() > o2.getWordList().size()){
                    return 1;
                } else if (o1.getWordList().size() < o2.getWordList().size()) {
                    return -1;
                } else {
                    return 0;
                }
            }
    });
        allSentencesList.stream().forEach(c-> System.out.println(c));
    }

    @Override
    public void sortWordsStartingWithVowels(TextAll textAll) {
        LOGGER.info("Sort words of the text starting with vowels in alphabetical order according to the first consonant of the word.");
        String string = textAll.getText();
        List<Paragraph> paragraphList=splitTextIntoParagraphs(string);
        List<String>allWordsList=new ArrayList<>();

        for (Paragraph paragraph:paragraphList) {
            List<Sentence>sentenceList=splitTextIntoSentences(paragraph);
            for (Sentence sentence:sentenceList) {
                List<String>stringList=splitSentenceIntoWords(sentence);
                for (String str:stringList) {
                    allWordsList.add(str.toLowerCase(Locale.ROOT));
                }
            }
        }
        Pattern pattern = Pattern.compile("^[AEIOUYaeiouy]");
        List<String>textWithWordsOnlyVowels=new ArrayList<>();
        for (String word : allWordsList) {
            Matcher matcher = pattern.matcher(word);
            if (matcher.find()) {
                textWithWordsOnlyVowels.add(word);
            }
        }
        Collections.sort(textWithWordsOnlyVowels);

        textWithWordsOnlyVowels.stream().forEach(c-> System.out.println(c));

    }

    @Override
    public void removeFromTextWords(TextAll textAll, int lenghtWord) {
        LOGGER.info("remove from the text all words of a given length beginning with a consonant letter.");
        String string = textAll.getText();

        List<Paragraph> paragraphList=splitTextIntoParagraphs(string);
        List<String>allWordsList=new ArrayList<>();

        for (Paragraph paragraph:paragraphList) {
            List<Sentence>sentenceList=splitTextIntoSentences(paragraph);
            for (Sentence sentence:sentenceList) {
                List<String>stringList=splitSentenceIntoWords(sentence);
                for (String str:stringList) {
                    allWordsList.add(str);
                }
            }
        }
        Pattern pattern = Pattern.compile("^[AEIOUYaeiouy]");
        List<String>textWithWordsOnlyVowels=new ArrayList<>();
        for (String word : allWordsList) {
            Matcher matcher = pattern.matcher(word);
            if (matcher.find()) {
                textWithWordsOnlyVowels.add(word);
            }
        }

        List<String>listForDelete=new ArrayList<>();
        for (String word : textWithWordsOnlyVowels) {
            if (word.length()==lenghtWord) {
                listForDelete.add(word);
            }
        }
        listForDelete.stream().forEach(c-> System.out.println(c));
        StringBuilder sb = new StringBuilder(string);
        for (String str:listForDelete) {
            System.out.println(str);
            replaceAll(sb," "+str,"");
        }
        System.out.println(sb);
    }



    @Override
    public void replaceAll(StringBuilder builder, String from, String to) {
        int index = builder.indexOf(from);
        while (index != -1)
        {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
        }
    }

    @Override
    public void printWordsOnAlphabet(TextAll textAll) {
        LOGGER.info("All words of the text in alphabetical order first letter. \n" +
                " Words beginning with a new letter should be printed on the red line.");
        String string = textAll.getText();
        List<Paragraph> paragraphList=splitTextIntoParagraphs(string);
        List<String>allWordsList=new ArrayList<>();

        for (Paragraph paragraph:paragraphList) {
            List<Sentence>sentenceList=splitTextIntoSentences(paragraph);
            for (Sentence sentence:sentenceList) {
                List<String>stringList=splitSentenceIntoWords(sentence);
                for (String str:stringList) {
                    allWordsList.add(str.toLowerCase(Locale.ROOT));
                }
            }
        }

        Collections.sort(allWordsList);

        String firstLetter = allWordsList.get(0).substring(0,1);

        for (int i = 0; i < allWordsList.size(); i++) {

            if (firstLetter.equals(allWordsList.get(i).substring(0,1))){
                System.out.print(allWordsList.get(i)+", ");
            }
            else{
                System.out.println();
                System.out.print(allWordsList.get(i)+", ");
                firstLetter = allWordsList.get(i).substring(0,1);
            }
        }
        System.out.println();
    }
}
