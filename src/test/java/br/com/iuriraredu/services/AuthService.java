package br.com.iuriraredu.services;

import br.com.iuriraredu.config.ApiConfig;
import br.com.iuriraredu.models.AuthResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Serviço responsável pelas operações de autenticação na API.
 * Fornece métodos para realizar login com sucesso e login com falha, retornando os objetos de resposta apropriados.
 */
public class AuthService {

    /**
     * Realiza o login com credenciais válidas.
     * Envia uma requisição POST para o endpoint de autenticação e espera status 200.
     *
     * @param username Nome de usuário válido.
     * @param password Senha válida.
     * @return AuthResponse contendo os dados retornados pela API após login bem-sucedido.
     */
    public static AuthResponse loginSuccess(String username, String password) {
        return given()
                .contentType(ContentType.JSON)
                .body(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password))
                .when()
                .post(ApiConfig.getAuthEndpoint() + "/login")
                .then()
                .statusCode(200)
                .extract()
                .as(AuthResponse.class);
    }

    /**
     * Realiza o login com credenciais inválidas.
     * Envia uma requisição POST para o endpoint de autenticação e espera status 400.
     *
     * @param username Nome de usuário inválido.
     * @param password Senha inválida.
     * @return Response contendo a resposta da API após tentativa de login mal-sucedida.
     */
    public static Response loginFailure(String username, String password) {
        return given()
                .contentType(ContentType.JSON)
                .body(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password))
                .when()
                .post(ApiConfig.getAuthEndpoint() + "/login")
                .then()
                .statusCode(400)
                .extract()
                .response();
    }
}