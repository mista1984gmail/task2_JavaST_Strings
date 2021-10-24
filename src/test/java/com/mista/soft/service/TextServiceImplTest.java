package com.mista.soft.service;


import com.mista.soft.model.Paragraph;
import com.mista.soft.model.Sentence;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class TextServiceImplTest {

    @Test
   public void testSplitTextIntoParagraphs() {
        log.info("Test for 'SplitTextIntoParagraphs' are started.");
        //GIVEN
        TextServiceImpl service = new TextServiceImpl();
        String text ="Hello Java! \n Hello world!";

        // WHEN
        List<Paragraph> listParagraph = service.splitTextIntoParagraphs(text);
        int lenghtListExpected=2;

        int lenghtListTest=listParagraph.size();

        // THEN
        Assert.assertEquals(lenghtListExpected,lenghtListTest);
        log.info("Test for 'SplitTextIntoParagraphs' are finished.");
        log.info("*****************************************************************************");
    }

    @Test
    public void testSplitTextIntoSentences() {
        log.info("Test for 'SplitTextIntoSentences' are started.");
        //GIVEN
        TextServiceImpl service = new TextServiceImpl();
        String text ="Hello Java! Hello world!";

        // WHEN
        List<Paragraph> listParagraph = service.splitTextIntoParagraphs(text);
        List<Sentence> listSentences = service.splitTextIntoSentences(listParagraph.get(0));
        int lenghtListExpected=2;

        int lenghtListTest=listSentences.size();

        // THEN
        Assert.assertEquals(lenghtListExpected,lenghtListTest);
        log.info("Test for 'SplitTextIntoSentences' are finished.");
        log.info("*****************************************************************************");
    }
    @Test
    public void testSplitSentenceIntoWords() {
        log.info("Test for 'SplitSentenceIntoWords' are started.");
        //GIVEN
        TextServiceImpl service = new TextServiceImpl();
        String text ="Hello Java! Hello world!";

        // WHEN
        List<Paragraph> listParagraph = service.splitTextIntoParagraphs(text);
        List<Sentence> listSentences = service.splitTextIntoSentences(listParagraph.get(0));
        List<String>listWords=service.splitSentenceIntoWords(listSentences.get(0));
        int lenghtListExpected=2;

        int lenghtListTest=listWords.size();

        // THEN
        Assert.assertEquals(lenghtListExpected,lenghtListTest);
        log.info("Test for 'SplitSentenceIntoWords' are finished.");
        log.info("*****************************************************************************");
    }



}
