package com.example.itau.dtos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TransactionCreateDTO(@NotNull @Min(0) BigDecimal valor, @NotNull OffsetDateTime dataHora) {

}
