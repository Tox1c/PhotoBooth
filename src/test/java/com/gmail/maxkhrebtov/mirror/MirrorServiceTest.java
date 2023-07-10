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
                .ƨboiɿɘq bnɒ ƨɒmmoↄ ⑁ɟiw ɟxɘɟ ɟuqni ʇo ɘlqmɒxɘ nɒ ƨi ƨi⑁ɟ
                .bɘƨɿɘvɘɿ ɘd bluo⑁ƨ ƨbɿow llɒ ɟud ɿɘbɿo bɿow ɘmɒƨ ɘ⑁ɟ ɘvɒ⑁ bluo⑁ƨ ɟxɘɟ ɟuqɟuo ɘ⑁ɟ
                .noiɟɒɟnɘmɘlqmi ƨi⑁ɟ ni bɘwollɒ ƨɿɘɟimilɘb γlno ɘ⑁ɟ ɘɿɒ ƨɘↄɒqƨ bnɒ ,ƨboiɿɘq ,ƨɒmmoↄ""";
        String outputText = mirrorService.mirrorText(inputText);
        System.out.println(outputText);
        Assertions.assertEquals(expectedText, outputText);
    }
}
