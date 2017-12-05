package com.li.Model;

/**
 * Shiro权限框架的工具类
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.li.dao.DAO;
import com.li.daoImpl.DAOImpl;

@Service   
@Transactional   
public class Shiro extends AuthorizingRealm{   
     
	private static DAO dao;
       
    /**  
     * 权限认证  
     */    
    @Override    
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {    
//        //获取登录时输入的用户名    
//        String loginName=(String) principalCollection.fromRealm(getName()).iterator().next();    
//        //到数据库查是否有此对象    
//        User user=userService.findByName(loginName);  
//        if(user!=null){    
//            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）    
//            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();    
//            //用户的角色集合    
//            info.setRoles(user.getRolesName());    
//            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要    
//            List<Role> roleList=user.getRoleList();    
//            for (Role role : roleList) {    
//                info.addStringPermissions(role.getName());    
//            }    
//            return info;    
//        }    
//        return null;    
    	Set<String> roleNames = new HashSet<String>();  
        Set<String> permissions = new HashSet<String>();  
        roleNames.add("admin");//添加角色。对应到index.jsp  
        roleNames.add("administrator");  
        permissions.add("create");//添加权限,对应到index.jsp  
        permissions.add("login.do?main");  
        permissions.add("login.do?logout");  
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);  
        info.setStringPermissions(permissions);  
        return info; 
    }    	
    
    /**  
     * 登录认证;  
     */    
    @SuppressWarnings("unused")
	@Override    
    protected AuthenticationInfo doGetAuthenticationInfo(    
            AuthenticationToken authenticationToken) throws AuthenticationException {    
        //UsernamePasswordToken对象用来存放提交的登录信息    
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;    
        String name=token.getUsername();
        //查出是否有此用户    
        dao=new DAOImpl();
        ResultSet r=dao.selectpersonal(name);
        UserBean user=null;
        try {
        	while(r.next()) {
        		user=new UserBean(r.getString("username"),r.getString("password"),r.getString("email"),r.getString("phone"));      		
        	}        	
        	System.out.println(user.getEmail());
        }catch(NullPointerException | SQLException e){
        	throw new UnknownAccountException();
        }        
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());   
            
        
    }    
    
}    
