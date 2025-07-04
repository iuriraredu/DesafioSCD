package br.com.iuriraredu.tests;

import br.com.iuriraredu.config.BaseTest;
import br.com.iuriraredu.models.TestEndpointResponse;
import br.com.iuriraredu.services.TestEndpointService;
import br.com.iuriraredu.utils.ReportUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de testes para o endpoint de teste da API.
 * Verifica se o endpoint /test retorna status e método corretos.
 */
@ExtendWith(ReportUtils.class)
public class TestEndpointTests extends BaseTest {
    /**
     * Testa o endpoint /test.
     * Verifica se o status retornado é "ok" e o método é "GET".
     */
    @Test
    @DisplayName("Testar endpoint /test")
    public void testTestEndpoint(){
        TestEndpointResponse response = TestEndpointService.getTestEndpoint();

        assertAll("Verificar resposta do endpoint /test",
                () -> assertEquals("ok", response.getStatus(), "Status deve ser 'ok'"),
                () -> assertEquals("GET", response.getMethod(), "Method deve ser 'GET'")
        );

        ReportUtils.logInfo("Endpoint /test testado com sucesso - Status code: 200");
    }
}
