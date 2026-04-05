package com.example.itau.dtos;

import java.time.OffsetDateTime;

public record TransactionCreateDTO(float valor, OffsetDateTime dataHora) {

}
