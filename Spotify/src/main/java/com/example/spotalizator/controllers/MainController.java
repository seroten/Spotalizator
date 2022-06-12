package com.example.spotalizator.controllers;

import com.example.spotalizator.entities.DecileRangeStat;
import com.example.spotalizator.services.CSVReadable;
import com.example.spotalizator.services.DecileCalculatable;
import org.apache.commons.csv.CSVParser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    CSVReadable csvReaderService;
    DecileCalculatable cd;

    public MainController(CSVReadable csvReaderService, DecileCalculatable cd) {
        this.csvReaderService = csvReaderService;
        this.cd = cd;
    }

    @GetMapping("/")
    public @ResponseBody List<DecileRangeStat> main(@RequestParam String colname,
                                                    @RequestParam(required = false) String year) {
        CSVParser csvParser = csvReaderService.parseCSV("songs_normalize.csv");
        List<Float> list;
        if(year != null) {
            list = csvReaderService.getFieldSortedList(year, csvParser);
        } else {
            list = csvReaderService.getFieldSortedList(colname, csvParser);
        }
        return cd.getDecileList(list);
    }
}