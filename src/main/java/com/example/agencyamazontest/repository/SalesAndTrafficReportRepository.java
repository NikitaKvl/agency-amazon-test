package com.example.agencyamazontest.repository;

import com.example.agencyamazontest.entity.SalesAndTrafficReport;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesAndTrafficReportRepository extends MongoRepository<SalesAndTrafficReport, String> {
    @Query("{ 'salesAndTrafficByDate.date' : ?0 }")
    List<SalesAndTrafficReport> findReportsBySalesAndTrafficByDate(String date);

    @Query("{ 'salesAndTrafficByAsin.parentAsin' : ?0 }")
    List<SalesAndTrafficReport> findStatisticsByAsin(String parentAsin);

    @Query("{ 'reportSpecification.reportType' : ?0, 'reportSpecification.dataStartTime' : ?1 }")
    SalesAndTrafficReport findSalesAndTrafficReportByReportTypeAndDataStart(String reportType,
                                                                            String dataStartTime);
}
