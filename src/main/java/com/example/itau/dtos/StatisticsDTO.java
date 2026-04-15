package com.example.itau.dtos;

import java.math.BigDecimal;

public record StatisticsDTO(long count,
        BigDecimal sum,
        BigDecimal avg,
        BigDecimal min,
        BigDecimal max) {

}
