package com.gabriel.front_gestao_vagas.modules.candidate.service;

import com.gabriel.front_gestao_vagas.modules.candidate.dto.ProfileUseDTO;
import com.gabriel.front_gestao_vagas.modules.candidate.dto.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ProfileCandidateService {

    @Value("${host.api.gestao.vagas}")
    private String hostAPIGestaoVagas;

    public ProfileUseDTO execute(String token){
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

        String url = hostAPIGestaoVagas.concat("/candidate/");

        try {
            var result = rt.exchange(url, HttpMethod.GET, request, ProfileUseDTO.class);
            return result.getBody();
        }catch (Unauthorized ex){
            throw  new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}
