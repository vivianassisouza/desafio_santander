package com.nttdata.teste_santander.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/cep/{cep}")
    public String buscarCep(@PathVariable String cep) {
        if (!cep.matches("\\d{5}-?\\d{3}")) {
            return "Formato de CEP inválido. Use o formato 12345-678.";
        }
        return cepService.buscarCep(cep);
    }
}
