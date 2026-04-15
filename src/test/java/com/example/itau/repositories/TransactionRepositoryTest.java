package com.example.itau.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.itau.domain.Transaction;
import com.example.itau.dtos.StatisticsDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {

    private TransactionRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TransactionRepository();
    }

    @Test
    void saveValidTransactionUpdatesStatistics() {
        Transaction t = new Transaction(
                BigDecimal.valueOf(100),
                OffsetDateTime.now());

        repository.save(t);

        StatisticsDTO stats = repository.getStatistics();

        assertEquals(1L, stats.count());
        assertEquals(BigDecimal.valueOf(100), stats.sum());
    }

    @Test
    void deleteAllExistingTransactionsClearsRepository() {
        repository.save(new Transaction(
                BigDecimal.valueOf(100),
                OffsetDateTime.now()));

        repository.deleteAll();

        StatisticsDTO stats = repository.getStatistics();

        assertEquals(0L, stats.count());
    }

    @Test
    void getStatisticsNoTransactionsReturnsEmptyStatistics() {
        StatisticsDTO stats = repository.getStatistics();

        assertEquals(0L, stats.count());
        assertEquals(BigDecimal.ZERO, stats.sum());
        assertEquals(BigDecimal.ZERO, stats.avg());
        assertEquals(BigDecimal.ZERO, stats.min());
        assertEquals(BigDecimal.ZERO, stats.max());
    }

    @Test
    void getStatisticsMultipleTransactionsCalculatesCorrectly() {
        OffsetDateTime now = OffsetDateTime.now();

        repository.save(new Transaction(BigDecimal.valueOf(10), now));
        repository.save(new Transaction(BigDecimal.valueOf(20), now));
        repository.save(new Transaction(BigDecimal.valueOf(30), now));

        StatisticsDTO stats = repository.getStatistics();

        assertEquals(3L, stats.count());
        assertEquals(BigDecimal.valueOf(60), stats.sum());
        assertEquals(BigDecimal.valueOf(20.00).setScale(2), stats.avg());
        assertEquals(BigDecimal.valueOf(10), stats.min());
        assertEquals(BigDecimal.valueOf(30), stats.max());
    }

    @Test
    void getStatisticsOldTransactionsIgnoredReturnsOnlyRecent() {
        OffsetDateTime now = OffsetDateTime.now();

        repository.save(new Transaction(
                BigDecimal.valueOf(100),
                now.minusSeconds(120)));

        repository.save(new Transaction(
                BigDecimal.valueOf(50),
                now));

        StatisticsDTO stats = repository.getStatistics();

        assertEquals(1L, stats.count());
        assertEquals(BigDecimal.valueOf(50), stats.sum());
    }

    @Test
    void getStatisticsAverageWithRoundingReturnsScaledValue() {
        OffsetDateTime now = OffsetDateTime.now();

        repository.save(new Transaction(BigDecimal.valueOf(10), now));
        repository.save(new Transaction(BigDecimal.valueOf(11), now));

        StatisticsDTO stats = repository.getStatistics();

        assertEquals(BigDecimal.valueOf(10.50).setScale(2), stats.avg());
    }
}