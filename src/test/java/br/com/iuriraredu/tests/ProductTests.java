package br.com.iuriraredu.tests;

import br.com.iuriraredu.config.PropertiesConfig;
import br.com.iuriraredu.config.BaseTest;
import br.com.iuriraredu.models.Product;
import br.com.iuriraredu.services.ProductService;
import br.com.iuriraredu.utils.JsonDataReader;
import br.com.iuriraredu.utils.ReportUtils;
import br.com.iuriraredu.utils.RestAssuredUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ReportUtils.class)
public class ProductTests extends BaseTest {

    @Test
    @DisplayName("Listar todos os produtos")
    public void testGetAllProducts() {
        var response = ProductService.getProducts();

        RestAssuredUtils.validateStatusCode(response, 200);
        assertFalse(response.jsonPath().getList("products").isEmpty(),
                "Lista de produtos não deve estar vazia");

        ReportUtils.logInfo("Lista de produtos obtida com sucesso - Status code: 200");
    }

    @Test
    @DisplayName("Buscar produto por ID existente")
    public void testGetProductById() {
        var response = ProductService.getProductById(PropertiesConfig.getExistingProductId());

        RestAssuredUtils.validateStatusCode(response, 200);
        assertNotNull(response.jsonPath().get("id"), "Produto deve ter um ID");
        assertNotNull(response.jsonPath().get("title"), "Produto deve ter um título");

        ReportUtils.logInfo("Produto obtido por ID com sucesso - Status code: 200");
    }

    @Test
    @DisplayName("Deletar produto por ID existente")
    public void testDelProductById() {
        var response = ProductService.delProductById(PropertiesConfig.getExistingProductId());

        RestAssuredUtils.validateStatusCode(response, 200);
        assertNotNull(response.jsonPath().get("id"), "Produto deve ter um ID");
        assertNotNull(response.jsonPath().get("title"), "Produto deve ter um título");
        assertEquals(true, response.jsonPath().get("isDeleted"));

        ReportUtils.logInfo("Produto deletado por ID com sucesso - Status code: 200");
    }

    @Test
    @DisplayName("Buscar produto por ID inexistente")
    public void testGetNonExistingProduct() {
        var response = ProductService.getProductById(PropertiesConfig.getNonExistingProductId());

        RestAssuredUtils.validateStatusCode(response, 404);
        ReportUtils.logInfo("Produto inexistente - Status code: 404");
    }

    @Test
    @DisplayName("Adicionar novo produto")
    public void testAddProduct() {
        Product newProduct = new Product();
        newProduct.setTitle("Novo Produto");
        newProduct.setDescription("Descrição do novo produto");
        newProduct.setPrice(99.99);
        newProduct.setStock(10);
        newProduct.setCategory("eletrônicos");

        var response = ProductService.addProduct(newProduct);

        RestAssuredUtils.validateStatusCode(response, 201);
        assertNotNull(response.jsonPath().get("id"), "Produto adicionado deve ter um ID");
        assertEquals("Novo Produto", response.jsonPath().get("title"),
                "Título do produto deve corresponder");

        ReportUtils.logInfo("Produto adicionado com sucesso - Status code: 201");
    }

    @Test
    @DisplayName("Adicionar produto com dados do JSON")
    public void testAddProductWithJsonData() throws IOException {
        Product newProduct = JsonDataReader.readTestData(
                "test-data/products.json",
                "newProduct",
                Product.class
        );

        var response = ProductService.addProduct(newProduct);

        RestAssuredUtils.validateStatusCode(response, 201);
        assertNotNull(response.jsonPath().get("id"), "Produto adicionado deve ter um ID");
        assertEquals(newProduct.getTitle(), response.jsonPath().get("title"),
                "Título do produto deve corresponder");

        ReportUtils.logInfo("Produto adicionado com dados do JSON com sucesso - Status code: 201");
    }
}