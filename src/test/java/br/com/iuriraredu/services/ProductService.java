package br.com.iuriraredu.services;

import br.com.iuriraredu.config.ApiConfig;
import br.com.iuriraredu.models.Product;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductService{
    public static Response getProducts(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiConfig.getProductsEndpoint());
    }

    public static Response getProductById(String id){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiConfig.getProductsEndpoint() + "/" + id);
    }

    public static Response delProductById(String id){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete(ApiConfig.getProductsEndpoint() + "/" + id);
    }

    public static Response getAuthProducts(String token){
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get(ApiConfig.getAuthProductsEndpoint());
    }

    public static Response addProduct(Product product){
        return given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post(ApiConfig.getProductsEndpoint() + "/add");
    }
}
