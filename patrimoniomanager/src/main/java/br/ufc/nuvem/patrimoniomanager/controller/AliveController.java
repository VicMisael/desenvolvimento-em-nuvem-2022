package br.ufc.nuvem.patrimoniomanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alive")
public class AliveController {

    @GetMapping
    public String getAnswer() {
        return "ALIVE";
    }
}
