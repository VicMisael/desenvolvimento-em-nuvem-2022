package br.ufc.nuvem.patrimoniomanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/alive")
public class AliveController {

    @GetMapping
    public String getAnswer() {
        String SystemName;
        try {
            SystemName
                    = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return "ALIVE " + SystemName;
    }
}
