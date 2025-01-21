package com.nttdata.teste_santander.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<Object> buscarCep(@PathVariable String cep) {
        if (!cep.matches("\\d{5}-?\\d{3}")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", "Formato de CEP inválido. Use o formato 12345-678."));
        }

        String response = cepService.buscarCep(cep);
        if (response.startsWith("Erro ao buscar o CEP")) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", response));
        }

        return ResponseEntity.ok(response);
    }
}
