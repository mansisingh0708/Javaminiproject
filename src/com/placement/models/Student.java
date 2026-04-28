package com.placement.models;

import java.util.List;

// INHERITANCE: Student extends Person, so Student IS-A Person.
// Student inherits the name field and introduce() method from Person.
public class Student extends Person {

    // ENCAPSULATION: private fields — can only be accessed via getters
    private double cgpa;
    private List<String> skills;
    private String branch;

    public Student(String name, double cgpa, List<String> skills, String branch) {
        super(name); // calls the Person constructor to set name
        this.cgpa = cgpa;
        this.skills = skills;
        this.branch = branch;
    }

    // ENCAPSULATION: getters provide read access to private fields
    public double getCgpa() {
        return cgpa;
    }

    public List<String> getSkills() {
        return skills;
    }

    public String getBranch() {
        return branch;
    }

    // POLYMORPHISM: overrides the abstract getRole() method from Person.
    // When getRole() is called on a Student object, this version runs.
    @Override
    public String getRole() {
        return "Student";
    }
}
