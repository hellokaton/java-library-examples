package io.github.biezhi.json.gson.model;

public class Animal<T> {

    private T animal;

    public T getAnimal() {
        return animal;
    }

    public void setAnimal(T animal) {
        this.animal = animal;
    }
}