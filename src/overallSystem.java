import java.util.*;

public class OverallSystem {

    private Scanner sc = new Scanner(System.in);
    private DatabaseOperations dbOps = new DatabaseOperations();
    private ReportGenerator report = new ReportGenerator(dbOps);

    public void searchEmployee() {
        System.out.print("Enter name, SSN, or Employee ID: ");
        String keyword = sc.nextLine();

        Employee emp = dbOps.getEmployeeByKeyword(keyword);

        if (emp == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("=============================================================");
        System.out.println("                    EMPLOYEE SEARCH RESULT");
        System.out.println("=============================================================");
        System.out.println("Employee ID: " + emp.getEmpID());
        System.out.println("Name:        " + emp.getName());
        System.out.println("SSN:         " + emp.getSSN());
        System.out.println("Job Title:   " + emp.getJobTitle());
        System.out.println("Division:    " + emp.getDivision());
        System.out.println("Salary:      $" + String.format("%.2f", emp.getSalary()));
        System.out.println("=============================================================\n");
    }

    public void generateFullEmployeePayReport(String keyword) {
        report.generateFullEmployeePayReport(keyword);
    }

    public void generateMonthlyPayrollReport() {
        report.generateJobTitleSalaryReport();
        report.generateDivisionSalaryReport();
    }
}
