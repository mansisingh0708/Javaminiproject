package com.placement.models;

import java.util.List;

// INHERITANCE: PlacementDrive extends Company, so it IS-A Company.
// It inherits all fields (name, minCgpa, skills, sector) and methods from Company.
// It also adds new fields: driveDate and packageLPA.
public class PlacementDrive extends Company {

    private String driveDate;
    private double packageLPA; // salary offered in Lakhs Per Annum

    public PlacementDrive(String name, double minCgpa, List<String> requiredSkills,
                          String sector, String driveDate, double packageLPA) {
        super(name, minCgpa, requiredSkills, sector); // calls the Company constructor
        this.driveDate = driveDate;
        this.packageLPA = packageLPA;
    }

    public String getDriveDate() {
        return driveDate;
    }

    public double getPackageLPA() {
        return packageLPA;
    }

    // POLYMORPHISM: overrides getInfo() from Company.
    // When getInfo() is called on a PlacementDrive object, this version runs
    // — it adds drive date and package info on top of the Company info.
    @Override
    public String getInfo() {
        return super.getInfo() + " | Drive Date: " + driveDate + " | Package: " + packageLPA + " LPA";
    }
}
