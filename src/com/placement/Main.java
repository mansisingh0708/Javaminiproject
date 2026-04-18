package com.placement;

import com.placement.models.Company;
import com.placement.models.Student;
import com.placement.services.EligibilityService;
import com.placement.services.FileService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final FileService fileService = new FileService();
    private static final EligibilityService eligibilityService = new EligibilityService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("================================");
        System.out.println(" Student Placement Eligibility ");
        System.out.println("================================");

        List<Company> allCompanies = fileService.loadCompanies();

        while (true) {
            System.out.println("\n1. Check Eligibility");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                checkEligibility(scanner, allCompanies);
            } else if (choice.equals("2")) {
                System.out.println("Exiting... Good luck with your placements!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void checkEligibility(Scanner scanner, List<Company> allCompanies) {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();

        double cgpa = -1;
        while (cgpa < 0 || cgpa > 10) {
            System.out.print("Enter your CGPA (0.0 - 10.0): ");
            try {
                cgpa = Double.parseDouble(scanner.nextLine());
                if (cgpa < 0 || cgpa > 10) {
                    System.out.println("Please enter a valid CGPA between 0 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

        System.out.print("Enter your skills (comma separated, e.g., Java, Python, SQL): ");
        String skillsInput = scanner.nextLine();
        List<String> skills = Arrays.stream(skillsInput.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        Student student = new Student(name, cgpa, skills);
        List<Company> eligibleCompanies = eligibilityService.getEligibleCompanies(student, allCompanies);

        System.out.println("\n--- Eligibility Result for " + name + " ---");
        if (eligibleCompanies.isEmpty()) {
            System.out.println("Sorry, you are currently not eligible for any companies based on the criteria.");
        } else {
            System.out.println("You are eligible for the following companies:");
            System.out.printf("%-15s | %-8s | %-30s%n", "Company", "Min CGPA", "Required Skills");
            System.out.println("------------------------------------------------------------");
            for (Company company : eligibleCompanies) {
                System.out.printf("%-15s | %-8.2f | %-30s%n",
                        company.getName(),
                        company.getMinCgpa(),
                        String.join(", ", company.getRequiredSkills()));
            }
        }
    }
}
