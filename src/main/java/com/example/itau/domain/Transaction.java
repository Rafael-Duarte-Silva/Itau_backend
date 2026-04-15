package com.example.itau.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transaction {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
