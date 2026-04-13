package com.example.itau.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    private TransactionService service;

    private OffsetDateTime now;

    @BeforeEach
    void setup() {
        service = new TransactionService();
        now = OffsetDateTime.now();
    }

    @Test
    void createTransactionValidTransactionReturnsTrue() {
        OffsetDateTime time = now.minusHours(1);

        boolean result = service.create(new BigDecimal("5.5"), time);

        assertTrue(result);
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
