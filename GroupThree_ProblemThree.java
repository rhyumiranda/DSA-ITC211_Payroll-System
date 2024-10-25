import java.util.*;

public class GroupThree_ProblemThree {
  public static class Employee {
    private String employeeName;
    private int employeeID;
    private String employeeDepartment;
    private String employeeRank;
    private double monthlyHoursToRender;
    private double hourlyRate;
    private double overtimeHours;
    private double overtimeRate;
    private String tardinessTime;
    private String undertimeTime;
    private int absencesCount;
    private double tardinessDeduction;
    private double undertimeDeduction;
    private double absencesDeduction;
    private double withholdingTax;
    private double totalDeductions;
    private double regularPay;
    private double overtimePay;
    private double grossPay;
    private double netPay;

    public Employee(
      String employeeName,
      int employeeID,
      String employeeDepartment,
      String employeeRank,
      double monthlyHoursToRender,
      double hourlyRate,
      double overtimeHours,
      double overtimeRate,
      String tardinessTime,
      String undertimeTime,
      int absencesCount,
      double tardinessDeduction,
      double undertimeDeduction,
      double absencesDeduction,
      double withholdingTax,
      double grossPay,
      double netPay) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.employeeDepartment = employeeDepartment;
        this.employeeRank = employeeRank;
        this.monthlyHoursToRender = monthlyHoursToRender;
        this.hourlyRate = hourlyRate;
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
        this.tardinessTime = tardinessTime;
        this.undertimeTime = undertimeTime;
        this.absencesCount = absencesCount;
        this.tardinessDeduction = tardinessDeduction;
        this.undertimeDeduction = undertimeDeduction;
        this.absencesDeduction = absencesDeduction;
        this.withholdingTax = withholdingTax;
        this.totalDeductions = absencesDeduction + tardinessDeduction + 
        undertimeDeduction + withholdingTax;
        this.regularPay = monthlyHoursToRender * hourlyRate;
        this.overtimePay = overtimeHours * overtimeRate;
        this.grossPay = regularPay + overtimePay;
        this.netPay = grossPay - totalDeductions;
    }
    
