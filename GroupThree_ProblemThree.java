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

// Using existing attributes to use as the scanner for creating an employee

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

        public void createEmployee() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the number of employees to create: ");
            int numberOfEmployees = scanner.nextInt();
            scanner.nextLine(); 

            for (int i = 0; i < numberOfEmployees; i++) {
                System.out.println("Entering details for employee " + (i + 1) + ":");

                employees.add(new Employee(
                    getInput(scanner, "Employee Name: "),
                    getDoubleInput(scanner, "Employee ID: "),
                    getInput(scanner, "Department: "),
                    getInput(scanner, "Rank: "),
                    getDoubleInput(scanner, "Hours: "),
                    getDoubleInput(scanner, "Minutes: "),
                    getDoubleInput(scanner, "Seconds: "),
                    getIntInput(scanner, "Absence Count Deduction: "),
                    getDoubleInput(scanner, "Tardiness Deduction: "),
                    getDoubleInput(scanner, "Undertime Deduction: ")
                ));
            }

            System.out.println("\nEmployees Created:");
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }

        private String getInput(Scanner scanner, String prompt) {
            System.out.print(prompt);
            return scanner.nextLine();
        }

        private double getDoubleInput(Scanner scanner, String prompt) {
            System.out.print(prompt);
            return scanner.nextDouble();
        }

        private int getIntInput(Scanner scanner, String prompt) {
            System.out.print(prompt);
            return scanner.nextInt();
        }
        
        // Using existing attributes to use as the scanner for creating an employee

        public void findEmployee() {
            // Source code for finding an employee will go here
        }

        public void updateEmployee() {
            // Source code for updating an employee will go here
        }

        public void deleteEmployee() {
            // Source code for deleting an employee will go here
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
        payrollSystem.createEmployee();
    }
}
