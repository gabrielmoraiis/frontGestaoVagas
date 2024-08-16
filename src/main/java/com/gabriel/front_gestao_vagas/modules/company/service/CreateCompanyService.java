package com.gabriel.front_gestao_vagas.modules.company.service;

import com.gabriel.front_gestao_vagas.modules.candidate.dto.CreateCandidateDTO;
import com.gabriel.front_gestao_vagas.modules.company.dto.CreateCompanyDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateCompanyService {

    @Value("${host.api.gestao.vagas}")
    private String hostAPIGestaoVagas;

    public String execute(CreateCompanyDTO dto) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateCompanyDTO> request = new HttpEntity<>(dto, headers);

        String url = hostAPIGestaoVagas.concat("/company/");

        return rt.postForObject(url, request, String.class);
    }


}
