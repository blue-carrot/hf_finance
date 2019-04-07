package com.kakaopay.hf_finance.repository;

import com.kakaopay.hf_finance.entity.FinanceInfo;
import com.kakaopay.hf_finance.entity.Institute;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface FinanceInfoRepository extends JpaRepository<FinanceInfo, Long> {

    @Query("SELECT DISTINCT I.year FROM FinanceInfo I ORDER BY I.year")
    List<Integer> findDistinctByYear();

    @Query("SELECT SUM(I.amount) FROM FinanceInfo I WHERE I.year = ?1")
    Long getTotalAmountByYear(Integer year);

    @Query("SELECT SUM(I.amount) FROM FinanceInfo I WHERE I.year = ?1 AND I.institute = ?2")
    Long getTotalAmountByYearAndInstitute(Integer year, Institute institute);

    @Query("SELECT I FROM FinanceInfo I ORDER BY I.amount DESC")
    List<FinanceInfo> getMaxAmount(Pageable pageable);

    @Query("SELECT I.year AS year, AVG(I.amount) AS amount FROM FinanceInfo I GROUP BY I.year ORDER BY amount")
    List<Map<String, Object>> getAvgMinAmount(Institute institute, Pageable pageable);

    @Query("SELECT I.year AS year, AVG(I.amount) AS amount FROM FinanceInfo I GROUP BY I.year ORDER BY amount DESC")
    List<Map<String, Object>> getAvgMaxAmount(Institute institute, Pageable pageable);

}