package com.nttdata.teste_santander.codigo;

import com.nttdata.teste_santander.core.CepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CepServiceTest {

    @Mock
    private RestTemplate restTemplateMock;

    private CepService cepService;

    @BeforeEach
    void setUp() {
        // chamar mock e classe
        restTemplateMock = Mockito.mock(RestTemplate.class);
        cepService = new CepService(restTemplateMock);
    }

    @Test
    @DisplayName("Deve retornar os dados válidos do CEP quando a API responder corretamente")
    void testBuscarCepRetornaDadosValidos() {
        // Moock
        Mockito.when(restTemplateMock.getForObject("http://viacep.com.br/ws/01001-000/json/", String.class))
                .thenReturn("{\"cep\":\"01001-000\",\"logradouro\":\"Praça da Sé\"}");

        // Ação
        String resultado = cepService.buscarCep("01001-000");

        // Validação
        assertEquals("{\"cep\":\"01001-000\",\"logradouro\":\"Praça da Sé\"}", resultado);
    }

    @Test
    @DisplayName("Deve retornar mensagem de erro quando ocorrer um erro na busca do CEP")
    void testBuscarCepComErro() {
        // Mock
        Mockito.when(restTemplateMock.getForObject("http://viacep.com.br/ws/00000-000/json/", String.class))
                .thenThrow(new RuntimeException("Erro ao acessar o serviço"));

        // Ação
        String resultado = cepService.buscarCep("00000-000");

        // Verificação
        assertEquals("Erro ao buscar o CEP: Erro ao acessar o serviço", resultado);
    }
}