    @Override
    public String toString() {
      // %d for int
      return String.format(
        "Employee Details:\n" + 
        "  Name: %s\n" +
        "  Employee ID No.: %d\n" +
        "  Department: %s\n" +
        "  Rank: %s\n" +
        "  Monthly Hours to Render: %.2f hours\n" +
        "  Hourly Rate: P %.2f\n" +
        "  Overtime Hours: %.2f hours\n" +
        "  Overtime Rate: P %.2f\n" +
        "  Tardiness Time: %s\n" +
        "  Undertime Time: %s\n" +
        "  Absences Count: %d\n" +
        "  Tardiness Deduction: P %.2f\n" +
        "  Undertime Deduction: P %.2f\n" +
        "  Absences Deduction: P %.2f\n" +
        "  Withholding Tax: P %.2f\n" +
        "  Total Deductions: P %.2f\n" +
        "  Regular Pay: P %.2f\n" +
        "  Overtime Pay: P %.2f\n" +
        "  Gross Pay: P %.2f\n" +
        "  Net Pay: P %.2f\n",
        employeeName,
        employeeID,
        employeeDepartment,
        employeeRank,
        monthlyHoursToRender,
        hourlyRate,
        overtimeHours,
        overtimeRate,
        tardinessTime,
        undertimeTime,
        absencesCount,
        tardinessDeduction,
        undertimeDeduction,
        absencesDeduction,
        withholdingTax,
        totalDeductions,
        regularPay,
        overtimePay,
        grossPay,
        netPay);
    } 
  }

  public static class PayrollSystem {
    LinkedList<Employee> employees = new LinkedList<>();
    private Scanner input = new Scanner(System.in);
    private int employeeCount = 1;

    public void createEmployee() {
      System.out.print("Enter number of employees to create: ");
      int numberOfEmployees = input.nextInt();
      input.nextLine();

      for (int i = 0; i < numberOfEmployees; i++) {
        System.out.println("---------- Employee " + employeeCount + " ----------");
        System.out.print("Employee Name (Ln, Fn Mi): ");
        String employeeName = input.nextLine();
        System.out.print("Employee Department: ");
        String employeeDepartment = input.nextLine();
        System.out.print("Hours to render in a month: ");
        double monthlyHoursToRender = input.nextDouble();
        System.out.print("Hourly rate: ");
        double hourlyRate = input.nextDouble();
        System.out.print("Overtime hours: ");
        double overtimeHours = input.nextDouble();
        System.out.print("Overtime rate: ");
        double overtimeRate = input.nextDouble();
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

        double tardinessDeduction = convertTimeToHour(tardinessTime) * hourlyRate;
        double undertimeDeduction = convertTimeToHour(undertimeTime) * hourlyRate;
        double absencesDeduction = absencesCount * (hourlyRate * 8);
        double regularPay = monthlyHoursToRender * hourlyRate;
        double overtimePay = overtimeHours * overtimeRate;
        double grossPay = regularPay + overtimePay;
        double withholdingTax = grossPay * 0.25;
        double totalDeductions = absencesDeduction + tardinessDeduction + undertimeDeduction + withholdingTax;
        double netPay = grossPay - totalDeductions;

        String employeeRank = determineRank(grossPay);
        
        Employee employee = new Employee(
          employeeName,
          employeeCount++,
          employeeDepartment,
          employeeRank,
          monthlyHoursToRender,
          hourlyRate,
          overtimeHours,
          overtimeRate,
          tardinessTime,
          undertimeTime,
          absencesCount,
          tardinessDeduction,
          undertimeDeduction,
          absencesDeduction,
          withholdingTax,
          grossPay,
          netPay);

          employees.add(employee);
          System.out.println("Employee " + employeeName + " has been added.");
          System.out.println("========== ========== ==========");
      }
    }
    
    private String determineRank(double grossPay) {
      if (grossPay >= 70000) return "CEO";
      if (grossPay >= 50000) return "Manager";
      if (grossPay >= 20000) return "Supervisor";
      if (grossPay >= 10000) return "Rank and File";
      return "Unknown"; // Fallback
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
      if (employees.isEmpty()) {
        System.out.println("o===o===o===o===o===o===o===o===o===o");
        System.out.println("      No employees to update.");
        System.out.println("o===o===o===o===o===o===o===o===o===o");
        return;
      }
      System.out.print("Enter Employee ID to update: ");
      int idToUpdate = input.nextInt();
      input.nextLine(); // Consume leftover newline
    
      Employee employeeToUpdate = employees.stream()
          .filter(emp -> emp.employeeID == idToUpdate)
          .findFirst()
          .orElse(null);

      if (employeeToUpdate != null) {
        System.out.println("Updating employee with ID " + idToUpdate + ".");
        
        System.out.print("New Employee Name (Leave blank to keep current: " + employeeToUpdate.employeeName + "): ");
        String newName = input.nextLine();
        if (!newName.isBlank()) employeeToUpdate.employeeName = newName;
        
        System.out.print("New Employee Department (Current: " + employeeToUpdate.employeeDepartment + "): ");
        String newDepartment = input.nextLine();
        if (!newDepartment.isBlank()) employeeToUpdate.employeeDepartment = newDepartment;
        
        System.out.print("New Monthly Hours to Render (Current: " + employeeToUpdate.monthlyHoursToRender + " hours): ");
        String hoursInput = input.nextLine();
        if (!hoursInput.isBlank()) {
          try {
            employeeToUpdate.monthlyHoursToRender = Double.parseDouble(hoursInput);
          } catch (NumberFormatException e) {
            System.out.println("Invalid input for monthly hours. Keeping current value.");
          }
        }
        
        System.out.print("New Hourly Rate (Current: P " + employeeToUpdate.hourlyRate + "): ");
        String rateInput = input.nextLine();
        if (!rateInput.isBlank()) {
          try {
            employeeToUpdate.hourlyRate = Double.parseDouble(rateInput);
          } catch (NumberFormatException e) {
            System.out.println("Invalid input for hourly rate. Keeping current value.");
          }
        }
        
        System.out.print("New Overtime Hours (Current: " + employeeToUpdate.overtimeHours + " hours): ");
        String overtimeInput = input.nextLine();
        if (!overtimeInput.isBlank()) {
          try {
            employeeToUpdate.overtimeHours = Double.parseDouble(overtimeInput);
          } catch (NumberFormatException e) {
            System.out.println("Invalid input for overtime hours. Keeping current value.");
          }
        }
        
        System.out.print("New Overtime Rate (Current: P " + employeeToUpdate.overtimeRate + "): ");
        String overtimerateInput = input.nextLine();
        if (!overtimerateInput.isBlank()) {
          try {
            employeeToUpdate.overtimeRate = Double.parseDouble(overtimerateInput);
          } catch (NumberFormatException e) {
            System.out.println("Invalid input for overtime rate. Keeping current value.");
          }
        }
        
        System.out.print("New Tardiness Time (Current: " + employeeToUpdate.tardinessTime + "): ");
        String newTardinessTime = input.nextLine();
        if (!newTardinessTime.isBlank()) employeeToUpdate.tardinessTime = newTardinessTime;
        
        System.out.print("New Undertime Time (Current: " + employeeToUpdate.undertimeTime + "): ");
        String newUndertimeTime = input.nextLine();
        if (!newUndertimeTime.isBlank()) employeeToUpdate.undertimeTime = newUndertimeTime;
        
        System.out.print("New Absences Count (Current: " + employeeToUpdate.absencesCount + "): ");
        String absencesInput = input.nextLine();
        if (!absencesInput.isBlank()) {
          try {
            employeeToUpdate.absencesCount = Integer.parseInt(absencesInput);
          } catch (NumberFormatException e) {
            System.out.println("Invalid input for absences count. Keeping current value.");
          }
        }

        double tardinessDeduction = convertTimeToHour(employeeToUpdate.tardinessTime) * employeeToUpdate.hourlyRate;
        double undertimeDeduction = convertTimeToHour(employeeToUpdate.undertimeTime) * employeeToUpdate.hourlyRate;
        double absencesDeduction = employeeToUpdate.absencesCount * (employeeToUpdate.hourlyRate * 8);
        double regularPay = employeeToUpdate.monthlyHoursToRender * employeeToUpdate.hourlyRate;
        double overtimePay = employeeToUpdate.overtimeHours * employeeToUpdate.overtimeRate;
        double grossPay = regularPay + overtimePay;
        double withholdingTax = grossPay * 0.25;
        double totalDeductions = absencesDeduction + tardinessDeduction + undertimeDeduction + withholdingTax;
        double netPay = grossPay - totalDeductions;

        employeeToUpdate.tardinessDeduction = tardinessDeduction;
        employeeToUpdate.undertimeDeduction = undertimeDeduction;
        employeeToUpdate.absencesDeduction = absencesDeduction;
        employeeToUpdate.withholdingTax = withholdingTax;
        employeeToUpdate.totalDeductions = totalDeductions;
        employeeToUpdate.regularPay = regularPay;
        employeeToUpdate.overtimePay = overtimePay;
        employeeToUpdate.grossPay = grossPay;
        employeeToUpdate.netPay = netPay;

        String newRank = determineRank(grossPay);
        employeeToUpdate.employeeRank = newRank;
        System.out.println("New Rank: " + newRank);

        System.out.printf("Tardiness Deduction: %.2f%n", tardinessDeduction);
        System.out.printf("Undertime Deduction: %.2f%n", undertimeDeduction);
        System.out.printf("Absences Deduction: %.2f%n", absencesDeduction);
        System.out.printf("Withholding Tax: %.2f%n", withholdingTax);
        System.out.printf("Total Deductions: %.2f%n", totalDeductions);
        System.out.printf("Regular Pay: %.2f%n", employeeToUpdate.regularPay);
        System.out.printf("Overtime Pay: %.2f%n", employeeToUpdate.overtimePay);
        System.out.printf("Gross Pay: %.2f%n", employeeToUpdate.grossPay);
        System.out.printf("Net Pay: %.2f%n", employeeToUpdate.netPay);
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

    public static double convertTimeToHour(String time) {
      if (!isValidTimeFormat(time)) {
          System.out.println("Invalid time format. Please use HH:MM:SS.");
          return 0;
      }

      String[] splitTime = time.split(":");
      int hour = Integer.parseInt(splitTime[0]);
      int minute = Integer.parseInt(splitTime[1]);
      int second = Integer.parseInt(splitTime[2]);

      return hour + minute / 60.0 + second / 3600.0;
    }

    private static boolean isValidTimeFormat(String time) {
      String regex = "^([01]?\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
      return time.matches(regex);
    }
  }

  public static void main(String[] args){
    PayrollSystem payrollSystem = new PayrollSystem();
    payrollSystem.displayMenu();
  }
}
 
