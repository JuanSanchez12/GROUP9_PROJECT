public class Payroll {

    private DatabaseOperations dbOps;

    public Payroll(DatabaseOperations dbOps) {
        this.dbOps = dbOps;
    }

    // Example: calculate net salary for one employee
    public double calculateNetSalary(int empId) {
        Employee emp = dbOps.getEmployeeById(empId);  // Daniel’s part
        if (emp == null) {
            throw new IllegalArgumentException("Employee not found: " + empId);
        }

        double baseSalary = emp.getSalary();
        double tax = calculateTax(baseSalary);
        double otherDeductions = calculateOtherDeductions(baseSalary);

        return baseSalary - tax - otherDeductions;
    }

    private double calculateTax(double salary) {
        // placeholder logic – you can agree rules with the team
        if (salary <= 50000) return salary * 0.10;
        else if (salary <= 100000) return salary * 0.20;
        else return salary * 0.30;
    }

    private double calculateOtherDeductions(double salary) {
        // e.g., benefits, insurance, etc. You can keep simple if project is small
        return salary * 0.05;
    }

    // maybe a method to generate payroll for ALL employees
    public Map<Employee, Double> generatePayrollForAllEmployees() {
        List<Employee> employees = dbOps.getAllEmployees();
        Map<Employee, Double> payroll = new HashMap<>();

        for (Employee emp : employees) {
            double net = calculateNetSalary(emp.getEmpId());
            payroll.put(emp, net);
        }

        return payroll;
    }
}
