package com.example;

public class IntegerStore implements Store<Integer>{
    private String name = "default int";

    public Integer getStore() {
        return 123;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}