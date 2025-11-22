import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class databaseOperations {

    //Database Connection
    private Connection connect() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

        String url = "jdbc:mysql://localhost:3306/project"; 
        String user = "root"; 
        String password = "yourPassword"; 

        return DriverManager.getConnection(url, user, password);
    }

    //Search Employee by Name, SSN, or ID
    public List<employee> searchEmployee(String keyword) {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<employee> list = new ArrayList<>();

    String sql = "SELECT * FROM Employee WHERE name LIKE ? OR ssn LIKE ? OR empId LIKE ?";

    try {
        conn = connect();
        stmt = conn.prepareStatement(sql);

        String likeValue = "%" + keyword + "%";

        stmt.setString(1, likeValue);
        stmt.setString(2, likeValue);
        stmt.setString(3, likeValue);

        rs = stmt.executeQuery();

        while (rs.next()) {
            employee emp = new employee(
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
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return list;
}

}
