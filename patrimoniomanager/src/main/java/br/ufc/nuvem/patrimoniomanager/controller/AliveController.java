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

@RestController
@CrossOrigin("*")
@RequestMapping("/alive")
public class AliveController {

    @Value("${git.commit.message.short}")
    private String commitMessage;

    @Value("${git.branch}")
    private String branch;

    @Value("${git.commit.id}")
    private String commitId;

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
                buildProperties.getName() + " @ " + SystemName + " branch " + branch + " Commit " + commitId + " \n" +
                "compiled @ " + buildProperties.getTime();


    }
}
