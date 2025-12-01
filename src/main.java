import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        OverallSystem system = new OverallSystem();

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        //Keeps showing the menu until the user chooses 8
        while (choice != 8) {
            System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
            System.out.println("1. Search Employee");
            System.out.println("2. Add Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Update Salary Range");
            System.out.println("6. Monthly Payroll Report");
            System.out.println("7. Full Employee Pay Report");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                //Searches by name, SSN, or ID
                case 1:
                    system.searchEmployee();
                    break;

                //Adds a new employee
                case 2:
                    system.addEmployee();
                    break;

                //Removes an employee
                case 3:
                    system.removeEmployee();
                    break;

                //Updates existing employee info
                case 4:
                    system.updateEmployee();
                    break;

                //Updates salaries for a range of employees
                case 5:
                    system.updateSalaryByRange();
                    break;

                //Generates payroll summary for the month
                case 6:
                    system.generateMonthlyPayrollReport();
                    break;

                case 7:
                    System.out.print("Enter name, SSN, or Employee ID: ");

                    //User’s search input for generating a full pay report
                    String key = sc.nextLine();

                    system.generateFullEmployeePayReport(key);
                    break;

                case 8:
                    System.out.println("Exiting system...");
                    break;

                //Handles invalid menu choices
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        sc.close();
    }
}
