package io.github.biezhi.model;

import lombok.Data;

@Data
public class Human implements Mammal {

    protected Long id;
    protected String name;

}