package com.example.itau.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.itau.dtos.TransactionCreateDTO;
import com.example.itau.services.TransactionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/transacao")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(
            @RequestBody @Validated TransactionCreateDTO data) {

        boolean created = service.create(data.valor(), data.dataHora());

        if (!created) {
            return ResponseEntity.unprocessableContent().build();
        }

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTransaction() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
