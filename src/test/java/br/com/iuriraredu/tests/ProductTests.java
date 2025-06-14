package br.com.iuriraredu.tests;

import br.com.iuriraredu.config.TestConfig;
import br.com.iuriraredu.models.Product;
import br.com.iuriraredu.services.ProductService;
import br.com.iuriraredu.utils.JsonDataReader;
import br.com.iuriraredu.utils.ReportUtils;
import br.com.iuriraredu.utils.RestAssuredUtils;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ReportUtils.class)
@Epic("Gerenciamento de Produtos")
@Feature("Operações CRUD de Produtos")
public class ProductTests extends TestConfig {
    private static final int EXISTING_PRODUCT_ID = 20;
    private static final int NON_EXISTING_PRODUCT_ID = 9999;

    @Test
    @DisplayName("Listar todos os produtos")
    @Story("Usuário pode listar todos os produtos")
    @Severity(SeverityLevel.BLOCKER)
    public void testGetAllProducts() {
        var response = ProductService.getProducts();

        RestAssuredUtils.validateStatusCode(response, 200);
        assertFalse(response.jsonPath().getList("products").isEmpty(),
                "Lista de produtos não deve estar vazia");

        ReportUtils.logInfo("Lista de produtos obtida com sucesso");
    }

    @Test
    @DisplayName("Buscar produto por ID existente")
    @Story("Usuário pode buscar produto por ID")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetProductById() {
        var response = ProductService.getProductById(EXISTING_PRODUCT_ID);

        RestAssuredUtils.validateStatusCode(response, 200);
        assertNotNull(response.jsonPath().get("id"), "Produto deve ter um ID");
        assertNotNull(response.jsonPath().get("title"), "Produto deve ter um título");

        ReportUtils.logInfo("Produto obtido por ID com sucesso");
    }

    @Test
    @DisplayName("Buscar produto por ID inexistente")
    @Story("Sistema deve tratar produto não encontrado")
    @Severity(SeverityLevel.NORMAL)
    public void testGetNonExistingProduct() {
        var response = ProductService.getProductById(NON_EXISTING_PRODUCT_ID);

        RestAssuredUtils.validateStatusCode(response, 404);
        ReportUtils.logInfo("Produto inexistente retornou status 404 como esperado");
    }

    @Test
    @DisplayName("Adicionar novo produto")
    @Story("Usuário pode adicionar produtos")
    @Severity(SeverityLevel.CRITICAL)
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

        ReportUtils.logInfo("Produto adicionado com sucesso");
    }

    @Test
    @DisplayName("Adicionar produto com dados do JSON")
    @Story("Usuário pode adicionar produto com dados externos")
    @Severity(SeverityLevel.NORMAL)
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

        ReportUtils.logInfo("Produto adicionado com dados do JSON com sucesso");
    }
}