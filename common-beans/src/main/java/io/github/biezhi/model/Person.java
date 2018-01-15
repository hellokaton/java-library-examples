package io.github.biezhi.model;

import io.github.benas.randombeans.annotation.Exclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Person extends Human {

    protected transient String email;

    protected Gender gender;

    protected Address address;

    protected Date birthDate;

    protected String phoneNumber;

    protected List<String> nicknames;

    @Exclude
    protected String excluded;

}