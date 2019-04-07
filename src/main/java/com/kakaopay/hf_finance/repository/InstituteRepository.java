package com.kakaopay.hf_finance.repository;

import com.kakaopay.hf_finance.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstituteRepository extends JpaRepository<Institute, String> {
    Optional<Institute> findByCode(String code);
}
