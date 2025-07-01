package br.com.iuriraredu.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo que representa a resposta de autenticação da API.
 * Contém os campos retornados após o login, como tokens, dados do usuário e mensagens.
 */
@Getter
@Setter
public class AuthResponse {
    /**
     * Token de acesso JWT retornado após autenticação bem-sucedida.
     */
    private String accessToken;

    /**
     * Token de atualização para renovar o accessToken.
     */
    private String refreshToken;

    /**
     * Identificador único do usuário autenticado.
     */
    private Integer id;

    /**
     * Nome de usuário do usuário autenticado.
     */
    private String username;

    /**
     * E-mail do usuário autenticado.
     */
    private String email;

    /**
     * Primeiro nome do usuário.
     */
    private String firstName;

    /**
     * Sobrenome do usuário.
     */
    private String lastName;

    /**
     * Gênero do usuário.
     */
    private String gender;

    /**
     * URL da imagem do usuário.
     */
    private String image;

    /**
     * Mensagem de resposta da API (ex: erro ou status).
     */
    private String message;
}
