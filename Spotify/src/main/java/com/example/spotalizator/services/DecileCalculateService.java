package com.example.spotalizator.services;

import com.example.spotalizator.entities.DecileRangeStat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DecileCalculateService implements DecileCalculatable {

    //    Di = (N + 1) * i / 10  - decile`s formula
    @Override
    public List<DecileRangeStat> getDecileList(List<Float> dataList) {
        List<DecileRangeStat> list = new ArrayList<>();
        int currentDi;
        int previousDi = 0;
        for(int i = 1; i <= 10; i++) {
            if(i == 10) {
                currentDi = dataList.size()-1;
            } else {
                currentDi = (dataList.size() + 1) * i/10;
            }
            DecileRangeStat decileRangeStat = new DecileRangeStat();
            decileRangeStat.setMin(dataList.get(previousDi));
            decileRangeStat.setMax(dataList.get(currentDi));
            decileRangeStat.setCount(getCount(previousDi, currentDi));
            previousDi = currentDi;
            list.add(decileRangeStat);
        }
        return list;
    }

    private int getCount(int previousDn, int currentDn) {
        int count = 0;
        for (int i = previousDn; i < currentDn; i++) {
            count++;
        }
        return count;
    }
}