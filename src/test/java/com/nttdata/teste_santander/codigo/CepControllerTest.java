package com.nttdata.teste_santander.codigo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CepControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar os dados do CEP válido quando o serviço responder com sucesso")
    void testBuscarCepValido() throws Exception {
        // Realiza uma requisição GET ao endpoint
        mockMvc.perform(get("/cep/01001-000"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Praça da Sé")));
    }

    @Test
    @DisplayName("Deve retornar mensagem de erro para formato de CEP inválido")
    void testBuscarCepInvalido() throws Exception {
        // Realiza uma requisição GET ao endpoint com CEP inválido
        mockMvc.perform(get("/cep/123"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Formato de CEP inválido. Use o formato 12345-678."));
    }

    @Test
    @DisplayName("Deve retornar mensagem de erro quando o CEP não for encontrado")
    void testBuscarCepNaoEncontrado() throws Exception {
        mockMvc.perform(get("/cep/99999-999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value("Erro ao buscar o CEP: CEP não encontrado."));
    }
}
