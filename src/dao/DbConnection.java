package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection implements AutoCloseable 
{   
    private final String driver;
    private final String url;
    private final String user;
    private final String password;
    
    private Statement statement;
    private Connection connection;
    
    public DbConnection()
    {
        password = "PASS";
        user = "USER";
        url = "URL";
        driver = "com.mysql.jdbc.Driver";
        
        try {
            CreateConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void CreateConnection() throws ClassNotFoundException, SQLException
    {
        try {
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    @Override
    public void close() throws Exception 
    {
        connection.close();
    }

    public Statement getStatement() {
        return statement;
    }
}