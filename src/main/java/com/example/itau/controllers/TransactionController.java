package com.example.itau.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.itau.dtos.StatisticsDTO;
import com.example.itau.dtos.TransactionCreateDTO;
import com.example.itau.services.TransactionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> createTransaction(
            @RequestBody @Valid TransactionCreateDTO data) {

        boolean created = service.create(data.valor(), data.dataHora());

        if (!created) {
            return ResponseEntity.unprocessableContent().build();
        }

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deleteAllTransaction() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<StatisticsDTO> getStatistics() {
        return ResponseEntity.ok(service.getStatistics());
    }

}
