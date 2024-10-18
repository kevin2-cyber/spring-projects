package com.kimikevin.simple.web;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    static int getOccurrences(String source, String word) {
        String cleanedUpString = source.replaceAll("\\.", "");
        return (int) Arrays.stream(cleanedUpString.split(""))
                .filter(w -> w.equalsIgnoreCase(word))
                .count();
    }

    static List<Integer> getReversed(List<Integer> source) {
        List<Integer> reversed = new ArrayList<>(source);
        Collections.reverse(reversed);
        return reversed;
    }

    static double getAverage(List<Integer> source) {
        if (source == null || source.isEmpty()) {
            return 0;
        }
        return source
                .stream()
                .reduce(0, Integer::sum) / (double) source.size();
    }

    static List<String> findFiveOrFewer(String source) {
        return Arrays.stream(source.split(" "))
                .filter(word -> word.length() <= 5)
                .collect(Collectors.toList());
    }

    static List<Integer> getIndices(List<Integer> source, int target) {
        Map<Integer, Integer> sourceMap = new HashMap<>();
        for (int i = 0; i < source.size(); i++) {
            sourceMap.put(source.get(i), i);
        }
        for (int i = 0; i < source.size(); i++) {
            int solutionNumber = target - source.get(i);
            if (sourceMap.containsKey(solutionNumber) && sourceMap.get(solutionNumber) != i) {
                return Arrays.asList(i, sourceMap.get(solutionNumber));
            }
        }
        return Arrays.asList(-1, -1);
    }
}
