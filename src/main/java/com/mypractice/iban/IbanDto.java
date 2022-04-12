package com.mypractice.iban;

import lombok.Data;

@Data
public class IbanDto {

    private CountryCode countryCode;
    private String bankCode;
    private String branchCode;
    private String accountNumber;
}
