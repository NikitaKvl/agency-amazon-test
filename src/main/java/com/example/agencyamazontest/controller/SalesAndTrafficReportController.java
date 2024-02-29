package com.example.agencyamazontest.controller;

import com.example.agencyamazontest.entity.SalesAndTrafficByAsin;
import com.example.agencyamazontest.entity.SalesAndTrafficByDate;
import com.example.agencyamazontest.service.SalesAndTrafficReportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class SalesAndTrafficReportController {
    private final SalesAndTrafficReportService reportService;

    @GetMapping("/statistics-by-date")
    public SalesAndTrafficByDate findStatisticsByDate(@RequestParam String date) {
        return reportService.findStatisticsByDate(date);
    }

    @GetMapping("/statistics-by-asin/{asin}")
    public ResponseEntity<SalesAndTrafficByAsin> findStatisticsByAsin(
            @PathVariable(name = "asin") String asin) {
        SalesAndTrafficByAsin statisticsByAsin = reportService.findStatisticsByAsin(asin);
        return statisticsByAsin != null ? ResponseEntity.ok(statisticsByAsin) : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-date")
    public List<SalesAndTrafficByDate> findAllByDate() {
        return reportService.findAllSalesAndTrafficByDate();
    }

    @GetMapping("/by-asin")
    public List<SalesAndTrafficByAsin> findAllByAsin() {
        return reportService.findAllSalesAndTrafficByAsin();
    }
}
