package com.nttdata.teste_santander.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BuscaCepWebTest {

    //DATA para os testcases
    WebDriver driver;

    WebElement campoCep;
    WebElement botaoBuscar;
    WebElement resultado;

    By campoCepBy = By.id("cep");
    By botaoBuscarBy = By.xpath("//button[text()='Buscar']");
    By resultadoBy = By.id("resultado");

    String url = "http://localhost:8080/index.html";

    //Hooks
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        campoCep = driver.findElement(campoCepBy);
        botaoBuscar = driver.findElement(botaoBuscarBy);
        resultado = driver.findElement(resultadoBy);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //Testes
    @Test
    public void testarAcessoPagina() {
        Assertions.assertEquals(driver.getTitle(), "Busca de CEP");
    }

    @Test
    public void testarBuscaPorCep() throws InterruptedException {
        campoCep.sendKeys("01001000");
        botaoBuscar.click();
        Thread.sleep(1000);
        Assertions.assertTrue(resultado.getText().contains("Praça da Sé"));
        Assertions.assertFalse(resultado.getText().contains("Pernambuco"));
    }

    @Test
    public void testarNaoPreenchimento() throws InterruptedException {
        botaoBuscar.click();
        Thread.sleep(1000);
        Assertions.assertEquals(resultado.getText(), "");
    }

    @Test
    public void testarPreenchimentoIncorreto() throws InterruptedException {
        campoCep.sendKeys("00000000");
        botaoBuscar.click();
        Thread.sleep(1000);
        Assertions.assertTrue(resultado.getText().contains("Erro ao buscar o CEP:"));
        Assertions.assertTrue(resultado.getText().contains("CEP incorreto / não existe"));
    }

    @Test
    public void testarPreenchimentoIncompleto() throws InterruptedException {
        campoCep.sendKeys("01001");
        botaoBuscar.click();
        Thread.sleep(1000);
        Assertions.assertTrue(resultado.getText().contains("Erro ao buscar o CEP:"));
        Assertions.assertTrue(resultado.getText().contains("Formato de CEP inválido"));
    }
}
