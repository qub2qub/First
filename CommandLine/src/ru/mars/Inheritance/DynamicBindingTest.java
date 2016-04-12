package ru.mars.Inheritance;

import java.util.ArrayList;
import java.util.List;

public class DynamicBindingTest {

    public static void main(String args[]) {
    	//here Type is vehicle but object will be Car
    	Vehicle vehicle = new Car(); 
    	//Car's start called because start() is overridden method
    	vehicle.start();
    	
    	List<Vehicle> list = new ArrayList<>();
    	list.add(new Vehicle());
    	list.add(new Car());
    	list.add(new Boat());
    	for (Vehicle v : list) {
			v.start();
		}
    }
}

class Vehicle {
    public void start() {
        System.out.println("Start Vehicle");
    }
}

class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Start Car");
    }
}

class Boat extends Vehicle {
	@Override
	public void start() {
		System.out.println("Start Boat");
	}
}