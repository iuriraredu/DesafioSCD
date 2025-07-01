package br.com.iuriraredu.tests;

import br.com.iuriraredu.config.ApiConfig;
import br.com.iuriraredu.config.BaseTest;
import br.com.iuriraredu.services.UserService;
import br.com.iuriraredu.utils.ReportUtils;
import br.com.iuriraredu.utils.RestAssuredUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Classe de testes para operações relacionadas a usuários na API.
 * Contém cenários para listar, buscar e remover usuários, incluindo casos positivos e negativos.
 */
@ExtendWith(ReportUtils.class)
public class UserTests extends BaseTest {

    /**
     * Testa a listagem de todos os usuários.
     * Verifica se a resposta retorna status 200 e a lista não está vazia.
     * Também valida os campos essenciais de cada usuário retornado.
     */
    @Test
    @DisplayName("Listar usuários")
    public void testGetAllUsers(){
        var response = UserService.getUsers();

        RestAssuredUtils.validateStatusCode(response, 200);
        assertFalse(response.jsonPath().getList("users").isEmpty(), "Lista de usuários não deve estar vazia");

        // Validar campos essenciais para cada usuário
        response.jsonPath().getList("users").forEach(user -> {
            assertNotNull(((java.util.Map<?, ?>) user).get("id"), "Usuário deve ter ID");
            assertNotNull(((java.util.Map<?, ?>) user).get("firstName"), "Usuário deve ter firstName");
            assertNotNull(((java.util.Map<?, ?>) user).get("lastName"), "Usuário deve ter lastName");
            assertNotNull(((java.util.Map<?, ?>) user).get("email"), "Usuário deve ter email");
            assertNotNull(((java.util.Map<?, ?>) user).get("phone"), "Usuário deve ter phone");
            assertNotNull(((java.util.Map<?, ?>) user).get("username"), "Usuário deve ter username");
        });

        ReportUtils.logInfo("Lista de usuários obtida com sucesso - Status code: 200");
    }

    /**
     * Testa a busca de um usuário por ID existente.
     * Verifica se a resposta retorna status 200 e os campos essenciais do usuário.
     */
    @Test
    @DisplayName("Buscar usuário pelo ID Existente")
    public void testGetUserById() {
        var response = UserService.getUsersById(ApiConfig.getExistingProductId());

        RestAssuredUtils.validateStatusCode(response, 200);
        assertNotNull(response.jsonPath().get("id"), "Usuário deve ter ID");
        assertNotNull(response.jsonPath().get("firstName"), "Usuário deve ter firstName");
        assertNotNull(response.jsonPath().get("lastName"), "Usuário deve ter lastName");
        assertNotNull(response.jsonPath().get("email"), "Usuário deve ter email");
        assertNotNull(response.jsonPath().get("phone"), "Usuário deve ter phone");
        assertNotNull(response.jsonPath().get("username"), "Usuário deve ter username");

        ReportUtils.logInfo("Usuário obtido por ID com sucesso - Status code: 200");
    }

    /**
     * Testa a busca de um usuário por ID inexistente.
     * Verifica se a resposta retorna status 404 e a mensagem de erro apropriada.
     */
    @Test
    @DisplayName("Buscar usuário pelo ID Inexistente")
    public void testGetNonExistingUser() {
        var response = UserService.getUsersById(ApiConfig.getNonExistingProductId());

        RestAssuredUtils.validateStatusCode(response, 404);
        assertEquals(
                "User with id '" + ApiConfig.getNonExistingProductId()+ "' not found",
                response.jsonPath().get("message")
        );

        ReportUtils.logInfo("Usuário inexistente - Status code: 404");
    }

    /**
     * Testa a busca de um usuário por ID alfabético inválido.
     * Verifica se a resposta retorna status 400 e a mensagem de erro apropriada.
     */
    @Test
    @DisplayName("Buscar usuário pelo ID Inexistente (Alfabético)")
    public void testGetNonExistingUserAfabetic() {
        var response = UserService.getUsersById("inv");

        RestAssuredUtils.validateStatusCode(response, 400);
        assertEquals("Invalid user id 'inv'", response.jsonPath().get("message"));

        ReportUtils.logInfo("Usuário inexistente - Status code: 400");
    }

    /**
     * Testa a remoção de um usuário por ID existente.
     * Verifica se a resposta retorna status 200, os campos essenciais e se o usuário foi marcado como deletado.
     */
    @Test
    @DisplayName("Deletar usuário pelo ID Existente")
    public void testDelUserById() {
        var response = UserService.delUsersById(ApiConfig.getExistingProductId());

        RestAssuredUtils.validateStatusCode(response, 200);
        assertNotNull(response.jsonPath().get("id"), "Usuário deve ter ID");
        assertNotNull(response.jsonPath().get("firstName"), "Usuário deve ter firstName");
        assertNotNull(response.jsonPath().get("lastName"), "Usuário deve ter lastName");
        assertNotNull(response.jsonPath().get("email"), "Usuário deve ter email");
        assertNotNull(response.jsonPath().get("phone"), "Usuário deve ter phone");
        assertNotNull(response.jsonPath().get("username"), "Usuário deve ter username");
        assertEquals(true, response.jsonPath().get("isDeleted"));

        ReportUtils.logInfo("Usuário deletado por ID com sucesso - Status code: 200");
    }
}
