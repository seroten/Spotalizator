package com.example.spotalizator.entities;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class DecileRangeStat {
    private float min;
    private float max;
    private int count;
}
