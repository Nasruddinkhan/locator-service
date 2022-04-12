package com.mypractice.model.dto.branches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mypractice.enums.OBBranchType1Code;
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
public class BranchList {
    @JsonProperty("Identification")
    public String identification;
    @JsonProperty("SequenceNumber")
    public String sequenceNumber;
    @JsonProperty("Name")
    public String name;
    @JsonProperty("Type")
    public OBBranchType1Code type;
    @JsonProperty("CustomerSegment")
    public List<String> customerSegment;
    @JsonProperty("Accessibility")
    public List<String> accessibility;
    @JsonProperty("ServiceAndFacility")
    public List<String> serviceAndFacility;
    @JsonProperty("Availability")
    public Availability availability;
    @JsonProperty("ContactInfo")
    public List<ContactInfoList> contactInfo;
    @JsonProperty("PostalAddress")
    public PostalAddress postalAddress;
}
