package com.tutorialspoint;  

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;  
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

public class UserDao { 
   public List<User> getAllUsers(){ 
      
      List<User> userList = null; 
      try { 
         File file = new File("Users.dat"); 
        if (!file.exists()) 
    	  { 
            User user = new User(1, "Mahesh", "Teacher"); 
            userList = new ArrayList<User>(); 
            userList.add(user); 
            saveUserList(userList); 
         } 
         else{ 
            FileInputStream fis = new FileInputStream(file); 
            ObjectInputStream ois = new ObjectInputStream(fis); 
            userList = (List<User>) ois.readObject(); 
            ois.close(); 
         } 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } catch (ClassNotFoundException e) { 
         e.printStackTrace(); 
      }   
      return userList; 
   } 
   private void saveUserList(List<User> userList){ 
      try { 
         File file = new File("Users.dat"); 
         FileOutputStream fos;  
         fos = new FileOutputStream(file); 
         ObjectOutputStream oos = new ObjectOutputStream(fos); 
         oos.writeObject(userList); 
         oos.close(); 
      } catch (FileNotFoundException e) { 
         e.printStackTrace(); 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } 
   }
 
   public User getuserAccount(int id) throws SQLException {
	User user = null;
	String sql="select EMPID,NAME,JOB,AGE from User where EMPID=?";
	System.out.println(sql);
	Map<Object,Object> obj=DBUtils.excuteQuery(sql, id);
	
//Need to identify dynamic way to return data like object mapper
	int idd =(Integer) obj.get("EMPID");
	System.out.println(idd);
	String name=obj.get("NAME").toString();
	System.out.println(name);

	String job=obj.get("JOB").toString();
	System.out.println(job);
	
	user=new User(idd,name,job);
	
	return user;
}

   public Result getUserResult(int id) throws SQLException {
		User user = new User();;
		String sql="select EMPID,NAME,JOB,AGE from User where EMPID=?";
		System.out.println(sql);
		Map<Object,Object> obj=DBUtils.excuteQuery(sql, id);
		
	//Need to identify dynamic way to return data like object mapper
		int idd =(Integer) obj.get("EMPID");
		System.out.println(idd);
		String name=obj.get("NAME").toString();
		System.out.println(name);

		String job=obj.get("JOB").toString();
		System.out.println(job);
		String age=obj.get("age").toString();

		user.setId(idd);
		user.setName(name);
		user.setProfession(job);
		Result result=new Result();
		result.setUser(user);
		result.setAge(age);
		return result;
	}

   

public void createUser(User user)  {
	 System.out.println("Excuting /createUser" );
	 String sql="Insert into User (EMPID,NAME,JOB) values (?,?,?)";
	 DBUtils.excuteQuery(sql,user);
}    

public  static void main(String[] args){
	UserDao ob=new UserDao();
	//ob.createUser(new User(18,"da","asdas"));
//	ob.getuserAccount(18);
}
//public void createManager(Manager man) {
//	String sql="Insert into Manager (MNR_ID,SALARY,ADDRESS,USER_ID) values (?,?,?,?)";
//	DBUtils.excuteQuery(sql,man);	
//}
public void updateUser(int id ,String  age) {
	// TODO Auto-generated method stub
	System.out.println("Excuting /UpdateUser" );
	String sql="update  User set age=? where EMPID=?";
	//.excuteUpdate(sql,id,age);
	
}
public User getuserAccount2(Integer id, Integer age) {
	User user = null;
	String sql="select EMPID,NAME,JOB,AGE from User where EMPID=? AND AGE=?";
	HashMap<String,Integer> hm=new HashMap<String,Integer>();
	hm.put("id", id);
	hm.put("age", age);
	
	Map<Object,Object> obj=DBUtils.excuteQuery(sql, hm);
	
//Need to identify dynamic way to return data like object mapper
	int idd =(Integer) obj.get("EMPID");
	System.out.println(idd);
	String name=obj.get("NAME").toString();
	System.out.println(name);

	String job=obj.get("JOB").toString();
	System.out.println(job);
	
	user=new User(idd,name,job);
	
	return user;
}

   
   
   
 
}
