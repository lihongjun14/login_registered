package com.li.Model;
import java.sql.*;

/**
 * 连接数据库的工具类
 * @author 2016
 *
 */
public class ConnectDataBase {
	private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/test";  
	private static final String DBUSER = "root";  
	private static final String DBPASS = "1234";
	private static Connection conn;
	
	private static ConnectDataBase Connect=new ConnectDataBase();
	
    private ConnectDataBase() {
        try
        {
            Class.forName(DRIVER_MYSQL);
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }	
    
    public static ConnectDataBase getConnectDataBase() {
    	return Connect;
    }
    
    public  Connection getConnection (){
		return conn;
	}
}
