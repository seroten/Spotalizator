package com.example.spotalizator.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CSVReaderService implements CSVReadable{

    @Override
    public CSVParser parseCSV(String pathToFile) {
        CSVParser csvParser = null;
        try {
            Resource resource = new ClassPathResource(pathToFile);
            File file = resource.getFile();

            Reader in = Files.newBufferedReader(Paths.get(file.getPath()));
            csvParser = new CSVParser(in, CSVFormat.DEFAULT
                    .withHeader("artist", "song", "duration_ms", "explicit", "year", "popularity",
                            "danceability", "energy", "key", "loudness", "mode", "speechiness",
                            "acousticness", "instrumentalness", "liveness", "valence", "tempo", "genre")
                    .withIgnoreHeaderCase()
                    .withTrim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvParser;
    }

    @Override
    public List<Float> getFieldSortedList(String field, CSVParser csvParser) {
        List<Float> fieldList = new ArrayList<>();
        boolean firstRecord = true;
        for (CSVRecord record : csvParser) {
            if(firstRecord) {
                firstRecord = false;
                continue;
            }
            fieldList.add(Float.parseFloat(record.get(field)));
        }
        Collections.sort(fieldList);
        return fieldList;
    }
}
//    duration_ms
//            year
//    popularity
//            danceability
//    energy
//            key
