package com.example.Vaccination;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class VaccinationApplication {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Vaccination Application");

        while (true) {
            System.out.println("Please choose your vaccine type:");
            System.out.println("1. Covid");
            System.out.println("2. Polio");
            System.out.println("3. Typhoid");
            int vaccineChoice = scanner.nextInt();
            scanner.nextLine();

            String userChoice = "";
            switch (vaccineChoice) {
                case 1 -> userChoice = "Covid";
                case 2 -> userChoice = "Polio";
                case 3 -> userChoice = "Typhoid";

                default -> {
                    System.out.println("Invalid Choice..");
                    return;
                }
            }
            System.out.println("Whom do you want to vaccinate");
            System.out.println("1. Father");
            System.out.println("2. Mother");
            System.out.println("3. Self");
            System.out.println("4. Spouse");
            System.out.println("5. Exit");
            String userType = "";
            int userchoice = scanner.nextInt();
            scanner.nextLine();
            switch (userchoice) {
                case 1 -> userType = "father";
                case 2 -> userType = "mother";
                case 3 -> userType = "self";
                case 4 -> userType = "spouse";
                default -> {
                    System.out.println("Exiting.....");
                    return;
                }
            }
            String reqBean=userType+userChoice;
            System.out.println(userType);
            System.out.println(userChoice);
            System.out.println(reqBean);
            User user=(User) context.getBean(reqBean);
            if(user.IsVaccinated()){
                System.out.println("User is already vaccinated");
            }
            else {
                System.out.println("Please enter " + userType + " details:");
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

//		        Getting appointment details
                System.out.print("Appointment date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                System.out.print("Appointment time (HH:MM AM/PM): ");
                String time = scanner.nextLine();
                System.out.print("Appointment location: ");
                String location = scanner.nextLine();
                TimeAndLocation timeAndLocation = (TimeAndLocation) context.getBean("timeAndLocation");
                timeAndLocation.setDetails(time,location,date);
                user.setUserDetails(name, age, timeAndLocation);
                user.setAppointment();
            }

            System.out.println("Do you want to register for someone Else");
            System.out.println("1. Yes\n2. No");
            int input = scanner.nextInt();
            if (input == 2) break;
        }
        }
}