package br.ufc.nuvem.patrimoniomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin("*")
@RequestMapping("/alive")
public class AliveController {
    @Autowired
    BuildProperties buildProperties;

    @GetMapping
    public String getAnswer() {
        String SystemName;
        try {
            SystemName
                    = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        return "ALIVE \n" +
                buildProperties.getName() + " running on " + SystemName +
                "compiled @ " + buildProperties.getTime().atOffset(ZoneOffset.ofHours(-3)).format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mma"));


    }
}
