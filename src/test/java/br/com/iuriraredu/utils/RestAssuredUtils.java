package br.com.iuriraredu.utils;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class RestAssuredUtils {
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode(),
                "Status code diferente do esperado. Esperado: " + expectedStatusCode +
                        ", Obtido: " + response.getStatusCode());
    }

    public static void validateResponseTime(Response response, long maxTime) {
        Assertions.assertTrue(response.getTime() <= maxTime,
                "Tempo de resposta excedeu o limite. Limite: " + maxTime +
                        "ms, Obtido: " + response.getTime() + "ms");
    }
}





