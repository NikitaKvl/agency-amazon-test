package com.example.agencyamazontest.service.impl;

import com.example.agencyamazontest.entity.SalesAndTrafficByAsin;
import com.example.agencyamazontest.entity.SalesAndTrafficByDate;
import com.example.agencyamazontest.entity.SalesAndTrafficReport;
import com.example.agencyamazontest.repository.SalesAndTrafficReportRepository;
import com.example.agencyamazontest.service.SalesAndTrafficReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class SalesAndTrafficReportServiceImpl implements SalesAndTrafficReportService {
    private static final String filePath = "src/main/resources/file/test_report.json";
    private static final String REPORT_TYPE = "GET_SALES_AND_TRAFFIC_REPORT";
    private static final String DATA_START_TIME = "2024-02-14";
    private final SalesAndTrafficReportRepository reportRepository;
    private final ObjectMapper objectMapper;
    private boolean isInitialDataLoaded = false;

    @Cacheable(value = "salesAndTrafficByDate", key = "#date")
    @Override
    public SalesAndTrafficByDate findStatisticsByDate(String date) {
        List<SalesAndTrafficReport> reports = reportRepository.findReportsBySalesAndTrafficByDate(date);
        for (SalesAndTrafficReport report : reports) {
            for (SalesAndTrafficByDate salesAndTrafficByDate : report.getSalesAndTrafficByDate()) {
                if (date.equals(salesAndTrafficByDate.getDate())) {
                    return salesAndTrafficByDate;
                }
            }
        }
        return null;
    }

    @Cacheable(value = "salesAndTrafficByAsin", key = "#parentAsin")
    @Override
    public SalesAndTrafficByAsin findStatisticsByAsin(String parentAsin) {
        List<SalesAndTrafficReport> reports = reportRepository.findStatisticsByAsin(parentAsin);
        for (SalesAndTrafficReport report : reports) {
            for (SalesAndTrafficByAsin salesAndTrafficByAsin : report.getSalesAndTrafficByAsin()) {
                if (parentAsin.equals(salesAndTrafficByAsin.getParentAsin())) {
                    return salesAndTrafficByAsin;
                }
            }
        }
        return null;
    }

    @Cacheable(value = "salesAndTrafficByDates")
    @Override
    public List<SalesAndTrafficByDate> findAllSalesAndTrafficByDate() {
        System.out.println("Call method findAllSalesAndTrafficByDate");
        SalesAndTrafficReport report = reportRepository.findAll().stream().findFirst().orElseThrow();
        return report.getSalesAndTrafficByDate();
    }

    @Cacheable(value = "salesAndTrafficByAsins")
    @Override
    public List<SalesAndTrafficByAsin> findAllSalesAndTrafficByAsin() {
        SalesAndTrafficReport report = reportRepository.findAll().stream().findFirst().orElseThrow();
        return report.getSalesAndTrafficByAsin();
    }

    @Override
    @Scheduled(fixedRate = 300000)
    public void initializeDatabase() throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
        SalesAndTrafficReport salesAndTrafficReport = objectMapper.readValue(jsonString, SalesAndTrafficReport.class);
        if (!isInitialDataLoaded) {
            reportRepository.save(salesAndTrafficReport);
            isInitialDataLoaded = true;
        } else {
            SalesAndTrafficReport salesAndTrafficReportByReportTypeAndDataStart
                    = reportRepository.findSalesAndTrafficReportByReportTypeAndDataStart(REPORT_TYPE, DATA_START_TIME);
            salesAndTrafficReport.setId(salesAndTrafficReportByReportTypeAndDataStart.getId());
            reportRepository.save(salesAndTrafficReport);
        }
    }

    @Scheduled(fixedRate = 300000)
    @CacheEvict(value = {"salesAndTrafficByAsin", "salesAndTrafficByDate",
            "salesAndTrafficByAsins", "salesAndTrafficByDates"}, allEntries = true)
    public void evictCache() {
    }
}
