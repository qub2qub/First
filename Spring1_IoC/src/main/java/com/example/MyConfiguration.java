package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public StringStore stringStore() {
        StringStore stringStore = new StringStore();
        stringStore.setName("str config");
        return stringStore;
    }

    @Bean
    public IntegerStore integerStore() {
        IntegerStore integerStore = new IntegerStore();
        integerStore.setName("int config");
        return integerStore;
    }

}