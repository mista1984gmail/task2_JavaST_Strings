package com.mista.soft.service;

import com.mista.soft.model.Paragraph;
import com.mista.soft.model.Sentence;
import com.mista.soft.model.TextAll;

import java.util.List;

public interface TextService {
    List<Paragraph> splitTextIntoParagraphs(String text);
    List<Sentence> splitTextIntoSentences (Paragraph paragraph);
    List<String> splitSentenceIntoWords(Sentence sentence);
    void infoAboutText(String text);
    void showAllText(String text);
    void showAllSentence(TextAll textAll);
    void showAllWords(TextAll textAll);
    void sortSentencesOfLength(TextAll textAll);
    void sortWordsStartingWithVowels(TextAll textAll);
    void replaceAll(StringBuilder builder, String from, String to);
    void printWordsOnAlphabet(TextAll textAll);

}
