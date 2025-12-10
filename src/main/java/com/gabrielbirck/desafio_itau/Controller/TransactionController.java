package com.gabrielbirck.desafio_itau.Controller;

import com.gabrielbirck.desafio_itau.Class.Transaction;
import com.gabrielbirck.desafio_itau.DTO.TransactionRequest;
import com.gabrielbirck.desafio_itau.Service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequest transaction) {
        if (transaction.getDataHora().isAfter(OffsetDateTime.now()) || transaction.getValor() <= 0) {
            return ResponseEntity.unprocessableEntity().build();
        }

        transactionService.addTransaction(new Transaction(transaction.getValor(), transaction.getDataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction() {
        transactionService.cleanTransactions();
        return ResponseEntity.ok().build();
    }

}
