package BD;

import java.sql.*;

/**
 * BD
 * Nombre_project: JDBC
 * Conexion
 * Created by: MarcosRa
 * Date : 28/04/2021
 * Description:
 **/
public class Conexion {
    private static final String userName = "root";
    private static final String password = "";
    private static final String url = "jdbc:MySQL://localhost:3306/ejercicionominas";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);

    }
    public static void close(ResultSet r) throws SQLException {
        r.close();
    }
    public static void close(Statement s) throws SQLException {
        s.close();
    }
    public static void close(Connection conn) throws SQLException {
        conn.close();
    }
}
