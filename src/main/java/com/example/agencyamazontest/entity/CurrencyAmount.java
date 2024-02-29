package com.example.agencyamazontest.entity;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data
public abstract class CurrencyAmount {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal amount;
    private String currencyCode;
}
