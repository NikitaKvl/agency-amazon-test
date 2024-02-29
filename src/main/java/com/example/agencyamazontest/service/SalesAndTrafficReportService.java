package com.example.agencyamazontest.service;

import com.example.agencyamazontest.entity.SalesAndTrafficByAsin;
import com.example.agencyamazontest.entity.SalesAndTrafficByDate;
import java.io.IOException;
import java.util.List;

public interface SalesAndTrafficReportService {
    void initializeDatabase() throws IOException;

    SalesAndTrafficByDate findStatisticsByDate(String date);

    SalesAndTrafficByAsin findStatisticsByAsin(String parentAsin);

    List<SalesAndTrafficByDate> findAllSalesAndTrafficByDate();

    List<SalesAndTrafficByAsin> findAllSalesAndTrafficByAsin();
}
