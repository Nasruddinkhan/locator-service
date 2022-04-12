package com.mypractice.model.dto.branches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mypractice.enums.OBDayName1Code;
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
public class Day {
    @JsonProperty("Name")
    public OBDayName1Code name;
    @JsonProperty("OpeningHours")
    public List<OpeningHoursList> openingHours;
}
