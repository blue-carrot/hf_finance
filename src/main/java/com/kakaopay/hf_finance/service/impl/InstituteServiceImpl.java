package com.kakaopay.hf_finance.service.impl;

import com.kakaopay.hf_finance.entity.Institute;
import com.kakaopay.hf_finance.repository.InstituteRepository;
import com.kakaopay.hf_finance.service.InstituteService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InstituteServiceImpl implements InstituteService {

    private final InstituteRepository instituteRepository;

    public InstituteServiceImpl(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Institute> findByCode(String code) {
        return instituteRepository.findByCode(code);
    }

    @Transactional(readOnly = true)
    @Override
    public String findAll(){
        List<Institute> institutes = instituteRepository.findAll();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Institute institute: institutes) {
            JSONObject jb = new JSONObject();
            try {
                jb.put("bankname",institute.getName());
                jb.put("bankcode",institute.getCode());
                jsonArray.put(jb);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObject.put("list",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        try {
            jsonObject.put("name", "금융기관(은행) 목록");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
}
