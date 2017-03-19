package Inheritance.InterfacesDefaultMethod;

import java.lang.*;

public class TestSimpleTimeClient {
    public static void main(String... args) {
        System.out.println("TEST inheritance 2 interfaces with overriden default method");
        TimeClient myTimeClient = new SimpleTimeClient();
        System.out.println("Current time: " + myTimeClient.toString());
        System.out.println("Time in California: " +
            myTimeClient.getZonedDateTime("Blah blah").toString());
    }
}