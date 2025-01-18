package com.nttdata.teste_santander.api;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BuscaCepAPITest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    @Test
    public void testarCepValido() {

        given()
                .pathParam("cep", "01001000")
                .when()
                .get("/cep/{cep}")
                .then()
                .statusCode(200)
                .body("logradouro", equalTo("Praça da Sé"))
                .body("localidade", equalTo("São Paulo"))
                .body("uf", equalTo("SP"));
    }

    @Test
    public void testarCepInvalido() {

        given()
                .pathParam("cep", "12345")
                .when()
                .get("/cep/{cep}")
                .then()
                .statusCode(200)
                .body(containsString("Formato de CEP inválido"));
    }

    @Test
    public void testarCepNaoEncontrado() {
        given()
                .pathParam("cep", "00000000")
                .when()
                .get("/cep/{cep}")
                .then()
                .statusCode(200)
                .body(containsString("CEP não encontrado"));
    }

    @Test
    public void validarTempoDeResposta() {
        given()
                .pathParam("cep", "01001000")
                .when()
                .get("/cep/{cep}")
                .then()
                .time(lessThan(1000L)); // 1 seg
    }
}
