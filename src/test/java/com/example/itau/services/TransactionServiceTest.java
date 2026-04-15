package com.example.itau.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.itau.domain.Transaction;
import com.example.itau.repositories.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService service;

    private OffsetDateTime now;

    @BeforeEach
    void setup() {
        now = OffsetDateTime.now();
    }

    @Test
    void createTransactionValidTransactionReturnsTrue() {
        OffsetDateTime time = now.minusHours(1);
        when(repository.save(any(Transaction.class)))
                .thenReturn(new Transaction(new BigDecimal("5.5"), time));

        boolean result = service.create(new BigDecimal("5.5"), time);

        assertTrue(result);
        verify(repository).save(any(Transaction.class));
    }

    @Test
    void createTransactionTransactionInFutureReturnsFalse() {
        boolean result = service.create(new BigDecimal("5.5"), now.plusHours(1));

        assertFalse(result);
    }

    @Test
    void createTransactionNegativeValorReturnsFalse() {
        boolean result = service.create(new BigDecimal("-1"), now.minusHours(1));

        assertFalse(result);
    }
}
