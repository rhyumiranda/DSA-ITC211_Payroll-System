import java.util.*;

public class GroupThree_ProblemThree {
    public static class Employee {
        private String employeeName;
        private double employeeID;
        private String employeeDepartment;
        private String employeeRank;
        private double hours;
        private double minutes;
        private double seconds;
        private int absencesCountDeduction;
        private double tardinessDeduction;
        private double undertimeDeduction;

        public Employee(
                String employeeName,
                double employeeID,
                String employeeDepartment,
                String employeeRank,
                double hours,
                double minutes,
                double seconds,
                int absencesCountDeduction,
                double tardinessDeduction,
                double undertimeDeduction) {
            this.employeeName = employeeName;
            this.employeeID = employeeID;
            this.employeeDepartment = employeeDepartment;
            this.employeeRank = employeeRank;
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
            this.absencesCountDeduction = absencesCountDeduction;
            this.tardinessDeduction = tardinessDeduction;
            this.undertimeDeduction = undertimeDeduction;
        }

        @Override
        public String toString() {
            return String.format("Employee Details:\n" +
                            "  Name: %s\n" +
                            "  ID: %.0f\n" +
                            "  Department: %s\n" +
                            "  Rank: %s\n" +
                            "  Time: %.0fh %.0fm %.0fs\n" +
                            "  Absences: %d\n" +
                            "  Tardiness Deduction: %.2f\n" +
                            "  Undertime Deduction: %.2f\n",
                    employeeName, employeeID, employeeDepartment, employeeRank,
                    hours, minutes, seconds, absencesCountDeduction,
                    tardinessDeduction, undertimeDeduction);
        }
    }

    public static class PayrollSystem {
        LinkedList<Employee> employees = new LinkedList<>();
        private Scanner input = new Scanner(System.in);
        private int employeeCounter = 0; 
      
        public static final String RESET = "\u001B[0m";
        public static final String CYAN = "\u001B[36m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String RED = "\u001B[31m";

        public void createEmployee() {
            System.out.print("Enter the number of employees to create: ");
            int numberOfEmployees = input.nextInt();
            input.nextLine(); 

            for (int i = 0; i < numberOfEmployees; i++) {
                System.out.println("================================");
                System.out.println("Entering details for employee " + (employeeCounter + 1) + ":");

                System.out.print("Employee Name: ");
                String name = input.nextLine();

                System.out.print("Employee ID: ");
                double id = input.nextDouble();
                input.nextLine(); 

                System.out.print("Department: ");
                String department = input.nextLine();

                System.out.print("Rank: ");
                String rank = input.nextLine();

                System.out.print("Hours: ");
                double hours = input.nextDouble();

                System.out.print("Minutes: ");
                double minutes = input.nextDouble();

                System.out.print("Seconds: ");
                double seconds = input.nextDouble();

                System.out.print("Absence Count Deduction: ");
                int absencesCount = input.nextInt();

                System.out.print("Tardiness Deduction: ");
                double tardiness = input.nextDouble();

                System.out.print("Undertime Deduction: ");
                double undertime = input.nextDouble();
                input.nextLine(); 

                Employee employee = new Employee(name, id, department, rank, hours, minutes, seconds, absencesCount, tardiness, undertime);
                employees.add(employee);
                employeeCounter++; 
            }
            
            System.out.println("================================");
            System.out.println("\nEmployees Created:");
            System.out.println("================================");
            displayEmployee(); 
        }

        public void findEmployee() {
            // Source code for finding an employee here
        }

        public void updateEmployee() {
            // Source code for updating an employee here
        }

        public void deleteEmployee() {
            // Source code for deleting an employee here
        }

        public void displayEmployee() {
            if (employees.isEmpty()) {
                System.out.println("No employees to display.");
                return;
            }
            System.out.println("List of Employees:");
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }

        public void displayMenu() {
            boolean isActive = false;

            while (!isActive) {
                System.out.println("o===o===o Payroll System o===o===o");
                System.out.println("1. Create an Employee");
                System.out.println("2. Find an Employee");
                System.out.println("3. Update an Employee");
                System.out.println("4. Delete an Employee");
                System.out.println("5. Display Employees");
                System.out.println("6. Exit");
                System.out.print("> ");

                try {
                    int userChoice = input.nextInt();
                    System.out.println("o===o===o===o===o===o===o===o===o===o");

                    switch (userChoice) {
                        case 1:
                            createEmployee();
                            break;
                        case 2:
                            findEmployee();
                            break;
                        case 3:
                            updateEmployee();
                            break;
                        case 4:
                            deleteEmployee();
                            break;
                        case 5:
                            displayEmployee();
                            break;
                        case 6:
                            System.out.println("Exiting the program...");
                            isActive = true;
                            break;
                        default:
                            System.out.println("Invalid input. Please enter a number from 1 to 6.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    input.nextLine();
                }
                pause();
            }
        }

        private void pause() {
            System.out.print("Press Enter to continue...");
            input.nextLine();
            input.nextLine();
        }

        public static double convertTimeToHour(int hours, int minutes, int seconds) {
            double HOUR_CONSTANT_FOR_MINUTES = 60;
            double HOUR_CONSTANT_FOR_SECONDS = 3600;
            double convertedMinutes = minutes / HOUR_CONSTANT_FOR_MINUTES;
            double convertedSeconds = seconds / HOUR_CONSTANT_FOR_SECONDS;
            return hours + convertedMinutes + convertedSeconds;
        }
    }

    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        payrollSystem.displayMenu();
    }
}
