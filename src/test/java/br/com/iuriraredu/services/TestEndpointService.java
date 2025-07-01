package br.com.iuriraredu.services;

import br.com.iuriraredu.config.ApiConfig;
import br.com.iuriraredu.models.TestEndpointResponse;

import static io.restassured.RestAssured.given;

/**
 * Serviço responsável por consumir o endpoint de teste da API.
 * Fornece método para realizar requisição GET e obter a resposta mapeada.
 */
public class TestEndpointService {
    /**
     * Realiza uma requisição GET ao endpoint de teste da API.
     * Espera status 200 e retorna o corpo da resposta mapeado para TestEndpointResponse.
     *
     * @return TestEndpointResponse contendo status e método retornados pela API.
     */
    public static TestEndpointResponse getTestEndpoint() {
        return given()
                .when()
                .get(ApiConfig.getTestEndpoint())
                .then()
                .statusCode(200)
                .extract()
                .as(TestEndpointResponse.class);
    }
}
