import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        OverallSystem system = new OverallSystem();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
            System.out.println("1. Search Employee");
            System.out.println("2. Monthly Payroll Report");
            System.out.println("3. Full Employee Pay Report");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    system.searchEmployee();
                    break;

                case 2:
                    system.generateMonthlyPayrollReport();
                    break;

                case 3:
                    System.out.print("Enter name, SSN, or Employee ID: ");
                    String key = sc.nextLine();
                    system.generateFullEmployeePayReport(key);
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        sc.close();
    }
}
