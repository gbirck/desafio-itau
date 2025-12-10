package com.gabrielbirck.desafio_itau.Controller;

import com.gabrielbirck.desafio_itau.DTO.StatisticsResponse;
import com.gabrielbirck.desafio_itau.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/estatistica")
public class StatisticController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity<StatisticsResponse>  getStatistics() {
        DoubleSummaryStatistics stats = transactionService.getStatistics();
        return ResponseEntity.ok(new StatisticsResponse(stats));
    }
}
