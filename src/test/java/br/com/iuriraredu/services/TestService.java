package br.com.iuriraredu.services;

import br.com.iuriraredu.config.ApiConfig;
import br.com.iuriraredu.models.TestResponse;

import static io.restassured.RestAssured.given;

public class TestService{
    public static TestResponse getTestEndpoint(){
        return given()
                .when()
                .get(ApiConfig.getTestEndpoint())
                .then()
                .statusCode(200)
                .extract()
                .as(TestResponse.class);
    }
}
