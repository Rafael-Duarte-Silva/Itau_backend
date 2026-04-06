package com.example.itau.dtos;

import java.math.BigDecimal;

public interface StatisticsDTO {
    Long getCount();

    BigDecimal getSum();

    BigDecimal getAvg();

    BigDecimal getMin();

    BigDecimal getMax();
}
