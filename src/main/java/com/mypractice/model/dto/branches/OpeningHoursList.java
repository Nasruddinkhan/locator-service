package com.mypractice.model.dto.branches;

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
public class OpeningHoursList {
    @JsonProperty("OpeningTime")
    public String openingTime;
    @JsonProperty("ClosingTime")
    public String closingTime;
}
