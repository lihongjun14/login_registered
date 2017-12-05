package com.li.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.li.Model.ConnectDataBase;
import com.li.dao.DAO;

/**
 * 数据访问层具体实现类
 * @author 2016
 *
 */

public class DAOImpl implements DAO{
	
	private static Statement st;
	 //查询数据表的所有记录
    public ResultSet select(){ 
        ResultSet result = null;
        try
        {	
        	ConnectDataBase c=ConnectDataBase.getConnectDataBase();
        	st=c.getConnection().createStatement();
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
        	ConnectDataBase c=ConnectDataBase.getConnectDataBase();
        	st=c.getConnection().createStatement();
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
        	ConnectDataBase c=ConnectDataBase.getConnectDataBase();
        	st=c.getConnection().createStatement();
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
    		ConnectDataBase c=ConnectDataBase.getConnectDataBase();
        	st=c.getConnection().createStatement();
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
    		ConnectDataBase c=ConnectDataBase.getConnectDataBase();
        	st=c.getConnection().createStatement();
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
    		ConnectDataBase c=ConnectDataBase.getConnectDataBase();
        	st=c.getConnection().createStatement();
            String sql = "DELETE FROM test_user WHERE id = "+id;  
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }    
    
}
