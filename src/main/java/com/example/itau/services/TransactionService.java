package com.example.itau.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.example.itau.dtos.StatisticsDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {

    public boolean create(BigDecimal valor, OffsetDateTime dataHora) {
        log.debug("Creating transaction - valor={}, dataHora={}", valor, dataHora);

        boolean isFuture = dataHora.isAfter(OffsetDateTime.now());
        if (isFuture || valor.signum() < 0) {
            log.warn("TRANSACTION rejected - invalid data. valor={}, dataHora={}", valor, dataHora);
            return false;
        }

        // code

        return true;
    }

    public void deleteAll() {
        log.warn("TRANSACTION delete all requested");
        // code
        log.info("TRANSACTION all deleted");
    }

    public StatisticsDTO getStatistics() {
        log.debug("Fetching statistics");
        // code
        log.info("STATISTICS generated");
        return new StatisticsDTO(0L, null, null, null, null);
    }
}
