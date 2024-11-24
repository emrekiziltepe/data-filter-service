package org.example.datafilter.controller;

import lombok.RequiredArgsConstructor;
import org.example.datafilter.service.DataProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/data")
@RequiredArgsConstructor
public class DataProcessController {

    private final DataProcessService dataProcessService;

    @PostMapping("/process")
    public ResponseEntity<List<Integer>> processData(@RequestBody List<List<String>> inputArray) {
        var result = dataProcessService.processArray(inputArray);
        return ResponseEntity.ok(result);
    }
}
