package com.li.dao;

import java.sql.ResultSet;

/**
 * 数据访问层接口
 * @author 2016
 *
 */
public interface DAO {
	
	public ResultSet select();
	public ResultSet selectpersonal(String name);
	public void printTABLE(ResultSet result);
	public void executeUpdate(String username,String password,String email,String phone,String birth,String sex,String address,String personal,String nickname);
	public void executeADD(String username,String password,String email,String phone);
	public void executeDELETE(int id);
}
