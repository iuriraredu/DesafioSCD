package br.com.iuriraredu.services;

import br.com.iuriraredu.config.PropertiesConfig;
import br.com.iuriraredu.models.Product;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductService{
    public static Response getProducts(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(PropertiesConfig.getProductsEndpoint());
    }

    public static Response getProductById(String id){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(PropertiesConfig.getProductsEndpoint() + "/" + id);
    }

    public static Response delProductById(String id){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete(PropertiesConfig.getProductsEndpoint() + "/" + id);
    }

    public static Response getAuthProducts(String token){
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get(PropertiesConfig.getAuthProductsEndpoint());
    }

    public static Response addProduct(Product product){
        return given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post(PropertiesConfig.getAddProductsEndpoint());
    }
}
