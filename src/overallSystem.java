import java.util.*;

public class OverallSystem {

    private Scanner sc = new Scanner(System.in);

    private DatabaseOperations dbOps = new DatabaseOperations();

    private ReportGenerator report = new ReportGenerator(dbOps);

    public void searchEmployee() {

        System.out.print("Enter name, SSN, or Employee ID: ");
        String keyword = sc.nextLine();

        //Looks up an employee using thier name, SSN, or ID
        Employee emp = dbOps.getEmployeeByKeyword(keyword);

        if (emp == null) {
            System.out.println("=============================================================");
            System.out.println("                     EMPLOYEE NOT FOUND");
            System.out.println("=============================================================\n");
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

    //Adds a new employee to the database
    public void addEmployee() {

        System.out.print("Enter Employee ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter SSN: ");
        String ssn = sc.nextLine();

        System.out.print("Enter Job Title: ");
        String job = sc.nextLine();

        System.out.print("Enter Division: ");
        String division = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = Double.parseDouble(sc.nextLine());

        //Creates an employee object with the user’s input
        Employee emp = new Employee(id, name, ssn, job, division, salary);

        System.out.println("=============================================================");

        //Adds the employee to the database
        if (dbOps.addEmployee(emp)) {
            System.out.println("                 EMPLOYEE ADDED SUCCESSFULLY");
            System.out.println("-------------------------------------------------------------");
            System.out.println("Employee ID: " + emp.getEmpID());
            System.out.println("Name:        " + emp.getName());
            System.out.println("SSN:         " + emp.getSSN());
            System.out.println("Job Title:   " + emp.getJobTitle());
            System.out.println("Division:    " + emp.getDivision());
            System.out.println("Salary:      $" + String.format("%.2f", emp.getSalary()));
        } else {
            System.out.println("                   FAILED TO ADD EMPLOYEE");
        }

        System.out.println("=============================================================\n");
    }

    public void removeEmployee() {

        System.out.print("Enter Employee ID to remove: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.println("=============================================================");

        //Deletes an employee by their ID
        if (dbOps.removeEmployee(id)) {
            System.out.println("               EMPLOYEE REMOVED SUCCESSFULLY");
            System.out.println("-------------------------------------------------------------");
            System.out.println("Employee Removed: ID " + id);
        } else {
            System.out.println("                 EMPLOYEE NOT FOUND / FAILED");
        }

        System.out.println("=============================================================\n");
    }

    public void updateEmployee() {

        System.out.print("Enter Employee ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        //Finds the employee so we can update their fields
        Employee emp = dbOps.getEmployeeByKeyword(String.valueOf(id));

        if (emp == null) {
            System.out.println("=============================================================");
            System.out.println("                     EMPLOYEE NOT FOUND");
            System.out.println("=============================================================\n");
            return;
        }

        System.out.print("Enter new name (" + emp.getName() + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) emp.setName(name);

        System.out.print("Enter new SSN (" + emp.getSSN() + "): ");
        String ssn = sc.nextLine();
        if (!ssn.isEmpty()) emp.setSSN(ssn);

        System.out.print("Enter new Job Title (" + emp.getJobTitle() + "): ");
        String job = sc.nextLine();
        if (!job.isEmpty()) emp.setJobTitle(job);

        System.out.print("Enter new Division (" + emp.getDivision() + "): ");
        String division = sc.nextLine();
        if (!division.isEmpty()) emp.setDivision(division);

        System.out.print("Enter new Salary (" + emp.getSalary() + "): ");
        String salInput = sc.nextLine();
        if (!salInput.isEmpty()) emp.setSalary(Double.parseDouble(salInput));

        System.out.println("=============================================================");

        //Outputs updated employee info back to the database
        if (dbOps.updateEmployee(emp)) {
            System.out.println("                EMPLOYEE UPDATED SUCCESSFULLY");
            System.out.println("-------------------------------------------------------------");
            System.out.println("Employee ID: " + emp.getEmpID());
            System.out.println("Name:        " + emp.getName());
            System.out.println("SSN:         " + emp.getSSN());
            System.out.println("Job Title:   " + emp.getJobTitle());
            System.out.println("Division:    " + emp.getDivision());
            System.out.println("Salary:      $" + String.format("%.2f", emp.getSalary()));
        } else {
            System.out.println("               FAILED TO UPDATE EMPLOYEE");
        }

        System.out.println("=============================================================\n");
    }

    public void updateSalaryByRange() {

        System.out.print("Enter salary increase percentage (ex: 3.2): ");
        double percent = Double.parseDouble(sc.nextLine());

        System.out.print("Enter minimum salary range: ");
        double min = Double.parseDouble(sc.nextLine());

        System.out.print("Enter maximum salary range: ");
        double max = Double.parseDouble(sc.nextLine());

        System.out.println("=============================================================");

        //Updates the salary for all employees whose salary is within a minimum and maximum range
        if (dbOps.updateSalaryByRange(percent, min, max)) {
            System.out.println("             SALARY UPDATED FOR QUALIFYING EMPLOYEES");
            System.out.println("-------------------------------------------------------------");
            System.out.println("Percentage Increase: " + percent + "%");
            System.out.println("Range: $" + min + " - $" + max);
        } else {
            System.out.println("           NO EMPLOYEES FOUND IN SPECIFIED RANGE");
        }

        System.out.println("=============================================================\n");
    }

    public void generateFullEmployeePayReport(String keyword) {

        //Generates a complete pay report for one specific employee
        report.generateFullEmployeePayReport(keyword);
    }

    public void generateMonthlyPayrollReport() {

        //Generates salary totals grouped by job title
        report.generateJobTitleSalaryReport();

        //Generates salary totals grouped by division
        report.generateDivisionSalaryReport();
    }
}
