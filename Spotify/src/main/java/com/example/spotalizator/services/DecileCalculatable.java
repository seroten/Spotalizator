package com.example.spotalizator.services;

import com.example.spotalizator.entities.DecileRangeStat;

import java.util.List;

public interface DecileCalculatable {
    public List<DecileRangeStat> getDecileList(List<Float> dataList);
}
