package io.github.biezhi.json.jackson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Employee {

    @JsonProperty("employee-name")
    private String name;

    @JsonProperty("employee-dept")
    private String dept;

}