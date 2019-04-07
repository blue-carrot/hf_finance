package com.kakaopay.hf_finance.controller;

import com.kakaopay.hf_finance.service.FinanceInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinancelInfoControllter {

    private final FinanceInfoService financeInfoService;

    public FinancelInfoControllter(FinanceInfoService financeInfoService) {
        this.financeInfoService = financeInfoService;
    }

    @GetMapping(value = "/financeinfo")
    public ResponseEntity<String> financeinfo() {
        return ResponseEntity.ok(financeInfoService.financeInfoAll());
    }


    @GetMapping(value = "/maxamount")
    public ResponseEntity<String> maxamount() {
        return ResponseEntity.ok(financeInfoService.maxAmount());
    }

    @GetMapping(value = "/avgamount")
    public ResponseEntity<String> avgamount() {
        return ResponseEntity.ok(financeInfoService.avgAmount());
    }

}