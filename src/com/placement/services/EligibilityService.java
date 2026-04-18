package com.placement.services;

import com.placement.models.Company;
import com.placement.models.Student;

import java.util.List;
import java.util.stream.Collectors;

public class EligibilityService {

    public List<Company> getEligibleCompanies(Student student, List<Company> allCompanies) {
        return allCompanies.stream()
                .filter(company -> isEligible(student, company))
                .collect(Collectors.toList());
    }

    private boolean isEligible(Student student, Company company) {
        if (student.getCgpa() < company.getMinCgpa()) {
            return false;
        }
        List<String> studentSkills = student.getSkills().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        for (String reqSkill : company.getRequiredSkills()) {
            if (studentSkills.contains(reqSkill.toLowerCase())) {
                return true; 
            }
        }

        return false;
    }
}
