package br.com.iuriraredu.services;

import br.com.iuriraredu.config.PropertiesConfig;
import br.com.iuriraredu.models.AuthResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthService {

    public static AuthResponse loginSuccess(String username, String password) {
        return given()
                .contentType(ContentType.JSON)
                .body(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password))
                .when()
                .post(PropertiesConfig.getAuthEndpoint())
                .then()
                .statusCode(200)
                .extract()
                .as(AuthResponse.class);
    }

    public static Response loginFailure(String username, String password) {
        return given()
                .contentType(ContentType.JSON)
                .body(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password))
                .when()
                .post(PropertiesConfig.getAuthEndpoint())
                .then()
                .statusCode(400)
                .extract()
                .response();
    }
}