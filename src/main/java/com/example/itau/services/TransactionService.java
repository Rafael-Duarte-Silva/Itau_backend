package com.example.itau.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.example.itau.domain.Transaction;
import com.example.itau.domain.TransactionRepository;
import com.example.itau.dtos.StatisticsDTO;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public boolean create(BigDecimal valor, OffsetDateTime dataHora) {
        boolean isFuture = dataHora.isAfter(OffsetDateTime.now());
        if (isFuture || valor.signum() < 0) {
            return false;
        }

        repository.save(new Transaction(valor, dataHora));
        return true;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public StatisticsDTO getStatistics() {
        return repository.getStatistics();
    }
}
