package com.example.itau.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.example.itau.domain.Transaction;
import com.example.itau.dtos.StatisticsDTO;
import com.example.itau.repositories.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {
    private TransactionRepository repository = new TransactionRepository();

    public boolean create(BigDecimal valor, OffsetDateTime dataHora) {
        log.debug("Creating transaction - valor={}, dataHora={}", valor, dataHora);

        boolean isFuture = dataHora.isAfter(OffsetDateTime.now());
        if (isFuture || valor.signum() < 0) {
            log.warn("TRANSACTION rejected - invalid data. valor={}, dataHora={}", valor, dataHora);
            return false;
        }

        Transaction transaction = repository.save(new Transaction(valor, dataHora));
        log.info("TRANSACTION created - dataHora={}", transaction.getDataHora());

        return true;
    }

    public void deleteAll() {
        log.warn("TRANSACTION delete all requested");
        repository.deleteAll();
        log.info("TRANSACTION all deleted");
    }

    public StatisticsDTO getStatistics() {
        log.debug("Fetching statistics");
        StatisticsDTO stats = repository.getStatistics();
        log.info("STATISTICS generated");
        return stats;
    }
}
