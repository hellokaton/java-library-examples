package io.github.biezhi.json.jackson.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties({"dept", "address"})
public class Employee3 {

    private String name;
    private String dept;
    private String address;
}