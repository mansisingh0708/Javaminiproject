package com.placement;

import com.placement.models.Company;
import com.placement.models.Person;
import com.placement.models.PlacementDrive;
import com.placement.models.Student;
import com.placement.services.EligibilityService;
import com.placement.services.FileService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Load all companies from the data file
        FileService fileService = new FileService();
        List<Company> allCompanies = fileService.loadCompanies();

        EligibilityService eligibilityService = new EligibilityService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("  Campus Placement Eligibility System");
        System.out.println("=========================================");

        while (true) {
            System.out.println("\n  1. Check My Eligibility");
            System.out.println("  2. View All Companies");
            System.out.println("  3. Exit");
            System.out.print("  Choose an option: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                checkEligibility(scanner, allCompanies, eligibilityService);

            } else if (choice.equals("2")) {
                viewAllCompanies(allCompanies);

            } else if (choice.equals("3")) {
                System.out.println("\n  Goodbye! Best of luck with your placements!");
                break;

            } else {
                System.out.println("  Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }


    // Asks the user for their details, then shows which companies they qualify for
    private static void checkEligibility(Scanner scanner, List<Company> allCompanies,
                                          EligibilityService eligibilityService) {

        System.out.print("\n  Enter your name: ");
        String name = scanner.nextLine().trim();

        System.out.print("  Enter your CGPA (e.g. 8.5): ");
        double cgpa = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("  Enter your branch (e.g. Computer Science): ");
        String branch = scanner.nextLine().trim();

        System.out.print("  Enter your skills separated by commas (e.g. Java, Python, SQL): ");
        String[] skillsArray = scanner.nextLine().split(",");

        List<String> skills = new ArrayList<>();
        for (String skill : skillsArray) {
            String s = skill.trim();
            if (!s.isEmpty()) {
                skills.add(s);
            }
        }

        Student student = new Student(name, cgpa, skills, branch);

        List<Company> eligibleCompanies = eligibilityService.getEligibleCompanies(student, allCompanies);

        eligibilityService.printReport(student, eligibleCompanies);
    }

    // Prints a table of all companies
    private static void viewAllCompanies(List<Company> companies) {
        System.out.println("\n  --- All Registered Companies ---");

        if (companies.isEmpty()) {
            System.out.println("  No companies found.");
            return;
        }

        System.out.printf("  %-18s | %-8s | %-12s | %-30s%n",
                "Company", "Min CGPA", "Sector", "Required Skills");
        System.out.println("  " + "-".repeat(75));

        for (Company c : companies) {
            System.out.printf("  %-18s | %-8.2f | %-12s | %-30s%n",
                    c.getName(),
                    c.getMinCgpa(),
                    c.getSector(),
                    String.join(", ", c.getRequiredSkills()));
        }
    }
}
