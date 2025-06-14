package br.com.iuriraredu.tests;

import br.com.iuriraredu.config.TestConfig;
import br.com.iuriraredu.services.UserService;
import br.com.iuriraredu.utils.ReportUtils;
import br.com.iuriraredu.utils.RestAssuredUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ReportUtils.class)
public class UserTests extends TestConfig{
    @Test
    @DisplayName("Listar usuários ")
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

        ReportUtils.logInfo("Lista de usuários obtida com sucesso");
    }
}
