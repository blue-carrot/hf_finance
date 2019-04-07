package com.kakaopay.hf_finance.service;

import com.kakaopay.hf_finance.entity.Institute;

import java.util.List;
import java.util.Optional;

public interface InstituteService {
    Optional<Institute> findByCode(String code);

    String findAll();
}
