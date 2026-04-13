package com.example.itau.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Transaction {
    private long id;
    private BigDecimal valor;
    private OffsetDateTime dataHora;

    public Transaction(BigDecimal valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }
}
