package br.com.iuriraredu.tests;

import br.com.iuriraredu.config.PropertiesConfig;
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

@ExtendWith(ReportUtils.class)
public class UserTests extends BaseTest {
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

    @Test
    @DisplayName("Buscar usuário pelo ID Existente")
    public void testGetUserById() {
        var response = UserService.getUsersById(PropertiesConfig.getExistingProductId());

        RestAssuredUtils.validateStatusCode(response, 200);
        assertNotNull(response.jsonPath().get("id"), "Usuário deve ter ID");
        assertNotNull(response.jsonPath().get("firstName"), "Usuário deve ter firstName");
        assertNotNull(response.jsonPath().get("lastName"), "Usuário deve ter lastName");
        assertNotNull(response.jsonPath().get("email"), "Usuário deve ter email");
        assertNotNull(response.jsonPath().get("phone"), "Usuário deve ter phone");
        assertNotNull(response.jsonPath().get("username"), "Usuário deve ter username");

        ReportUtils.logInfo("Usuário obtido por ID com sucesso - Status code: 200");
    }

    @Test
    @DisplayName("Buscar usuário pelo ID Inexistente")
    public void testGetNonExistingUser() {
        var response = UserService.getUsersById(PropertiesConfig.getNonExistingProductId());

        RestAssuredUtils.validateStatusCode(response, 404);
        assertEquals(
                "User with id '" + PropertiesConfig.getNonExistingProductId()+ "' not found",
                response.jsonPath().get("message")
        );

        ReportUtils.logInfo("Usuário inexistente - Status code: 404");
    }

    @Test
    @DisplayName("Buscar usuário pelo ID Inexistente (Alfabético)")
    public void testGetNonExistingUserAfabetic() {
        var response = UserService.getUsersById("inv");

        RestAssuredUtils.validateStatusCode(response, 400);
        assertEquals("Invalid user id 'inv'", response.jsonPath().get("message"));

        ReportUtils.logInfo("Usuário inexistente - Status code: 400");
    }

    @Test
    @DisplayName("Deletar usuário pelo ID Existente")
    public void testDelUserById() {
        var response = UserService.delUsersById(PropertiesConfig.getExistingProductId());

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
