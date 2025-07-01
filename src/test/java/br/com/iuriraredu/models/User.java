package br.com.iuriraredu.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo que representa um usuário retornado pela API.
 * Contém os principais atributos de um usuário, como identificador, nome, e-mail, telefone e username.
 */
@Getter
@Setter
public class User {
    /**
     * Identificador único do usuário.
     */
    private Integer id;

    /**
     * Primeiro nome do usuário.
     */
    private String firstName;

    /**
     * Sobrenome do usuário.
     */
    private String lastName;

    /**
     * E-mail do usuário.
     */
    private String email;

    /**
     * Telefone do usuário.
     */
    private String phone;

    /**
     * Nome de usuário (username).
     */
    private String username;
}
