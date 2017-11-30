package com.li.Model;
import java.sql.*;
public class ConnectDataBase {
	private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/test";  
	private static final String DBUSER = "root";  
	private static final String DBPASS = "1234";
	private static Connection conn=null;
	private static Statement st;
//	连接到数据库
    public ConnectDataBase() {
        try
        {
            Class.forName(DRIVER_MYSQL);
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            st = conn.createStatement();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
  //查询数据表的所有记录
    public ResultSet select(){ 
        ResultSet result = null;
        try
        {
            result = st.executeQuery("SELECT * FROM test_user");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    //查询指定用户名的记录
    public ResultSet selectpersonal(String name){ 
        ResultSet result = null;
        try
        {
            result = st.executeQuery("SELECT * FROM test_user Where username='"+name+"'");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    //输出结果
    public void printTABLE(ResultSet result) {
        try
        {
            while(result.next()) {
                System.out.println(result.getString(1)+"   "+result.getString(2)+"   "+result.getString(3)+"   "+result.getString(4)+"   "+result.getString(5));
            }
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //更新指定用户名的记录
    public void executeUpdate(String username,String password,String email,String phone,String birth,String sex,String address,String personal,String nickname){
    	try{
            String sql = "UPDATE test_user SET password = '"+password+"', email ='"+email+"', phone ='"+phone+"', birth ='"+birth+"', sex ='"+sex+"', address ='"+address+"', personal ='"+personal+"', nickname ='"+nickname+"' WHERE username ='"+username+"'";
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
    //增加新的用户记录到数据表
    public void executeADD(String username,String password,String email,String phone){
    	try{
            String sql = "INSERT test_user VALUE(NULL,'"+username+"','"+password+"','"+email+"','"+phone+"',null,null,null,null,null)";
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
//    删除指定id的记录
    public void executeDELETE(int id){
    	try{
            String sql = "DELETE FROM test_user WHERE id = "+id;  
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
    	ConnectDataBase a = new ConnectDataBase();
    	//a.executeDELETE(1);
        ResultSet result = a.select();
        a.printTABLE(result);
    }
	
}
