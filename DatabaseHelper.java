import java.sql.*;

public class DatabaseHelper {
    public static Connection connection=null;
    public   PreparedStatement ps=null;
    public  ResultSet resultSet=null;
    static {
        try{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "sli19181");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        ps = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        resultSet=ps.executeQuery();
        return resultSet;
    }
    public int executeUpdate(String query, Object... params) throws SQLException {
        ps = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        return ps.executeUpdate();
    }

    public void closeResources(ResultSet rs) throws SQLException {
        if (rs != null) rs.close();
    }

    public void closeConnection() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (ps != null) ps.close();
        if (connection != null) connection.close();
    }
}
