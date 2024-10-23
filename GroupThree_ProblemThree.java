
import java.util.*;

public class GroupThree_ProblemThree {
  public static class Employee{
    private String employeeName;
    private int employeeID;
    private String employeeDepartment;
    private String employeeRank;

    private double monthlyHoursToRender;
    private double hourlyRate;
    private String tardinessTime;
    private String undertimeTime;
    private int absencesCount;

    private double absencesCountDeduction;
    private double tardinessDeduction;
    private double undertimeDeduction;

    private double grossPay;
    private double netPay;

    public Employee(
            String employeeName,
            int employeeID,
            String employeeDepartment,
            String employeeRank,
            double monthlyHoursToRender,
            double hourlyRate,
            String tardinessTime,
            String undertimeTime,
            int absencesCount,
            double absencesCountDeduction,
            double tardinessDeduction,
            double undertimeDeduction,
            double grossPay,
            double netPay){
      this.employeeName = employeeName;
      this.employeeID = employeeID;
      this.employeeDepartment = employeeDepartment;
      this.employeeRank = employeeRank;
      this.monthlyHoursToRender = monthlyHoursToRender;
      this.hourlyRate = hourlyRate;
      this.tardinessTime = tardinessTime;
      this.undertimeTime = undertimeTime;
      this.absencesCount = absencesCount;
      this.absencesCountDeduction = absencesCountDeduction;
      this.tardinessDeduction = tardinessDeduction;
      this.undertimeDeduction = undertimeDeduction;
      this.grossPay = grossPay;
      this.netPay = netPay;
    }

    @Override
    public String toString() {
      // %d for int
      return String.format(
              "Employee Details:\n  Name: %%s\n  Employee ID No.: %%d\n  Department: %%s\n  Rank: %%s\n  Monthly Hours to Render: %%.2f hours\n  Hourly Rate: P %%.2f\n  Tardiness Time: %%s\n  Undertime Time: %%s\n  Absences: %%d\n  Absences Deduction: P %%.2f\n  Tardiness Deduction: P %%.2f\n  Undertime Deduction: P %%.2f\n  Gross Pay: P %%.2f\n  Net Pay: P %%.2f\n".formatted(),
              employeeName,
              employeeID,
              employeeDepartment,
              employeeRank,
              monthlyHoursToRender,
              hourlyRate,
              tardinessTime,
              undertimeTime,
              absencesCount,
              absencesCountDeduction,
              tardinessDeduction,
              undertimeDeduction,
              grossPay,
              netPay
      );
    }
  }

  public static class PayrollSystem{
    LinkedList<Employee> employees = new LinkedList<>();
    private Scanner input = new Scanner(System.in);
    private int EmployeeCount = 1;

    public void createEmployee() {
      System.out.print("Enter number of employees to create: ");
      int numberOfEmployees = input.nextInt();

      double absencesDeduction = 0;
      double tardinessDeduction = 0;
      double undertimeDeduction = 0;
      double grossPay = 0;
      double netPay = 0;

      input.nextLine();

      for (int i = 0; i < numberOfEmployees; i++) {
        System.out.println("---------- Employee " + EmployeeCount + " ----------");
        System.out.print("Employee Name (Ln, Fn Mi): ");
        String employeeName = input.nextLine();
        System.out.print("Employee Department: ");
        String employeeDepartment = input.nextLine();
        System.out.println("Ranks (CEO), (Manager), (Supervisor), (Rank & File)");
        System.out.print("Employee Rank: ");
        String employeeRank = input.nextLine();
        System.out.print("Hours to render in a month: ");
        double monthlyHoursToRender = input.nextDouble();
        System.out.print("Hourly rate: ");
        double hourlyRate = input.nextDouble();
        input.nextLine();

        System.out.println("Please enter the tardiness or late duration in the format");
        System.out.print("(HH:MM:SS): ");
        String tardinessTime = input.nextLine();

        System.out.println("Please enter undertime duration in the format");
        System.out.print("(HH:MM:SS): ");
        String undertimeTime = input.nextLine();

        System.out.print("Please enter absences count: ");
        int absencesCount = input.nextInt();
        input.nextLine();

        String[] splitTardinessTime = tardinessTime.split(":");
        String[] splitUnderTime = undertimeTime.split(":");

        int hourTardiness = Integer.parseInt(splitTardinessTime[0]);
        int minutesTardiness = Integer.parseInt(splitTardinessTime[1]);
        int secondsTardiness = Integer.parseInt(splitTardinessTime[2]);

        int hourUnderTime = Integer.parseInt(splitUnderTime[0]);
        int minuteUnderTime = Integer.parseInt(splitUnderTime[1]);
        int secondsUnderTime = Integer.parseInt(splitUnderTime[2]);

        double convertedTardinessTime = convertTimeToHour(hourTardiness, minutesTardiness, secondsTardiness);
        double convertedUnderTimeDeduction = convertTimeToHour(hourUnderTime, minuteUnderTime, secondsUnderTime);

        absencesDeduction = absencesCount * (hourlyRate * 8);
        tardinessDeduction = convertedTardinessTime * hourlyRate;
        undertimeDeduction = convertedUnderTimeDeduction * hourlyRate;

        grossPay = monthlyHoursToRender * hourlyRate;
        netPay = grossPay - absencesDeduction - tardinessDeduction - undertimeDeduction;

        Employee employee = new Employee(
                employeeName,
                EmployeeCount++,
                employeeDepartment,
                employeeRank,
                monthlyHoursToRender,
                hourlyRate,
                tardinessTime,
                undertimeTime,
                absencesCount,
                absencesDeduction,
                tardinessDeduction,
                undertimeDeduction,
                grossPay,
                netPay
        );

        employees.add(employee);
        System.out.println(convertedUnderTimeDeduction);
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
