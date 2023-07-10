package com.gmail.maxkhrebtov.mirror;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MirrorServiceImpl implements MirrorService {
    @Override
    public String mirrorText(String input) {

        return Optional.ofNullable(input)
                .stream()
                .flatMap(s -> Stream.of(s.split("\n")))
                .map(this::flipString)
                .collect(Collectors.joining("\n")); //collect blocks
    }

    private String flipString(String text)
    {
        text = text.toLowerCase();
        StringBuilder result = new StringBuilder();

        for (int i = text.length() - 1; i >= 0; --i)
        {
            // Flip and mirror the text.
            result.append(flipChar(text.charAt(i)));
        }

        return result.toString();
    }

    private char flipChar(char c)
    {
        return switch (c) {
            case 'a' -> 'ɒ';
            case 'b' -> 'd';
            case 'c' -> 'ↄ';
            case 'd' -> 'b';
            case 'e' -> 'ɘ';
            case 'f' -> 'ʇ';
            case 'g' -> 'ϱ';
            case 'h' -> '⑁';
            case 'j' -> 'ᒑ';
            case 'k' -> 'ʞ';
            case 'p' -> 'q';
            case 'q' -> 'p';
            case 'r' -> 'ɿ';
            case 's' -> 'ƨ';
            case 't' -> 'ɟ';
            case 'y' -> 'γ';
            case 'B' -> 'ᗺ';
            case 'C' -> 'Ɔ';
            case 'D' -> 'ᗡ';
            case 'E' -> 'Ǝ';
            case 'F' -> 'ꟻ';
            case 'G' -> 'ວ';
            case 'J' -> 'ᒐ';
            case 'K' -> 'ꓘ';
            case 'L' -> '⅃';
            case 'N' -> 'И';
            case 'P' -> 'ᑫ';
            case 'Q' -> 'Ϙ';
            case 'R' -> 'Я';
            case 'S' -> 'Ƨ';
            case '[' -> ']';
            case ']' -> '[';
            case '(' -> ')';
            case ')' -> '(';
            case '{' -> '}';
            case '}' -> '{';
            case '?' -> '⸮';
            default -> c;
        };
    }
}
