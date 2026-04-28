package com.placement.models;

// ABSTRACTION: abstract class that cannot be instantiated directly.
// Any class that extends Person MUST implement the getRole() method.
public abstract class Person {

    // ENCAPSULATION: name is private — only accessible through getter/setter
    private String name;

    public Person(String name) {
        this.name = name;
    }

    // ENCAPSULATION: controlled access to the private field
    public String getName() {
        return name;
    }

    // ABSTRACTION: abstract method — subclasses must define what role they play
    public abstract String getRole();

    // Shared method available to all subclasses — prints a simple introduction
    public void introduce() {
        System.out.println("Hi, I am " + name + " and I am a " + getRole());
    }
}
