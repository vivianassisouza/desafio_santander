package com.nttdata.teste_santander.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public String buscarCep(String cep) {
        try {
            String url = "http://viacep.com.br/ws/" + cep + "/json/";
            String response = restTemplate.getForObject(url, String.class);

            // tratar 'erro = true'
            JsonNode jsonNode = objectMapper.readTree(response);
            if (jsonNode.has("erro") && jsonNode.get("erro").asBoolean()) {
                return "Erro ao buscar o CEP: CEP não encontrado.";
            }

            return response;
        } catch (Exception e) {
            return "Erro ao buscar o CEP: " + e.getMessage();
        }
    }
}
