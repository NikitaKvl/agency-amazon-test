package com.example.agencyamazontest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportOptions {
    private String dateGranularity;
    private String asinGranularity;
}

