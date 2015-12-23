package com.example.java_8;

/**
 * Created by colin on 15-12-17.
 */
public class PersonFactoryImp implements PersonFactory {
    @Override
    public Person create(String firstName, String lastName) {
        return new Person(firstName,lastName);
    }
}
