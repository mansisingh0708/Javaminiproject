package com.placement.models;

import java.util.List;

public class Company {
    private String name;
    private double minCgpa;
    private List<String> requiredSkills;

    public Company(String name, double minCgpa, List<String> requiredSkills) {
        this.name = name;
        this.minCgpa = minCgpa;
        this.requiredSkills = requiredSkills;
    }

    public String getName() {
        return name;
    }

    public double getMinCgpa() {
        return minCgpa;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", minCgpa=" + minCgpa +
                ", requiredSkills=" + requiredSkills +
                '}';
    }
}
