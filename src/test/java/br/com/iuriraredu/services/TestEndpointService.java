package br.com.iuriraredu.services;

import br.com.iuriraredu.config.PropertiesConfig;
import br.com.iuriraredu.models.TestEndpointResponse;

import static io.restassured.RestAssured.given;

public class TestEndpointService{
    public static TestEndpointResponse getTestEndpoint(){
        return given()
                .when()
                .get(PropertiesConfig.getTestEndpoint())
                .then()
                .statusCode(200)
                .extract()
                .as(TestEndpointResponse.class);
    }
}
