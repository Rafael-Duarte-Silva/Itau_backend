package com.example.itau.controllers;

import com.example.itau.services.TransactionService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService service;

    @Test
    void createTransactionValidTransactionReturns201() throws Exception {
        when(service.create(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any()))
                .thenReturn(true);

        String json = """
                {
                    "valor": 100.0,
                    "dataHora": "2024-01-01T10:00:00Z"
                }
                """;

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void createTransactionServiceRejectsTransactionReturns422() throws Exception {
        when(service.create(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any()))
                .thenReturn(false);

        String json = """
                {
                    "valor": -10.0,
                    "dataHora": "2024-01-01T10:00:00Z"
                }
                """;

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnprocessableContent());
    }

    @Test
    void createTransactionInvalidJsonReturns400() throws Exception {
        String json = """
                {
                    "valor": "abc",
                    "dataHora": "invalid-date"
                }
                """;

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteAllTransactionsNoContentReturns200() throws Exception {
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }

    @Test
    void getStatisticsNoContentReturns200() throws Exception {
        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk());
    }
}