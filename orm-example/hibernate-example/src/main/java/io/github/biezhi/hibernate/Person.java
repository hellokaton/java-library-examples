package io.github.biezhi.hibernate;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author biezhi
 * @date 2018/1/16
 */
@Data
@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "FULL_NAME")
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}