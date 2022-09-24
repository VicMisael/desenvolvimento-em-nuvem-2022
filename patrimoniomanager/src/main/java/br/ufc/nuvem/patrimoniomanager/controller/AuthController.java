package br.ufc.nuvem.patrimoniomanager.controller;

import br.ufc.nuvem.patrimoniomanager.model.LoginUser;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

//    @PostMapping("/login")
//    public ResponseEntity<Usuario> login(@RequestBody LoginUser user) {
//
//
//    }
}
