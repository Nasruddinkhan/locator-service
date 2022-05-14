package com.mypractice.controller;

import com.mypractice.model.dto.atms.OpenBankingATM;
import com.mypractice.sevices.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

/**
 *
 */
@RestController
@RequestMapping("/v1.0/open-data")
public class ATMController {
    /**
     *
     */
    private final  ATMService atmService;
    private final DiscoveryClient discoveryClient;

    /**
     * @param atmService
     * @param discoveryClient
     */
    @Autowired
    public ATMController(final ATMService atmService, DiscoveryClient discoveryClient) {
        this.atmService = atmService;
        this.discoveryClient = discoveryClient;
    }

    /**
     * @return
     */
    @GetMapping("/atms")
    public ResponseEntity<OpenBankingATM> getAllAtmsDetails() {
        return new ResponseEntity<>(atmService.getAtmsDetails(), HttpStatus.OK);
    }


    public Optional<URI> serviceUrl() {
        return discoveryClient.getInstances("myApp")
                .stream()
                .findFirst()
                .map(si -> si.getUri());
    }
}
