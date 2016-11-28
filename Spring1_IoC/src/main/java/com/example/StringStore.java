package com.example;

public class StringStore implements Store<String>{
    private String name = "default str";

    public String getStore() {
        return "str";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
