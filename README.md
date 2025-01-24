# **Teste Santander - API Busca CEP**

Este projeto é uma solução para um teste técnico do Santander, que consiste em uma aplicação para consulta de endereços por meio de um CEP, utilizando a API pública [ViaCEP](https://viacep.com.br/).

## **Objetivos do Projeto**

-    Foi criada uma aplicação simples, que permita aos usuários consultar informações de endereço inserindo um CEP para ser usada nos diversos tipos de teste (unitários, integração, back e front).
-   Exemplos de validações realizadas:
    -   CEP válido (retorna endereço completo).
    -   CEP inválido (formato inválido, e indicação de uso).
    -   CEP inexistente (endereço não encontrado).
    -   CEP não preenchido (não retorna resultado).
-   Automatizar testes para garantir a confiabilidade da aplicação.
-   Criar desenho do projeto para apresentação (src/main/resources/uml_diagram.png).
-   Criar collection para validação no Postman (src/main/resources/postman_collection.json).

## **Principais Tecnologias Utilizadas**

| Tecnologia    | Descrição                                                                                                                                                                 |
| :------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Java 17**     | Linguagem de programação utilizada.                                                                                                                                     |
| **Spring Boot** | Framework para desenvolvimento rápido de aplicações Java, simplificando a configuração e o desenvolvimento de aplicações stand-alone baseadas em Spring.                        |
| **Maven**       | Gerenciador de dependências e build, automatizando o processo de compilação, teste e empacotamento do projeto.                                                                |
| **Selenium**    | Framework para testes de interface web, permitindo a automação de testes em navegadores.                                                                                      |
| **JUnit**       | Framework para testes unitários em Java, utilizado para testar unidades de código isoladas.                                                                                 |
| **Rest Assured** | Biblioteca Java para simplificar testes de APIs REST, fornecendo uma DSL (Domain Specific Language) para escrever testes de API de forma mais legível e concisa. |

---

## **Pré-requisitos**

Certifique-se de ter as seguintes ferramentas instaladas no seu ambiente:

1.  **Java 17** ou superior (verifique com `java -version`).
2.  **Maven** (verifique com `mvn -version`).
3.  **Chrome*** (não é mais necessário possuir um 'Chromedriver' separado desde o Selenium 4, pois ele é gerenciado automaticamente).
4.  Conexão com a internet para acessar a API pública do ViaCEP.
5.  Postman para importar a collection que contém os GETs para a API.

---

## **Como Executar o Projeto**

### **1. Clonar o Repositório e caminhar até o projeto**

```bash
git clone https://github.com/vivianassisouza/desafio_santander.git
```
```
cd desafio_santander
```

### **2. Instalar as Dependências**

Execute o comando abaixo para baixar as dependências do projeto:

```bash
mvn install -DskipTests
```

### **3. Iniciar o Servidor**

Para rodar a aplicação localmente:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em http://localhost:8080.

Também é possível testá-la no **Postman**, usando endpoints como:
http://localhost:8080/cep/{{cepdesejado}}.

## **Como Executar os Testes**

Para rodar todos os testes:
```bash
mvn test
```

Para rodar testes apenas do código de negócio:
```bash
mvn -Dtest=com/nttdata/teste_santander/codigo/* test
```text

Para rodar testes apenas de API:
```bash
mvn -Dtest=com/nttdata/teste_santander/api/*
```text

Para rodar testes apenas do front-end:
```bash
mvn -Dtest=com/nttdata/teste_santander/web/* test
```text
---

## **Pontos de Validação**

### **Testes de Integração/Unitários**

Utilizando **JUnit** e **Spring Test**, validar os seguintes cenários:

- **Testes unitários**: Garantir que as funções isoladas (como validação de dados e lógica de negócios) estão funcionando conforme esperado, utilizando mock para simular as demais funções da aplicação.
- **Testes de integração**: Validar se os componentes do sistema interagem corretamente, trazendo o resultado esperado para cada cenário de busca.

### **API**

Testar diferentes cenários com o uso do **Rest Assured**, como:

- **CEP válido**: retorna endereço completo.
- **CEP inválido**: retorna erro.
- **CEP inexistente**: retorna mensagem "Endereço não encontrado".
  
### **Testes Web**

Garantir que a interface responde corretamente aos diferentes cenários de entrada utilizando **Selenium**:

- **Carregamento correto da página**: Verificar se a página inicial ou qualquer outra página crítica carrega sem erros.
- **Formulários**: Validar retorno correto como esperado pela página web
- **Interatividade**: Garantir que funções interativas (como cliques) estão respondendo conforme o esperado.

---

## **Estrutura do Projeto**

- **`src/main/java`**: Código fonte da aplicação.
- **`src/test/java`**: Testes automatizados utilizando **JUnit**, **Selenium**, **Rest Assured** e **Spring Test**.
- **`pom.xml`**: Configuração do Maven e dependências do projeto.








