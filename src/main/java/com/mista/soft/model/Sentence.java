package com.mista.soft.model;

import java.util.List;

public class Sentence {
    String text;
    List<String> wordList;

    public Sentence(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }

    @Override
    public String toString() {
        return text;
    }
}
