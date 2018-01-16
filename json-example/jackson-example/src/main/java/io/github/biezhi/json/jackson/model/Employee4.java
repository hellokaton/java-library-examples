package io.github.biezhi.json.jackson.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee4 {

    private String name;
    private String dept;
    private String address;

}