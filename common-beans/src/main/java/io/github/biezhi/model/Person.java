package io.github.biezhi.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString(callSuper = true)
public class Person extends Human {

    private Gender gender;
    private Address address;
    private Date birthDate;
    private String phoneNumber;
    private List<String> nicknames;

}