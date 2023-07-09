package com.gmail.maxkhrebtov.mirror;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MirrorServiceImpl implements MirrorService {
    @Override
    public String mirrorText(String input) {

        return Optional.ofNullable(input)
                .stream()
                .flatMap(s -> Stream.of(s.split("\n")))
                .map(StringUtils::reverse)
                .collect(Collectors.joining("\n")); //collect blocks
    }
}
