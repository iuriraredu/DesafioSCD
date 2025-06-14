package br.com.iuriraredu.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User{
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
}
