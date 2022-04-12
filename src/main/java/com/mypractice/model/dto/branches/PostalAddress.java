package com.mypractice.model.dto.branches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mypractice.model.dto.common.GeoLocation;
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
public class PostalAddress {
    @JsonProperty("AddressLine")
    public List<String> addressLine;
    @JsonProperty("City")
    public String city;
    @JsonProperty("CountrySubDivision")
    public List<String> countrySubDivision;
    @JsonProperty("Country")
    public String country;
    @JsonProperty("PostCode")
    public String postCode;
    @JsonProperty("GeoLocation")
    public GeoLocation geoLocation;
}
