package com.mypractice.model.dto.branches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mypractice.enums.OBContactType1Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactInfoList {
    @JsonProperty("ContactType")
    public OBContactType1Code contactType;
    @JsonProperty("ContactContent")
    public String contactContent;
}
