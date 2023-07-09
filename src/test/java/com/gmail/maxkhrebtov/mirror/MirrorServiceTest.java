package com.gmail.maxkhrebtov.mirror;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MirrorServiceTest {

    private final MirrorService mirrorService = new MirrorServiceImpl();

    @Test
    public void testMirror_null() {
        Assertions.assertEquals(StringUtils.EMPTY, mirrorService.mirrorText(null));
    }

    @Test
    public void testMirror_success() {
        String inputText = """
                This is an example of input text with commas and periods.
                The output text should have the same word order but all words should be reversed.
                Commas, periods, and spaces are the only delimiters allowed in this implementation.""";

        String expectedText = """
                .sdoirep dna sammoc htiw txet tupni fo elpmaxe na si sihT
                .desrever eb dluohs sdrow lla tub redro drow emas eht evah dluohs txet tuptuo ehT
                .noitatnemelpmi siht ni dewolla sretimiled ylno eht era secaps dna ,sdoirep ,sammoC""";
        String outputText = mirrorService.mirrorText(inputText);

        Assertions.assertEquals(expectedText, outputText);
    }
}
