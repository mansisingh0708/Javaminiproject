package com.placement.models;

import java.util.List;

public class Student {
    private String name;
    private double cgpa;
    private List<String> skills;

    public Student(String name, double cgpa, List<String> skills) {
        this.name = name;
        this.cgpa = cgpa;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", cgpa=" + cgpa +
                ", skills=" + skills +
                '}';
    }
}
