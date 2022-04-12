package com.mypractice.model.dto.atms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ATMList {

    @JsonProperty("Identification")
    public String identification;
    @JsonProperty("SupportedLanguages")
    public List<String> supportedLanguages;
    @JsonProperty("ATMServices")
    public List<String> aTMServices;
    @JsonProperty("CardServices")
    public List<String> cardServicesList;
    @JsonProperty("Accessibility")
    public List<String> accessibilityList;
    @JsonProperty("Access24HoursIndicator")
    public String access24HoursIndicator;
    @JsonProperty("SupportedCurrencies")
    public List<String> supportedCurrencies;
    @JsonProperty("MinimumPossibleAmount")
    public String minimumPossibleAmount;
    @JsonProperty("Branch")
    public Branch branch;
    @JsonProperty("Location")
    public LocationDto location;
}
