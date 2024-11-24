package org.example.datafilter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataProcessServiceTest {

    @InjectMocks
    private DataProcessService dataProcessService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessArray_validInput() {
        // given
        List<List<String>> input = Arrays.asList(
                Arrays.asList("0", "s1", null, "35", "90", "60"),
                Arrays.asList("ttt", null, null, "15"),
                Arrays.asList("75", "95", "0", "0", null, "ssss", "0", "-15"),
                Arrays.asList("25", "fgdfg", "", "105", "dsfdsf", "-5")
        );

        // when
        var result = dataProcessService.processArray(input);

        // then
        var expected = List.of(0, 35, 90, 60, 15, 75, 95, 0, 0);
        assertEquals(expected, result);
    }

    @Test
    public void testProcessArray_emptyInput() {
        // given
        List<List<String>> input = List.of();

        // when
        var result = dataProcessService.processArray(input);

        // then
        List<Integer> expected = List.of();
        assertEquals(expected, result);
    }

    @Test
    public void testProcessArray_noValidGroups() {
        // given
        var input = List.of(
                List.of("0", "10", "0"),
                List.of("20")
        );

        // when
        var result = dataProcessService.processArray(input);

        // then
        List<Integer> expected = List.of();
        assertEquals(expected, result);
    }

    @Test
    public void testProcessArray_singleGroupValid() {
        // given
        var input = List.of(
                List.of("50", "60", "40")
        );

        // when
        var result = dataProcessService.processArray(input);

        // then
        var expected = List.of(50, 60, 40);
        assertEquals(expected, result);
    }
}
