import java.sql.*;
import java.util.*;

public class DatabaseOperations {

    private Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/project";
        String user = "root";
        String password = "group9_csc3350";

        return DriverManager.getConnection(url, user, password);
    }

    public Employee getEmployeeByKeyword(String keyword) {

        String sql;
        Employee emp = null;

        if (keyword.matches("\\d+")) {
            if (keyword.length() == 9) {
                sql = "SELECT * FROM Employee WHERE ssn = ?";
            } else {
                sql = "SELECT * FROM Employee WHERE empId = ?";
            }
        } else {
            sql = "SELECT * FROM Employee WHERE name = ?";
        }

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
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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
}
