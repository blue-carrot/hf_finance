package com.kakaopay.hf_finance.controller;

import com.kakaopay.hf_finance.service.InstituteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InstituteController {

    private final InstituteService instituteService;

    public InstituteController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @GetMapping(value = "/inslist")
    public ResponseEntity<String> inslist() {

        return ResponseEntity.ok(instituteService.findAll());

    }
}
