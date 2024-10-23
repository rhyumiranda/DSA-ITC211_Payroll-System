
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
      double withholdingTax = 0;

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

        double convertedTardinessTime = convertTimeToHour(tardinessTime);
        double convertedUnderTimeDeduction = convertTimeToHour(undertimeTime);

        absencesDeduction = absencesCount * (hourlyRate * 8);
        tardinessDeduction = convertedTardinessTime * hourlyRate;
        undertimeDeduction = convertedUnderTimeDeduction * hourlyRate;

        grossPay = monthlyHoursToRender * hourlyRate;
        netPay = grossPay - absencesDeduction - tardinessDeduction - undertimeDeduction;

        withholdingTax = grossPay * 0.25;

        netPay = netPay - withholdingTax;

        if (grossPay >= 70000){
          employeeRank = "CEO";
        } else if (grossPay >= 50000) {
          employeeRank = "Manager";
        } else if (grossPay >= 20000) {
          employeeRank = "Supervisor";
        } else if (grossPay >= 10000) {
          employeeRank = "Rank and File";
        }

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
      System.out.print("Enter the employee ID to search: ");
      int idToSearch = input.nextInt();
      input.nextLine();

      Employee foundEmployee = employees.stream()
              .filter(emp -> emp.employeeID == idToSearch)
              .findFirst()
              .orElse(null);

      if (foundEmployee != null) {
        System.out.println("Employee found:");
        System.out.println(foundEmployee);
      } else {
        System.out.println("Employee with ID " + idToSearch + " not found.");
      }
    }

    public void updateEmployee() {
      System.out.print("Enter the employee ID to update: ");
      int idToUpdate = input.nextInt();
      input.nextLine();  // Consume leftover newline

      Employee employeeToUpdate = employees.stream()
              .filter(emp -> emp.employeeID == idToUpdate)
              .findFirst()
              .orElse(null);

      if (employeeToUpdate != null) {
        System.out.println("Updating employee with ID " + idToUpdate + ".");

        System.out.print("New Employee Name (Leave blank to keep current: "
                + employeeToUpdate.employeeName + "): ");
        String newName = input.nextLine();
        if (!newName.isBlank()) employeeToUpdate.employeeName = newName;

        System.out.print("New Employee Department (Current: "
                + employeeToUpdate.employeeDepartment + "): ");
        String newDepartment = input.nextLine();
        if (!newDepartment.isBlank()) employeeToUpdate.employeeDepartment = newDepartment;

        System.out.print("New Monthly Hours to Render (Current: "
                + employeeToUpdate.monthlyHoursToRender + "): ");
        String hoursInput = input.nextLine();
        if (!hoursInput.isBlank())
          employeeToUpdate.monthlyHoursToRender = Double.parseDouble(hoursInput);

        System.out.print("New Hourly Rate (Current: " + employeeToUpdate.hourlyRate + "): ");
        String rateInput = input.nextLine();
        if (!rateInput.isBlank())
          employeeToUpdate.hourlyRate = Double.parseDouble(rateInput);

        System.out.print("New Tardiness Time (Current: "
                + employeeToUpdate.tardinessTime + "): ");
        String newTardinessTime = input.nextLine();
        if (!newTardinessTime.isBlank())
          employeeToUpdate.tardinessTime = newTardinessTime;

        System.out.print("New Undertime Time (Current: "
                + employeeToUpdate.undertimeTime + "): ");
        String newUndertimeTime = input.nextLine();
        if (!newUndertimeTime.isBlank())
          employeeToUpdate.undertimeTime = newUndertimeTime;

        System.out.print("New Absences Count (Current: "
                + employeeToUpdate.absencesCount + "): ");
        String absencesInput = input.nextLine();
        if (!absencesInput.isBlank())
          employeeToUpdate.absencesCount = Integer.parseInt(absencesInput);

        double convertedTardinessTime = convertTimeToHour(newTardinessTime);
        double convertedUndertimeTime = convertTimeToHour(newUndertimeTime);

        double absencesDeduction = Double.parseDouble(absencesInput) * Double.parseDouble(rateInput);
        double tardinessDeduction = convertedTardinessTime * Double.parseDouble(rateInput);
        double undertimeDeduction = convertedUndertimeTime * Double.parseDouble(rateInput);

        System.out.println("Absences Deduction: " + absencesDeduction);
        employeeToUpdate.absencesCountDeduction = absencesDeduction;

        System.out.println("Tardiness Deduction: " + tardinessDeduction);
        employeeToUpdate.tardinessDeduction = tardinessDeduction;

        System.out.println("Undertime Deduction: " + undertimeDeduction);
        employeeToUpdate.undertimeDeduction = undertimeDeduction;

        double grossPay = Double.parseDouble(rateInput) * Double.parseDouble(hoursInput);
        double withholdingTax = grossPay * 0.25;
        double netPay = grossPay - absencesDeduction - tardinessDeduction - undertimeDeduction - withholdingTax;

        System.out.println("Gross Pay: " + grossPay);
        employeeToUpdate.grossPay = grossPay;

        System.out.println("Net Pay: " + netPay);
        employeeToUpdate.netPay = netPay;

        System.out.println("Employee details updated successfully.");
      } else {
        System.out.println("Employee with ID " + idToUpdate + " not found.");
      }
    }

    public void deleteEmployee() {
      System.out.print("Enter the ID of the employee to delete: ");
      int idToDelete = input.nextInt();
      input.nextLine();

      boolean found = employees.removeIf(emp -> emp.employeeID == idToDelete);
      if (found) {
        System.out.println("Employee with ID " + idToDelete + " has been deleted.");
      } else {
        System.out.println("Employee with ID " + idToDelete + " not found.");
      }
    }

    public void displayMenu() {

      boolean isActive = false;

      while(!isActive) {
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

    public static double convertTimeToHour(String time){
      String[] splitTime = time.split(":");

      int hour = Integer.parseInt(splitTime[0]);
      int minute = Integer.parseInt(splitTime[1]);
      int second= Integer.parseInt(splitTime[2]);

      double HOUR_CONSTANT_FOR_MINUTES = 60;
      double HOUR_CONSTANT_FOR_SECONDS = 3600;
      double convertedMinutes = minute / HOUR_CONSTANT_FOR_MINUTES;
      double convertedSeconds = second / HOUR_CONSTANT_FOR_SECONDS;
      return hour + convertedMinutes + convertedSeconds;
    }
  }

  public static void main(String[] args){
    PayrollSystem payrollSystem = new PayrollSystem();
    payrollSystem.displayMenu();
  }
}




