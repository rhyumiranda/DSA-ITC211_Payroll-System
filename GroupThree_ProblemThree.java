
import java.util.*;

public class GroupThree_ProblemThree {
  public static class Employee{
    private String employeeName;
    private double employeeID;
    private String employeeDepartment;
    private String employeeRank;

    private double monthlyHoursToRender;
    private double hourlyRate;
    private String tardinessDeductionTime;
    private String undertimeDeductionTime;

    private int absencesCountDeduction;
    private double tardinessDeduction;
    private double undertimeDeduction;

    public Employee(
            String employeeName,
            int employeeID,
            String employeeDepartment,
            String employeeRank,
            double monthlyHoursToRender,
            double hourlyRate,
            String tardinessDeductionTime,
            String undertimeDeductionTime,
            int absencesCountDeduction,
            double tardinessDeduction,
            double undertimeDeduction){
      this.employeeName = employeeName;
      this.employeeID = employeeID;
      this.employeeDepartment = employeeDepartment;
      this.employeeRank = employeeRank;
      this.monthlyHoursToRender = monthlyHoursToRender;
      this.hourlyRate = hourlyRate;
      this.tardinessDeductionTime = tardinessDeductionTime;
      this.undertimeDeductionTime = undertimeDeductionTime;
      this.absencesCountDeduction = absencesCountDeduction;
      this.tardinessDeduction = tardinessDeduction;
      this.undertimeDeduction = undertimeDeduction;
    }
  
  
  // Format to display the employee
    @Override
    public String toString() {
        return String.format("Employee Details:\n" +
                        "  Name: %s\n" +
                        "  Employee ID No. : %.0f\n" + // Added Employee ID on Display so it shouldn't be confusing when counting how many employees
                        "  Department: %s\n" +
                        "  Rank: %s\n" +
                        "  Time: %.2fh\n" +  
                        "  Tardiness Deduction Time: %s\n" +
                        "  Undertime Deduction Time: %s\n" +
                        "  Absences: %d\n",
                employeeName, employeeID, employeeDepartment, employeeRank,
                monthlyHoursToRender, 
                tardinessDeductionTime, 
                undertimeDeductionTime,
                absencesCountDeduction);
    
        }
    }

  public static class PayrollSystem{
        LinkedList<Employee> employees = new LinkedList<>();
        private Scanner input = new Scanner(System.in);
        private int EmployeeCount = 1; 

        public void createEmployee() {
            System.out.print("Enter number of employees to create: ");
            int numberOfEmployees = input.nextInt();
            input.nextLine(); 

            for (int i = 0; i < numberOfEmployees; i++) {
                System.out.println("---------- Employee " + EmployeeCount + " ----------");
                System.out.print("Employee Name (Ln, Fn Mi): ");
                String employeeName = input.nextLine();
                System.out.print("Employee Department: ");
                String employeeDepartment = input.nextLine();
                System.out.print("Employee Rank: ");
                String employeeRank = input.nextLine();
                System.out.print("Hours to render in a month: ");
                double monthlyHoursToRender = input.nextDouble();
                System.out.print("Hourly rate: ");
                double hourlyRate = input.nextDouble();
                input.nextLine(); 

                System.out.println("Please enter the tardiness or late duration in the format");
                System.out.print("(HH:MM:SS): ");
                String tardinessDeductionTime = input.nextLine();
                
                System.out.println("Please enter undertime duration in the format");
                System.out.print("(HH:MM:SS): ");
                String undertimeDeductionTime = input.nextLine();
                
                System.out.print("Please enter absences count: ");
                int absencesCountDeduction = input.nextInt();
                input.nextLine(); 

            Employee employee = new Employee(
                    employeeName,
                    EmployeeCount++, 
                    employeeDepartment,
                    employeeRank,
                    monthlyHoursToRender,
                    hourlyRate,
                    tardinessDeductionTime,
                    undertimeDeductionTime,
                    absencesCountDeduction,
                    0.0, // Placeholder for tardinessDeduction
                    0.0  // Placeholder for undertimeDeduction
            );

                employees.add(employee);
                System.out.println("Employee " + employeeName + " has been added.");
                System.out.println("========== ========== ==========");
            }
        }

        public void displayEmployee() {
            if (employees.isEmpty()) {
                System.out.println("o===o===o===o===o===o===o===o===o===o");
                System.out.println("      No employees to display.");
                System.out.println("o===o===o===o===o===o===o===o===o===o");
                return;
            }
            System.out.println("o===o===o===o===o===o===o===o===o===o");
            System.out.println("          List of Employees:");
            System.out.println("o===o===o===o===o===o===o===o===o===o");
            for (Employee emp : employees) {
                System.out.println(emp);
            }
            System.out.println("o===o===o===o===o===o===o===o===o===o");
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

    public void displayMenu(){

      boolean isActive = false;

      while(!isActive){
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

    public static double convertTimeToHour(int hours, int minutes, int seconds){
      double HOUR_CONSTANT_FOR_MINUTES = 60;
      double HOUR_CONSTANT_FOR_SECONDS = 3600;
      double convertedMinutes = minutes / HOUR_CONSTANT_FOR_MINUTES;
      double convertedSeconds = seconds / HOUR_CONSTANT_FOR_SECONDS;
      return hours + convertedMinutes + convertedSeconds;
    }
  }

  public static void main(String[] args){
    PayrollSystem payrollSystem = new PayrollSystem();
    payrollSystem.displayMenu();
  }
}
