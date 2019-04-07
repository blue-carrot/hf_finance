package com.kakaopay.hf_finance.service.impl;

import com.kakaopay.hf_finance.entity.FinanceInfo;
import com.kakaopay.hf_finance.entity.Institute;
import com.kakaopay.hf_finance.model.CsvFinanceInfo;
import com.kakaopay.hf_finance.repository.FinanceInfoRepository;
import com.kakaopay.hf_finance.repository.InstituteRepository;
import com.kakaopay.hf_finance.service.FinanceInfoService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FinanceInfoServiceImpl implements FinanceInfoService {

    private final FinanceInfoRepository financeInfoRepository;
    private final InstituteRepository instituteRepository;

    public FinanceInfoServiceImpl(FinanceInfoRepository financeInfoRepository, InstituteRepository instituteRepository) {
        this.financeInfoRepository = financeInfoRepository;
        this.instituteRepository = instituteRepository;
    }

    private Long removeComma(String value){
        return Long.parseLong(value.replaceAll(",", ""));
    }

    @Transactional(timeout = 10)
    @Override
    public void csvToStore(CsvFinanceInfo csvFinanceInfo) {

        int year = 0;
        int month = 0;
        Long amount;
        String bankcode = "";

        for (Field field : csvFinanceInfo.getClass().getDeclaredFields()) {
            field.setAccessible(true);     // you also get non-public fields
            try {
                if(field.getName().contains("year"))
                    year = Integer.parseInt(field.get(csvFinanceInfo).toString());
                else if(field.getName().contains("month"))
                    month = Integer.parseInt(field.get(csvFinanceInfo).toString());
                else {
                    bankcode = field.getName();

                    amount = removeComma(field.get(csvFinanceInfo).toString());

                    Optional<Institute> institute = instituteRepository.findByCode(bankcode);
                    FinanceInfo financeInfo = new FinanceInfo(year, month, amount, institute.get());
                    financeInfoRepository.save(financeInfo);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional(readOnly = true)
    @Override
    public String financeInfoAll() {
        List<Integer> years = financeInfoRepository.findDistinctByYear();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name","주택금융 공급현황");
            JSONArray jsonArray = new JSONArray();
            for (Integer year: years) {
                JSONObject jo = new JSONObject();
                jo.put("year",year + " 년");
                jo.put("total_amount", String.valueOf(financeInfoRepository.getTotalAmountByYear(year)));

                List<Institute> institutes = instituteRepository.findAll();
                JSONObject ins_jo = new JSONObject();
                for (Institute institute: institutes) {
                    Long institute_amount = financeInfoRepository.getTotalAmountByYearAndInstitute(year, institute);
                    ins_jo.put(institute.getName(), String.valueOf(institute_amount));
                }
                jo.put("detail_amount", ins_jo);

                jsonArray.put(jo);
            }
            jsonObject.put("list", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public String maxAmount() {
        List<FinanceInfo> financeInfos = financeInfoRepository.getMaxAmount(new PageRequest(0, 1));
        JSONObject jsonObject = new JSONObject();
        for (FinanceInfo financeInfo: financeInfos){
            try {
                jsonObject.put("year", financeInfo.getYear());
                jsonObject.put("bank", financeInfo.getInstitute().getName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonObject.toString();
    }

    @Override
    public String avgAmount() {
        Institute institute = instituteRepository.findByCode("bnk0008").get();
        List<Map<String, Object>> yearOfMaxAmounts = financeInfoRepository.getAvgMaxAmount(institute, new PageRequest(0, 1));
        List<Map<String, Object>> YearOfMinAmounts = financeInfoRepository.getAvgMinAmount(institute, new PageRequest(0, 1));

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonObject.put("bank", institute.getName());
            JSONObject jo = new JSONObject();
            jo.put("year", String.valueOf(yearOfMaxAmounts.get(0).get("year")));
            jo.put("amount", String.valueOf(Math.round((Double) yearOfMaxAmounts.get(0).get("amount"))));
            jsonArray.put(jo);


            JSONObject jo2 = new JSONObject();
            jo2.put("year", String.valueOf(YearOfMinAmounts.get(0).get("year")));
            jo2.put("amount", String.valueOf(Math.round((Double) YearOfMinAmounts.get(0).get("amount"))));
            jsonArray.put(jo2);


            jsonObject.put("support_amount", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
}
