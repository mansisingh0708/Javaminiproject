package com.placement.services;

import com.placement.models.Company;

import java.io.*;
import java.util.*;

public class FileService {
    private static final String FILE_PATH = "data/companies.txt";

    public List<Company> loadCompanies() {
        List<Company> companies = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.err.println("Data file not found: " + FILE_PATH);
            return companies;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String name = parts[0].trim();
                    double minCgpa = Double.parseDouble(parts[1].trim());
                    List<String> requiredSkills = new ArrayList<>();
                    for (int i = 2; i < parts.length; i++) {
                        requiredSkills.add(parts[i].trim().toLowerCase());
                    }
                    companies.add(new Company(name, minCgpa, requiredSkills));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading company data: " + e.getMessage());
        }
        return companies;
    }

    public void saveCompany(Company company) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(company.getName()).append(",").append(company.getMinCgpa());
            for (String skill : company.getRequiredSkills()) {
                sb.append(",").append(skill);
            }
            writer.println(sb.toString());
        } catch (IOException e) {
            System.err.println("Error saving company data: " + e.getMessage());
        }
    }
}
