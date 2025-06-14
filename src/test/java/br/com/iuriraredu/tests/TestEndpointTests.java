package br.com.iuriraredu.tests;

import br.com.iuriraredu.config.TestConfig;
import br.com.iuriraredu.models.TestResponse;
import br.com.iuriraredu.services.TestService;
import br.com.iuriraredu.utils.ReportUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ReportUtils.class)
public class TestEndpointTests extends TestConfig{
    @Test
    @DisplayName("Testar endpoint /test")
    public void testTestEndpoint(){
        TestResponse response = TestService.getTestEndpoint();

        // Validações equivalentes às do Postman
        assertAll("Verificar resposta do endpoint /test",
                () -> assertEquals("ok", response.getStatus(), "Status deve ser 'ok'"),
                () -> assertEquals("GET", response.getMethod(), "Method deve ser 'GET'")
        );

        ReportUtils.logInfo("Endpoint /test testado com sucesso");
    }
}
