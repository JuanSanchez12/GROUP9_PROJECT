import java.util.List;
import java.util.Scanner;

public class overallSystem {

    Scanner sc = new Scanner(System.in);
    private databaseOperations dbOps = new databaseOperations();

    public void searchEmployee() {
        System.out.print("Enter name, SSN, or Employee ID to search: ");
        String keyword = sc.nextLine();

        List<employee> results = dbOps.searchEmployee(keyword);

        if (results.isEmpty()) {
            System.out.println("No employee found.");
        } else {
            System.out.println("Search Results:");
            for (employee emp : results) {
                System.out.println(emp.toString());
            }
        }
    }
}
