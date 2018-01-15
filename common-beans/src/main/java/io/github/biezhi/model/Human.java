package io.github.biezhi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Human implements Serializable {

    protected Long id;
    protected String name;

}