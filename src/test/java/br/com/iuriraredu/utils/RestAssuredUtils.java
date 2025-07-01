package br.com.iuriraredu.utils;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

/**
 * Utilitário para facilitar validações comuns em respostas do RestAssured durante os testes.
 * Fornece métodos para validar status code e tempo de resposta das requisições.
 */
public class RestAssuredUtils {

    /**
     * Valida se o status code da resposta é igual ao esperado.
     *
     * @param response Response retornada pela requisição.
     * @param expectedStatusCode Status code esperado.
     */
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode(),
                "Status code diferente do esperado. Esperado: " + expectedStatusCode +
                        ", Obtido: " + response.getStatusCode());
    }

    /**
     * Valida se o tempo de resposta está dentro do limite máximo definido.
     *
     * @param response Response retornada pela requisição.
     * @param maxTime Tempo máximo permitido em milissegundos.
     */
    public static void validateResponseTime(Response response, long maxTime) {
        Assertions.assertTrue(response.getTime() <= maxTime,
                "Tempo de resposta excedeu o limite. Limite: " + maxTime +
                        "ms, Obtido: " + response.getTime() + "ms");
    }
}





