import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyDB {
    
    Connection conn ;
    
    public Connection getCon() throws SQLException
    {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/HelloDhakaDotCom","Ansary","104");
        System.out.println("Connection has been connection");
        return conn;
    }
    
}