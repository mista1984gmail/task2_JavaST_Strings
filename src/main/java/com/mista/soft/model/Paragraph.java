package com.mista.soft.model;

import java.util.List;

public class Paragraph {
    String text;
    List<Sentence> sentenceList;

    public Paragraph(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }

    @Override
    public String toString() {
        return text;
    }
}
