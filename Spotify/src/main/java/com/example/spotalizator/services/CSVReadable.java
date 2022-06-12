package com.example.spotalizator.services;

import org.apache.commons.csv.CSVParser;

import java.util.List;

public interface CSVReadable {
    CSVParser parseCSV(String pathToFile);
    List<Float> getFieldSortedList(String field, CSVParser csvParser);
}
