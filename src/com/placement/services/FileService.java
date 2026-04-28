package com.placement.services;

import com.placement.models.Company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Reads company data from the text file
public class FileService {

    // Path to the data file
    private static final String FILE_PATH = "data/companies.txt";

    // Reads all companies from the file and returns them as a list
    // File format per line: CompanyName,minCgpa,skill1,skill2,...,SECTOR:SectorName
    public List<Company> loadCompanies() {
        List<Company> companies = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Data file not found: " + FILE_PATH);
            return companies;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Skip empty lines and comment lines
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(",");

                // Need at least: name, minCgpa, one skill
                if (parts.length < 3) {
                    continue;
                }

                String name = parts[0].trim();
                double minCgpa = Double.parseDouble(parts[1].trim());

                // The last part might be a sector tag like "SECTOR:Finance"
                String sector = "Technology";
                int skillEnd = parts.length;

                if (parts[parts.length - 1].trim().startsWith("SECTOR:")) {
                    sector = parts[parts.length - 1].trim().substring(7).trim();
                    skillEnd = parts.length - 1;
                }

                // Collect all skills between index 2 and skillEnd
                List<String> skills = new ArrayList<>();
                for (int i = 2; i < skillEnd; i++) {
                    skills.add(parts[i].trim().toLowerCase());
                }

                companies.add(new Company(name, minCgpa, skills, sector));
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Bad number in file: " + e.getMessage());
        }

        return companies;
    }
}
