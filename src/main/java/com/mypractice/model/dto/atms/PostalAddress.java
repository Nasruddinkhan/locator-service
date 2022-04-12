package com.mypractice.model.dto.atms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mypractice.model.dto.common.GeoLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostalAddress {
    @JsonProperty("AddressLine")
    public String addressLine;
    @JsonProperty("BuildingNumber")
    public String buildingNumber;
    @JsonProperty("StreetName")
    public String streetName;
    @JsonProperty("City")
    public String city;
    @JsonProperty("Country")
    public String country;
    @JsonProperty("PostCode")
    public String postCode;
    @JsonProperty("GeoLocation")
    public GeoLocation geoLocation;
}
