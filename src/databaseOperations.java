import java.sql.*;
import java.util.*;

public class DatabaseOperations {

    //Creates and returns a connection to the MySQL database
    private Connection connect() throws SQLException {
        try {
            //Loads the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Database connection details
        String url = "jdbc:mysql://localhost:3306/project";
        String user = "root";
        String password = "group9_csc3350";

        return DriverManager.getConnection(url, user, password);
    }

    public Employee getEmployeeByKeyword(String keyword) {

        String sql;
        Employee emp = null;

        //Chooses search column depending on the keyword:
        //9 digits = SSN
        //Any digits but not 9 Digits = Employee ID
        //Anything else = Name search
        if (keyword.matches("\\d+")) {
            if (keyword.length() == 9) {
                sql = "SELECT * FROM Employee WHERE ssn = ?";
            } else {
                sql = "SELECT * FROM Employee WHERE empId = ?";
            }
        } else {
            sql = "SELECT * FROM Employee WHERE name = ?";
        }

        //Query database and build Employee object if found
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, keyword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emp = new Employee(
                        rs.getInt("empId"),
                        rs.getString("name"),
                        rs.getString("ssn"),
                        rs.getString("jobTitle"),
                        rs.getString("division"),
                        rs.getDouble("salary")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    public List<Employee> getAllEmployees() {

        //Gets every employee from the table
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            //Builds a list of Employee objects from DB rows
            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("empId"),
                        rs.getString("name"),
                        rs.getString("ssn"),
                        rs.getString("jobTitle"),
                        rs.getString("division"),
                        rs.getDouble("salary")
                );
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addEmployee(Employee emp) {

        //Inserts a new employee row into the database
        String sql = "INSERT INTO Employee (empId, name, ssn, jobTitle, division, salary) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emp.getEmpID());
            stmt.setString(2, emp.getName());
            stmt.setString(3, emp.getSSN());
            stmt.setString(4, emp.getJobTitle());
            stmt.setString(5, emp.getDivision());
            stmt.setDouble(6, emp.getSalary());

            //Returns true if at least one row was inserted
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeEmployee(int empId) {

        //Deletes an employee by their ID
        String sql = "DELETE FROM Employee WHERE empId = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empId);

            //Returns true if a row was removed
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmployee(Employee emp) {

        //Updates all fields for an existing employee
        String sql = "UPDATE Employee SET name = ?, ssn = ?, jobTitle = ?, division = ?, salary = ? WHERE empId = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getSSN());
            stmt.setString(3, emp.getJobTitle());
            stmt.setString(4, emp.getDivision());
            stmt.setDouble(5, emp.getSalary());
            stmt.setInt(6, emp.getEmpID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSalaryByRange(double percentIncrease, double minSalary, double maxSalary) {

        //Increases salary for employees within the given salary range
        String sql = "UPDATE Employee SET salary = salary + (salary * ? / 100) " +
                     "WHERE salary >= ? AND salary < ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, percentIncrease);
            stmt.setDouble(2, minSalary);
            stmt.setDouble(3, maxSalary);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
