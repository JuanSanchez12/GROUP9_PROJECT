import java.util.*;

public class Payroll {

    private DatabaseOperations dbOps;

    public Payroll(DatabaseOperations dbOps) {
        this.dbOps = dbOps;
    }

    //Returns monthly gross income
    public double getMonthlyGross(Employee emp) {
        return emp.getSalary() / 12;
    }

    //Calculates tax based on the annual salary bracket
    public double calculateMonthlyTax(Employee emp) {
        double annual = emp.getSalary();
        if (annual <= 50000) return (annual * 0.10) / 12;
        else if (annual <= 100000) return (annual * 0.20) / 12;
        else return (annual * 0.30) / 12;
    }

    //Returns flat 5 percent deduction
    public double calculateOtherDeductions(Employee emp) {
        return getMonthlyGross(emp) * 0.05;
    }

    //Net monthly pay after tax + deductions
    public double getMonthlyNet(Employee emp) {
        double gross = getMonthlyGross(emp);
        double tax = calculateMonthlyTax(emp);
        double other = calculateOtherDeductions(emp);
        return gross - tax - other;
    }

    // Average monthly salary of a group
    public double getAverageMonthlySalary(List<Employee> employees) {

        if (employees.isEmpty()) {
        return 0;
        }

        double total = 0;

        // Add each employee's monthly salary
        for (Employee emp : employees) {
            total += emp.getSalary() / 12;
        }

        // Return the average
        return total / employees.size();
    }

}
