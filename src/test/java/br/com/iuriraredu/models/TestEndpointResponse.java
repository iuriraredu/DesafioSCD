package br.com.iuriraredu.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo que representa a resposta do endpoint de teste da API.
 * Contém informações sobre o status da resposta e o método HTTP utilizado.
 */
@Getter
@Setter
public class TestEndpointResponse{
    /**
     * Status retornado pela API (ex: "success", "error").
     */
    private String status;
    /**
     * Método HTTP utilizado na requisição (ex: "GET", "POST").
     */
    private String method;
}
