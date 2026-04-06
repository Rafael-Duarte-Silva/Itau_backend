package com.example.itau.dtos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionCreateDTO(BigDecimal valor, OffsetDateTime dataHora) {

}
