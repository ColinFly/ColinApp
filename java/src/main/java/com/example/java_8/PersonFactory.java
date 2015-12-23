package com.example.java_8;

/**
 * Created by colin on 15-12-17.
 */
public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
