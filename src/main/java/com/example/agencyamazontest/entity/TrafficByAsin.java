package com.example.agencyamazontest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficByAsin {
    private Integer browserSessions;
    private Integer browserSessionsB2B;
    private Integer mobileAppSessions;
    private Integer mobileAppSessionsB2B;
    private Integer sessions;
    private Integer sessionsB2B;
    private Double browserSessionPercentage;
    private Integer browserSessionPercentageB2B;
    private Double mobileAppSessionPercentage;
    private Integer mobileAppSessionPercentageB2B;
    private Double sessionPercentage;
    private Integer sessionPercentageB2B;
    private Integer browserPageViews;
    private Integer browserPageViewsB2B;
    private Integer mobileAppPageViews;
    private Integer mobileAppPageViewsB2B;
    private Integer pageViews;
    private Integer pageViewsB2B;
    private Double browserPageViewsPercentage;
    private Integer browserPageViewsPercentageB2B;
    private Double mobileAppPageViewsPercentage;
    private Integer mobileAppPageViewsPercentageB2B;
    private Double pageViewsPercentage;
    private Integer pageViewsPercentageB2B;
    private Integer buyBoxPercentage;
    private Integer buyBoxPercentageB2B;
    private Double unitSessionPercentage;
    private Integer unitSessionPercentageB2B;
}
