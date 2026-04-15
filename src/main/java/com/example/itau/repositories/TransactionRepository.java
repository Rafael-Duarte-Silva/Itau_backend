package com.example.itau.repositories;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.example.itau.domain.Transaction;
import com.example.itau.dtos.StatisticsDTO;

public class TransactionRepository {
    private final TreeMap<OffsetDateTime, List<Transaction>> index = new TreeMap<>();

    public Transaction save(Transaction transaction) {
        index
                .computeIfAbsent(transaction.getDataHora(), key -> new ArrayList<>())
                .add(transaction);

        return transaction;
    }

    public void deleteAll() {
        index.clear();
    }

    public StatisticsDTO getStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime start = now.minusSeconds(60);

        long count = 0;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal min = null;
        BigDecimal max = null;

        NavigableMap<OffsetDateTime, List<Transaction>> recent = index.subMap(start, true, now, true);

        for (List<Transaction> list : recent.values()) {
            for (Transaction transaction : list) {
                BigDecimal valor = transaction.getValor();

                count++;
                sum = sum.add(valor);

                if (min == null || valor.compareTo(min) < 0) {
                    min = valor;
                }

                if (max == null || valor.compareTo(max) > 0) {
                    max = valor;
                }
            }
        }

        if (count == 0) {
            return new StatisticsDTO(
                    0L,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO);
        }

        BigDecimal avg = sum.divide(
                BigDecimal.valueOf(count),
                2,
                RoundingMode.HALF_UP);

        return new StatisticsDTO(count, sum, avg, min, max);
    }
}
