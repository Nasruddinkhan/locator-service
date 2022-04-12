package com.mypractice.iban;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.PK)
                .bankCode("BUPB")
                .branchCode("")
                .accountNumber("001233770012337").build();
        System.out.println(iban.getIbanValue());
        IbanDto ibanDto =  new IbanDto();
        ibanDto.setAccountNumber("00123377");
        ibanDto.setBankCode("BUPB");
        ibanDto.setBranchCode("123456");
        ibanDto.setCountryCode(CountryCode.SA);

       String result = new ObjectMapper().writeValueAsString(ibanDto);
        System.out.println(result);
       // System.out.println(Iban.valueOf("SA55BUKB12345600223377").getIbanValue());
    }
}
