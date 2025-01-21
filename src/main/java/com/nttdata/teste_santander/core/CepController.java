package com.nttdata.teste_santander.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<String> buscarCep(@PathVariable String cep) {
        if (!cep.matches("\\d{5}-?\\d{3}")) {
            return new ResponseEntity<>("Formato de CEP inválido. Use o formato 12345-678.", HttpStatus.BAD_REQUEST);
        }

        String resposta = cepService.buscarCep(cep);

        if (resposta.contains("Erro ao buscar o CEP: CEP não encontrado.")) {
            return new ResponseEntity<>(resposta, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
