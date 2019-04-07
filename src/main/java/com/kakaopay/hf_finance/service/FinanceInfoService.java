package com.kakaopay.hf_finance.service;

import com.kakaopay.hf_finance.entity.FinanceInfo;
import com.kakaopay.hf_finance.model.CsvFinanceInfo;

import java.util.List;

public interface FinanceInfoService {

    void csvToStore(CsvFinanceInfo csvFinanceInfo);

    String financeInfoAll();

    String maxAmount();

    String avgAmount();
}
