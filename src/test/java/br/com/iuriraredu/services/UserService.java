package br.com.iuriraredu.services;

import br.com.iuriraredu.config.ApiConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserService{
    public static Response getUsers() {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiConfig.getUsersEndpoint());
    }
}
