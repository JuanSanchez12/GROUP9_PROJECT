import java.util.*;

public class ReportGenerator {

    private DatabaseOperations dbOps;
    private Payroll payroll;

    public ReportGenerator(DatabaseOperations dbOps) {
        this.dbOps = dbOps;
        this.payroll = new Payroll(dbOps);
    }

    public void generateFullEmployeePayReport(String keyword) {

        //Looks up employee
        Employee emp = dbOps.getEmployeeByKeyword(keyword);

        if (emp == null) {
            System.out.println("=============================================================");
            System.out.println("                     EMPLOYEE NOT FOUND");
            System.out.println("=============================================================\n");
            return;
        }

        //Calculations handled by Payroll class
        double annualSalary = emp.getSalary();
        double monthlyGross = payroll.getMonthlyGross(emp);
        double tax = payroll.calculateMonthlyTax(emp);
        double other = payroll.calculateOtherDeductions(emp);
        double monthlyNet = payroll.getMonthlyNet(emp);

        System.out.println("=============================================================");
        System.out.println("                 FULL EMPLOYEE PAY REPORT");
        System.out.println("=============================================================");
        System.out.println("Employee ID:   " + emp.getEmpID());
        System.out.println("Name:          " + emp.getName());
        System.out.println("SSN:           " + emp.getSSN());
        System.out.println("Job Title:     " + emp.getJobTitle());
        System.out.println("Division:      " + emp.getDivision());
        System.out.println("-------------------------------------------------------------");
        System.out.printf("Annual Salary: $%.2f%n", annualSalary);
        System.out.printf("Gross Monthly: $%.2f%n", monthlyGross);
        System.out.printf("Monthly Tax:   $%.2f%n", tax);
        System.out.printf("Deductions:    $%.2f%n", other);
        System.out.printf("Net Monthly:   $%.2f%n", monthlyNet);
        System.out.println("=============================================================\n");
    }

    public void generateJobTitleSalaryReport() {

        //All employees
        List<Employee> employees = dbOps.getAllEmployees();

        //Group by job title
        Map<String, List<Employee>> grouped = new HashMap<>();

        for (Employee emp : employees) {
            grouped.computeIfAbsent(emp.getJobTitle(), k -> new ArrayList<>()).add(emp);
        }

        System.out.println("=============================================================");
        System.out.println("             MONTHLY PAY REPORT BY JOB TITLE");
        System.out.println("=============================================================");

        for (String title : grouped.keySet()) {

            List<Employee> list = grouped.get(title);

            //Average monthly salary handled by Payroll
            double avg = payroll.getAverageMonthlySalary(list);

            System.out.printf("\nJob Title: %s%n", title);
            System.out.printf("Average Monthly Pay: $%.2f%n", avg);
            System.out.println("Employees:");

            for (Employee emp : list) {
                System.out.printf("   %s (ID: %d)%n", emp.getName(), emp.getEmpID());
            }
        }

        System.out.println("=============================================================\n");
    }

    public void generateDivisionSalaryReport() {

        //All employees
        List<Employee> employees = dbOps.getAllEmployees();

        //Group by division
        Map<String, List<Employee>> grouped = new HashMap<>();

        for (Employee emp : employees) {
            grouped.computeIfAbsent(emp.getDivision(), k -> new ArrayList<>()).add(emp);
        }

        System.out.println("=============================================================");
        System.out.println("             MONTHLY PAY REPORT BY DIVISION");
        System.out.println("=============================================================");

        for (String division : grouped.keySet()) {

            List<Employee> list = grouped.get(division);

            //Average monthly salary computed by Payroll
            double avg = payroll.getAverageMonthlySalary(list);

            System.out.printf("\nDivision: %s%n", division);
            System.out.printf("Average Monthly Pay: $%.2f%n", avg);
            System.out.println("Employees:");

            for (Employee emp : list) {
                System.out.printf("   %s (ID: %d)%n", emp.getName(), emp.getEmpID());
            }
        }

        System.out.println("=============================================================\n");
    }
}
