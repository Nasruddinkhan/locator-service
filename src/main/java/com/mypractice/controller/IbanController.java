package com.mypractice.controller;

import com.mypractice.model.error.ErrorDetails;
import com.mypractice.iban.Iban;
import com.mypractice.iban.IbanDto;
import com.mypractice.iban.IbanException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1.0/open-data")
public class IbanController {

    @PostMapping("/iban")
    public ResponseEntity<Iban> createIban(@RequestBody @Valid IbanDto ibanDto) {
        Iban iban = new Iban.Builder()
                .countryCode(ibanDto.getCountryCode())
                .bankCode(ibanDto.getBankCode())
                .branchCode(ibanDto.getBranchCode())
                .accountNumber(ibanDto.getAccountNumber()).build();
        return new ResponseEntity<>(iban, HttpStatus.CREATED);
    }

    @GetMapping("/iban/{iban}/validate")
    public ResponseEntity<ErrorDetails> validateIban(@PathVariable String iban) {
        Iban validIbn = Iban.valueOf(iban);
        ErrorDetails errorDetails = Optional.ofNullable(validIbn).map(e -> ErrorDetails.builder().errorCode(200)
                .errorMessage(iban + " iban value validate successfully").build()).orElseThrow(() -> new IbanException("passing invalid iban number"));
        return new ResponseEntity<>(errorDetails, HttpStatus.OK);
    }
}
