package com.equipe05.plataformaconstrucao.services.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TrnTrackerService extends TrackerService {
    private @Value("${plataforma_construcao.app.traker.trnApiKey:}") String API_KEY;

    public TrnTrackerService() {
        this.apiHost = "https://public-api.tracker.gg/v2/";
    }

    public String sendGetRequest(String endpoint, String pathParam) throws Exception {
        RestTemplate restTemplate = restTemplate();
        String url = endpoint + "/" + pathParam;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.set("TRN-Api-Key", API_KEY);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, String.class);
        return response.getBody();
    }
}
