package br.ufc.nuvem.patrimoniomanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginUser {
    private String username;
    private String password;
}
