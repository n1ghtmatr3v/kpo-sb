package com.example.fileanalysis.service;

import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WordCloudService {

    public String buildWordCloudUrl(String text) {
        Map<String, Long> frequency = sanitize(text)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        String data = frequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(30)
                .map(entry -> String.format("['%s', %d]", escape(entry.getKey()), entry.getValue()))
                .collect(Collectors.joining(","));

        String config = String.format("{type:'wordCloud',data:{labels:[%s]}}", data);
        return "https://quickchart.io/chart?c=" + URLEncoder.encode(config, StandardCharsets.UTF_8);
    }

    private String escape(String input) {
        return input.replace("'", "\'");
    }

    private java.util.stream.Stream<String> sanitize(String text) {
        return text.toLowerCase().replaceAll("[^a-zа-я0-9 ]", " ").lines()
                .flatMap(line -> java.util.Arrays.stream(line.trim().split("\\s+")))
                .filter(word -> word.length() > 2);
    }
}
