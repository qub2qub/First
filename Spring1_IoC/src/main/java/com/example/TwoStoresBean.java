package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwoStoresBean {
    @Autowired
    private Store<String> s1; // <String> qualifier, injects the stringStore bean

    @Autowired
    private Store<Integer> s2; // <Integer> qualifier, injects the integerStore bean

    @Override
    public String toString() {
        return "TwoStoresBean{" +
                "s1=" +s1.getName()+", "+ s1.getStore() +
                ", s2=" +s2.getName()+", " + s2.getStore() +
                '}';
    }
}
