package com.kakaopay.hf_finance.controller;


import com.kakaopay.hf_finance.model.CsvFinanceInfo;
import com.kakaopay.hf_finance.service.FinanceInfoService;
import org.simpleflatmapper.csv.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

@RestController
public class FileUploadController {

    private final FinanceInfoService financeInfoService;


    public FileUploadController(FinanceInfoService financeInfoService) {
        this.financeInfoService = financeInfoService;
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public void uploadMultipart(@RequestParam("file") MultipartFile file) {

        try (Reader reader = new InputStreamReader(file.getInputStream(), "euc-kr")) {
            CsvParser
                    .skip(1)
                    .mapTo(CsvFinanceInfo.class)
                    .headers("year", "month", "bnk0001", "bnk0002", "bnk0003", "bnk0004", "bnk0005", "bnk0006", "bnk0007", "bnk0008", "bnk0009")
                    .stream(reader)
//                    .forEach(System.out::println);
                    .forEach(this::doFinanceInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doFinanceInfo(CsvFinanceInfo csvFinanceInfo){
        financeInfoService.csvToStore(csvFinanceInfo);
//        System.out.println(csvFinanceInfo.toString());
    }

}
