package br.com.iuriraredu.services;

import br.com.iuriraredu.config.ApiConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Serviço responsável pelas operações relacionadas a usuários na API.
 * Fornece métodos para buscar todos os usuários, buscar usuário por ID e remover usuário por ID.
 */
public class UserService {
    /**
     * Busca todos os usuários disponíveis na API.
     *
     * @return Response contendo a lista de usuários.
     */
    public static Response getUsers() {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiConfig.getUsersEndpoint());
    }

    /**
     * Busca um usuário específico pelo seu ID.
     *
     * @param id Identificador do usuário.
     * @return Response contendo os dados do usuário.
     */
    public static Response getUsersById(String id) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiConfig.getUsersEndpoint() + "/" + id);
    }

    /**
     * Remove um usuário específico pelo seu ID.
     *
     * @param id Identificador do usuário.
     * @return Response contendo o resultado da operação de remoção.
     */
    public static Response delUsersById(String id) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete(ApiConfig.getUsersEndpoint() + "/" + id);
    }
}
