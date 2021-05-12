/**
 * PACKAGE_NAME
 * Nombre_project: JDBC
 * Load
 * Created by: MarcosRa
 * Date : 21/04/2021
 * Description:
 **/
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class LoadDriver {

    public static void main (String[] args)
    {
        System.out.println("\n\n***** MySQL JDBC Connection Testing *****");
        Connection conn = null;
        try
        {
            String userName = "root";
            String password = "";
            String url = "jdbc:MySQL://localhost:3306/ejercicionominas";
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("\nDatabase Connection Established...");

        }
        catch (Exception ex)
        {
            System.err.println ("Cannot connect to database server");
            ex.printStackTrace();
        }

        finally
        {
            if (conn != null)
            {
                try
                {

                    System.out.println("\n***** Let terminate the Connection *****");
                    conn.close ();
                    System.out.println ("\nDatabase connection terminated...");
                }
                catch (Exception ex)
                {
                    System.out.println ("Error in connection termination!");
                }
            }
        }
    }
}