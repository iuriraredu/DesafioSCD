package br.com.iuriraredu.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

/**
 * Classe base para testes de API.
 * 
 * Responsável por configurar o RestAssured com a base URI, content type e filtros de log
 * para requisições e respostas. Todas as classes de teste devem herdar desta classe para
 * garantir a configuração centralizada.
 */
public abstract class BaseTest {
    /**
    * Especificação padrão de requisição utilizada nos testes.
    * Inclui Content-Type JSON e filtros de log para request e response.
    */
    protected static RequestSpecification requestSpec;

    /**
     * Método executado uma vez antes de todos os testes.
     * Configura a base URI do RestAssured e inicializa a especificação de requisição.
     */
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ApiConfig.getBaseUrl();

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}
