public class Payroll {

    private DatabaseOperations dbOps;

    public Payroll(DatabaseOperations dbOps) {
        this.dbOps = dbOps;
    }

    // Calculate monthly net salary using name, SSN, or ID
    public double calculateNetSalary(String keyword) {
        Employee emp = dbOps.getEmployeeByKeyword(keyword);

        if (emp == null) {
            throw new IllegalArgumentException("Employee not found for: " + keyword);
        }

        double annualSalary = emp.getSalary();
        double grossMonthly = annualSalary / 12;
        double tax = calculateTax(grossMonthly);
        double otherDeductions = grossMonthly * 0.05;

        return grossMonthly - tax - otherDeductions;
    }

    // Calculate tax per month based on tax brackets
    private double calculateTax(double monthlySalary) {
        double annual = monthlySalary * 12;

        if (annual <= 50000) return annual * 0.10 / 12;
        else if (annual <= 100000) return annual * 0.20 / 12;
        else return annual * 0.30 / 12;
    }
}
