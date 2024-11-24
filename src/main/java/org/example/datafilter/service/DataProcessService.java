package org.example.datafilter.service;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
public class DataProcessService {

    public List<Integer> processArray(List<List<String>> inputArray) {
        var validNumbers = inputArray.stream()
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .filter(this::isNumeric)
                .map(Integer::parseInt)
                .toList();

        return IntStream.range(0, validNumbers.size() / 3)
                .mapToObj(i -> validNumbers.subList(i * 3, (i * 3) + 3))
                .filter(group -> group.stream().mapToInt(Integer::intValue).sum() >= 90)
                .flatMap(List::stream)
                .toList();
    }

    private boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return value.matches("-?\\d+");
    }
}