package br.com.iuriraredu.tests;

import br.com.iuriraredu.config.ApiConfig;
import br.com.iuriraredu.config.BaseTest;
import br.com.iuriraredu.models.AuthResponse;
import br.com.iuriraredu.services.AuthService;
import br.com.iuriraredu.services.ProductService;
import br.com.iuriraredu.utils.ReportUtils;
import br.com.iuriraredu.utils.RestAssuredUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Classe de testes para autenticação e acesso a endpoints protegidos da API.
 * Contém cenários de login com credenciais válidas e inválidas, além de testes de acesso a endpoints protegidos com diferentes tokens.
 */
@ExtendWith(ReportUtils.class)
public class AuthTests extends BaseTest {

    /**
     * Testa o login com credenciais válidas.
     * Verifica se todos os campos essenciais da resposta estão presentes e corretos.
     */
    @Test
    @DisplayName("Login com credenciais válidas")
    public void testSuccessfulLogin() {
        AuthResponse response = AuthService.loginSuccess(
                ApiConfig.getValidUsername(),
                ApiConfig.getValidPassword()
        );

        assertAll("Verifica todos os campos da resposta de sucesso",
                () -> assertNotNull(response.getAccessToken(), "Access token não deve ser nulo"),
                () -> assertNotNull(response.getRefreshToken(), "Refresh token não deve ser nulo"),
                () -> assertEquals(ApiConfig.getValidUsername(), response.getUsername(), "Username deve corresponder"),
                () -> assertNotNull(response.getId(), "ID não deve ser nulo"),
                () -> assertNotNull(response.getFirstName(), "First Name não deve ser nulo"),
                () -> assertNotNull(response.getLastName(), "Last não deve ser nulo"),
                () -> assertNotNull(response.getEmail(), "Email não deve ser nulo"),
                () -> assertNotNull(response.getImage(), "Image URL não deve ser nula")
        );

        ReportUtils.logInfo("Login realizado com sucesso com usuário: " + ApiConfig.getValidUsername() + " - Status Code: 200");
    }

    /**
     * Testa o login com credenciais inválidas.
     * Verifica se a API retorna status 400 e mensagem de erro.
     */
    @Test
    @DisplayName("Login com credenciais inválidas")
    public void testLoginWithEmptyCredentials() {
        var response = AuthService.loginFailure(
                ApiConfig.getInvalidUsername(),
                ApiConfig.getInvalidPassword()
        );

        assertEquals(400, response.getStatusCode(), "Status code deve ser 400");
        assertNotNull(response.jsonPath().getString("message"),
                "Mensagem de erro não deve ser nula");

        ReportUtils.logInfo("Tentativa de login com credenciais vazias falhou - Status Code: 400");
    }

    /**
     * Testa o acesso ao endpoint protegido de produtos com token de acesso válido.
     * Espera status 200.
     */
    @Test
    @DisplayName("Listar Produtos após login com credenciais válidas")
    public void testAccessProtectedEndpointWithValidToken() {
        AuthResponse authResponse = AuthService.loginSuccess(
                ApiConfig.getValidUsername(),
                ApiConfig.getValidPassword()
        );
        String token = "Bearer " + authResponse.getAccessToken();

        var response = ProductService.getAuthProducts(token);
        RestAssuredUtils.validateStatusCode(response, 200);

        ReportUtils.logInfo("Endpoint protegido acessado com token válido - Status Code: 200");
    }

    /**
     * Testa o acesso ao endpoint protegido de produtos com refresh token válido.
     * Espera status 200.
     */
    @Test
    @DisplayName("Listar Produtos após login com credenciais válidas")
    public void testAccessProtectedEndpointWithValidRefreshToken() {
        AuthResponse authResponse = AuthService.loginSuccess(
                ApiConfig.getValidUsername(),
                ApiConfig.getValidPassword()
        );
        String token = "Bearer " + authResponse.getRefreshToken();

        var response = ProductService.getAuthProducts(token);
        RestAssuredUtils.validateStatusCode(response, 200);

        ReportUtils.logInfo("Endpoint protegido acessado com refresh token válido - Status Code: 200");
    }

    /**
     * Testa o acesso ao endpoint protegido de produtos com token inválido.
     * Espera status 401.
     */
    @Test
    @DisplayName("Login com com token inválido")
    public void testAccessProtectedEndpointWithInvalidToken() {
        String invalidToken = "Bearer invalidtoken";

        var response = ProductService.getAuthProducts(invalidToken);
        RestAssuredUtils.validateStatusCode(response, 401);

        ReportUtils.logInfo("Endpoint protegido não acessado com token inválido - Status Code: 401");
    }
}