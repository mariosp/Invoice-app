package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

import javax.swing.JOptionPane;

public class MySqlConn {
	
    public static Connection dbConnector() throws SQLException {
    	Connection conn = null;
        try {
        	String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            String url = "jdbc:mysql://localhost:3306/invoice?useUnicode=true&characterEncoding=UTF-8";
            conn = DriverManager.getConnection(url,"root","1234");
            return conn;
        }catch(Exception e)
        {
        	JOptionPane.showMessageDialog(null, e);
        	return null;
        }
        
    }
    
    
}
