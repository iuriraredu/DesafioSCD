package br.com.iuriraredu.services;

import br.com.iuriraredu.config.ApiConfig;
import br.com.iuriraredu.models.Product;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Serviço responsável pelas operações relacionadas a produtos na API.
 * Fornece métodos para buscar, adicionar e remover produtos, além de buscar produtos autenticados.
 */
public class ProductService {

    /**
     * Busca todos os produtos disponíveis na API.
     *
     * @return Response contendo a lista de produtos.
     */
    public static Response getProducts() {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiConfig.getProductsEndpoint());
    }

    /**
     * Busca um produto específico pelo seu ID.
     *
     * @param id Identificador do produto.
     * @return Response contendo os dados do produto.
     */
    public static Response getProductById(String id) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiConfig.getProductsEndpoint() + "/" + id);
    }

    /**
     * Remove um produto específico pelo seu ID.
     *
     * @param id Identificador do produto.
     * @return Response contendo o resultado da operação de remoção.
     */
    public static Response delProductById(String id) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete(ApiConfig.getProductsEndpoint() + "/" + id);
    }

    /**
     * Busca produtos autenticados utilizando um token JWT.
     *
     * @param token Token JWT de autenticação.
     * @return Response contendo a lista de produtos autenticados.
     */
    public static Response getAuthProducts(String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get(ApiConfig.getAuthEndpoint() + "/products");
    }

    /**
     * Adiciona um novo produto à base de dados da API.
     *
     * @param product Objeto Product contendo os dados do novo produto.
     * @return Response contendo o resultado da operação de adição.
     */
    public static Response addProduct(Product product) {
        return given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post(ApiConfig.getProductsEndpoint() + "/add");
    }
}
