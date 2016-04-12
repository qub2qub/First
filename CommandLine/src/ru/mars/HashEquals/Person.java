package ru.mars.HashEquals;

import java.util.Comparator;

public class Person implements Comparable {

	private int person_id;
    private String name;
    
    /**
     * Comparator implementation which sorts old.Person objects on person_id field
     */
    public class SortByPerson_ID implements Comparator{

        public int compare(Object o1, Object o2) {
            Person p1 = (Person) o1;
            Person p2 = (Person) o2;
            return p1.getPersonId() - p2.getPersonId();
        }
    }
   
    public int getPersonId() {
		return person_id;
	}

	public void setPersonId(int person_id) {
		this.person_id = person_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * Compare current person with specified person
     * return zero if person_id for both person is same
     * return negative if current person_id is less than specified one
     * return positive if specified person_id is greater than specified one
     */
    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        return this.person_id - p.person_id ;
    }
 
}