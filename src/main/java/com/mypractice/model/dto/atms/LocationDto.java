package com.mypractice.model.dto.atms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {

    @JsonProperty("Site")
    public Site site;
    @JsonProperty("PostalAddress")
    public PostalAddress postalAddress;
}
