package com.placement.models;

import java.util.List;

// ENCAPSULATION: all fields are private — data is hidden inside the class.
// The only way to read them from outside is through the getters below.
public class Company {

    private String name;
    private double minCgpa;
    private List<String> requiredSkills;
    private String sector;

    public Company(String name, double minCgpa, List<String> requiredSkills, String sector) {
        this.name = name;
        this.minCgpa = minCgpa;
        this.requiredSkills = requiredSkills;
        this.sector = sector;
    }

    // ENCAPSULATION: getters — the only way to read the private fields
    public String getName() {
        return name;
    }

    public double getMinCgpa() {
        return minCgpa;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public String getSector() {
        return sector;
    }

    // Returns basic info about the company — can be overridden by subclasses
    public String getInfo() {
        return name + " | Sector: " + sector + " | Min CGPA: " + minCgpa
                + " | Skills: " + requiredSkills;
    }
}
