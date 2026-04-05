package com.example.itau.services;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.example.itau.domain.Transaction;
import com.example.itau.domain.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public boolean create(float valor, OffsetDateTime dataHora) {
        boolean isFuture = dataHora.isAfter(OffsetDateTime.now());
        if (isFuture || valor < 0) {
            return false;
        }

        repository.save(new Transaction(valor, dataHora));
        return true;
    }
}
