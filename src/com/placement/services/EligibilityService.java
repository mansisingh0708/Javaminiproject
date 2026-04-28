package com.placement.services;

import com.placement.models.Company;
import com.placement.models.Student;

import java.util.ArrayList;
import java.util.List;

// Checks which companies a student is eligible for
public class EligibilityService {

    // Returns a list of companies the student qualifies for
    public List<Company> getEligibleCompanies(Student student, List<Company> allCompanies) {
        List<Company> eligibleList = new ArrayList<>();

        for (Company company : allCompanies) {
            if (isEligible(student, company)) {
                eligibleList.add(company);
            }
        }

        return eligibleList;
    }

    // A student is eligible if their CGPA meets the minimum AND they have at least one required skill
    public boolean isEligible(Student student, Company company) {

        // Check CGPA first
        if (student.getCgpa() < company.getMinCgpa()) {
            return false;
        }

        // Check if student has at least one skill the company requires
        for (String requiredSkill : company.getRequiredSkills()) {
            for (String studentSkill : student.getSkills()) {
                if (studentSkill.equalsIgnoreCase(requiredSkill)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Prints a formatted report of eligible companies for the student
    public void printReport(Student student, List<Company> eligibleCompanies) {
        System.out.println("\n--- Eligibility Report for " + student.getName()
                + " (CGPA: " + student.getCgpa() + ") ---");

        if (eligibleCompanies.isEmpty()) {
            System.out.println("  No eligible companies found.");
        } else {
            System.out.printf("  %-18s | %-8s | %-12s | %-30s%n",
                    "Company", "Min CGPA", "Sector", "Required Skills");
            System.out.println("  " + "-".repeat(75));

            for (Company c : eligibleCompanies) {
                System.out.printf("  %-18s | %-8.2f | %-12s | %-30s%n",
                        c.getName(),
                        c.getMinCgpa(),
                        c.getSector(),
                        String.join(", ", c.getRequiredSkills()));
            }
        }
    }
}
