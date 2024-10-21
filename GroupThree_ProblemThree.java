import java.util.*;

public class GroupThree_ProblemThree {
  public static class Employee{
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
            int employeeID,
            String employeeDepartment,
            String employeeRank,
            int hours,
            int minutes,
            int seconds,
            int absencesCountDeduction,
            double tardinessDeduction,
            double undertimeDeduction){
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

    public static double convertTimeToHour(int hours, int minutes, int seconds){
      double HOUR_CONSTANT_FOR_MINUTES = 60;
      double HOUR_CONSTANT_FOR_SECONDS = 3600;
      double convertedMinutes = minutes / HOUR_CONSTANT_FOR_MINUTES;
      double convertedSeconds = seconds / HOUR_CONSTANT_FOR_SECONDS;
      return hours + convertedMinutes + convertedSeconds;
    }
  }

  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    Employee person1 = new Employee("Omar", 1, "ICS", "CEO", 69, 68, 67, 2, 1.2, 1.5);
  }
}
