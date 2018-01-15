package io.github.biezhi.model;

import lombok.Data;

@Data
public class Address {

    private Street street;
    private String zipCode;
    private String city;
    private String country;

}