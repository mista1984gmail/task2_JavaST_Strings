package com.mista.soft.model;

import java.util.List;

public class TextAll {
    String text;
    List<Paragraph> paragraphList;

    public TextAll(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Paragraph> getParagraphList() {
        return paragraphList;
    }

    public void setParagraphList(List<Paragraph> paragraphList) {
        this.paragraphList = paragraphList;
    }

    @Override
    public String toString() {
        return text;
    }
}
