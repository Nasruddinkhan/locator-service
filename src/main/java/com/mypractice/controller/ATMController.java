package com.mypractice.controller;

import com.mypractice.model.dto.atms.OpenBankingATM;
import com.mypractice.sevices.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/v1.0/open-data")
public class ATMController {
    /**
     *
     */
    private final transient ATMService atmService;

    /**
     * @param atmService
     */
    @Autowired
    public ATMController(final ATMService atmService) {
        this.atmService = atmService;
    }

    /**
     * @return
     */
    @GetMapping("/atms")
    public ResponseEntity<OpenBankingATM> getAllAtmsDetails() {
        return new ResponseEntity<>(atmService.getAtmsDetails(), HttpStatus.OK);
    }
}
