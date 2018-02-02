package io.github.biezhi.json.gson.model;

public class Box<T> {

    private T boxContent;

    public Box(T t) {
        this.boxContent = t;
    }

}
