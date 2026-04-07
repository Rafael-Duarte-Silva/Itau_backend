package com.example.itau.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.itau.domain.Transaction;
import com.example.itau.domain.TransactionRepository;
import com.example.itau.dtos.StatisticsDTO;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public boolean create(BigDecimal valor, OffsetDateTime dataHora) {
        logger.debug("Creating transaction - valor={}, dataHora={}", valor, dataHora);

        boolean isFuture = dataHora.isAfter(OffsetDateTime.now());
        if (isFuture || valor.signum() < 0) {
            logger.warn("TRANSACTION rejected - invalid data. valor={}, dataHora={}", valor, dataHora);
            return false;
        }

        Transaction transaction = repository.save(new Transaction(valor, dataHora));
        logger.info("TRANSACTION created - id={}", transaction.getId());

        return true;
    }

    public void deleteAll() {
        logger.warn("TRANSACTION delete all requested");
        repository.deleteAll();
        logger.info("TRANSACTION all deleted");
    }

    public StatisticsDTO getStatistics() {
        logger.debug("Fetching statistics");
        StatisticsDTO stats = repository.getStatistics();
        logger.info("STATISTICS generated");
        return stats;
    }
}
