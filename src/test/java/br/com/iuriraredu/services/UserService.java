package br.com.iuriraredu.services;

import br.com.iuriraredu.config.PropertiesConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserService{
    public static Response getUsers() {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(PropertiesConfig.getUsersEndpoint());
    }

    public static Response getUsersById(String id) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(PropertiesConfig.getUsersEndpoint() + "/" + id);
    }

    public static Response delUsersById(String id) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete(PropertiesConfig.getUsersEndpoint() + "/" + id);
    }
}
