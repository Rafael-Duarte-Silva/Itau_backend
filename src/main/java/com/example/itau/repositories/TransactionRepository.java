package com.example.itau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.itau.domain.Transaction;
import com.example.itau.dtos.StatisticsDTO;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = """
                SELECT
                    COUNT(*) AS count,
                    COALESCE(SUM(valor), 0) AS sum,
                    COALESCE(AVG(valor), 0) AS avg,
                    COALESCE(MIN(valor), 0) AS min,
                    COALESCE(MAX(valor), 0) AS max
                FROM transaction
                WHERE data_hora >= NOW() - INTERVAL 60 SECOND
            """, nativeQuery = true)
    StatisticsDTO getStatistics();
}
