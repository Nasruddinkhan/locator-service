package com.mypractice.util;

import lombok.Data;
import org.apache.commons.collections4.MultiValuedMap;

@Data
public class ValuePropModel {
    private String promoValue;
    private String vpid;
    private String language;
    private MultiValuedMap<String, String> props;
}
