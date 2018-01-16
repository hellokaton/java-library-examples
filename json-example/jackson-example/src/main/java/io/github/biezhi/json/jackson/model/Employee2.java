package io.github.biezhi.json.jackson.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Employee2 {

    private String name;

    private String dept;

    @JsonIgnore
    private String address;
}