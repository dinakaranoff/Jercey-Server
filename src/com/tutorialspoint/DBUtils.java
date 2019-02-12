package com.tutorialspoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DBUtils {
	static Connection connection=null;
	public static Map<Object,Object> result=new HashMap<Object,Object>();
	
	public static Connection getconnection(){
		if(connection==null){
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","cfcNEBdUm6QerR6oo2UM");
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;		
	}
	
	/* Need to identify a way for dynamic data, and Object instead of User
	 * 
	 */
	public static  void excuteQuery(String sql,User obj)  {
		getconnection();
	
			PreparedStatement st;
			try {
				st = connection.prepareStatement(sql);
				st.setInt(1, obj.getId());
				st.setString(2, obj.getName());
				st.setString(3, obj.getProfession());
				st.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
	}

	//Need to find way to return generic data
	public static Map<Object,Object> excuteQuery(String sql, int id) throws  SQLException  {
		getconnection();
		try {
			PreparedStatement st=connection.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				result.put("EMPID", rs.getInt(1));
				result.put("NAME", rs.getString(2));
				result.put("JOB", rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
	}

	public static void excuteUpdate(String sql,int id, String age) {
		getconnection();
		int rs = 0;
		try {
			PreparedStatement st=connection.prepareStatement(sql);
			st.setInt(1, id);
			st.setString(2, age);
			System.out.println(st.toString());
			 rs=st.executeUpdate();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs==0){
		System.out.println("SQL Update is failed "+sql);
		}
	}

	public static Map<Object, Object> excuteQuery(String sql,
			HashMap<String, Integer> hm) {
		getconnection();
		try {
			PreparedStatement st=connection.prepareStatement(sql);
			Set<String> key=hm.keySet();
			Iterator<String> itr=key.iterator();
			int i=1;
			Integer value=null;
			while(itr.hasNext()){
				value=hm.get(itr.next());
				st.setObject(i, value);	
				i++;
			}
			System.out.println(st.toString());
			
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				result.put("EMPID", rs.getInt(1));
				result.put("NAME", rs.getString(2));
				result.put("JOB", rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
	}

	

}
