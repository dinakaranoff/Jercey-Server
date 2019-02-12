package com.tutorialspoint;  

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

import javax.ws.rs.Consumes;
import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

//Service: http://localhost:8080/Test/UserService

@Path("/UserService") 
public class UserService {  
   UserDao userDao = new UserDao();  
 
  /* http://localhost:8080/Test/UserService/users
   * Response code :200  for  Accept=application/json,
   * Response code 406 for applicaiton/xml 
   */
   @GET 
   @Path("/users") 
   @Produces(MediaType.APPLICATION_JSON) 
   public List<User> getJsonUser(){ 
      return userDao.getAllUsers(); 
   }
  
  /* http://localhost:8080/Test/UserService/users
  * Response code :200  for  Accept=application/xml,
  * Response code 406 for applicaiton/json 
  * These 2 methods are same end point. if we dont pass any header it ll send applicaiton/xml
  */
  @GET 
  @Path("/users") 
  @Produces(MediaType.APPLICATION_XML) 
  public List<User> getXmlUser(){ 
	  return userDao.getAllUsers(); 
 }
 
   
  /* Return Code: 406:
   * Pass header "accept"="application/json" then response code is 406. Not acceptable Media Type
     */
  @GET 
  @Path("/WrongMIME") 
  @Produces(MediaType.APPLICATION_XML) 
  public List<User> WrongMIME406 (){ 
	  return userDao.getAllUsers(); 
 }
 
  
  /* http://localhost:8080/Test/UserService/users/{number} 
   * Response code :200  for  Accept=application/json,
   * with queryString 
   */
   @GET 
   @Path("/queryUser") 
   @Produces(MediaType.APPLICATION_JSON) 
   public User userAccountQueryString(@QueryParam("id") Integer id) throws SQLException{ 	
      return userDao.getuserAccount(id); 
   }
   
   
   
   /* http://localhost:8080/Test/UserService/users/{number} 
    * Response code :200  for  Accept=application/json,
    * with queryString 
    */
    @GET 
    @Path("/queryUsers") 
    @Produces(MediaType.APPLICATION_JSON) 
    public User userAccountQueryString(@QueryParam("id") Integer id,@QueryParam("age") Integer age) throws SQLException{
    	System.out.println("adadasdsa"+id+" "+age);
       return userDao.getuserAccount2(id,age); 
    }
   

   /* http://localhost:8080/Test/UserService/users/{number} 
    * Response code :200  for  Accept=application/json,
    * with queryString 
    */
    @GET 
    @Path("/users/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public User userAccount(@PathParam("id") Integer id) throws SQLException{ 	   
       return userDao.getuserAccount(id); 
    }
 
   
   /*http://localhost:8080/Test/UserService/createAccount . Input JSON Object
   * Read Value from Json Structure and Automatically converted to Respective Object Struture using JAX-RC Object mapper.
   * Created UserAccount
   * User has to pass "contentType="application/json""
   * 
   */
   @POST
   @Path("/createAccount")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createAccount(User user){
	   System.out.println("Excuting /createAccount" );
	   userDao.createUser(user);
	   return Response.status(201).entity(user).build();
}
   
   
   /*http://localhost:8080/Test/UserService/createAccount . Input JSON Object
    * Read Value from Json Structure and Automatically converted to Respective Object Struture using JAX-RC Object mapper.
    * Created UserAccount
    * User has to pass "contentType="application/json""
    * 
    */
    @PUT
    @Path("/updateAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAccount(User user){
 	   System.out.println("Excuting /createAccount" );
 	  userDao.createUser(user);
 	   return Response.status(201).entity(user).build();
 }
    
    
    
    
    
    /*http://localhost:8080/Test/UserService/createAccount . Input JSON Object
     * Read Value from Json Structure and Automatically converted to Respective Object Struture using JAX-RC Object mapper.
     * Created UserAccount
     * User has to pass "contentType="application/json""
     * 
     */
     @POST
     @Path("/createResult")
     @Consumes(MediaType.APPLICATION_JSON)
     public Response createResult(Result result){
  	   System.out.println("Excuting /createResult" );
  	  // userDao.createManager(man);
  	   System.out.println("AGE"+result.getAge());
  	   
  	   User user=new User();
  	   user=result.getUser();   	  
  	   System.out.println(user.getId());
  	//	for(int i=0;i<2;i++){
  		  userDao.createUser(user);
  		//  userDao.updateUser(user.getId(),result.getAge());		  
  	//	 }
		 return Response.status(201).entity("Success").build();
  	  }
     
     @GET
     @Path("/GetResult/{id}")
     @Consumes(MediaType.APPLICATION_JSON)
     public Response getResult(@PathParam("id") Integer id){
  	   System.out.println("Excuting /createAccount" );
  	 Result result=null;
  	 try {
			result=userDao.getUserResult(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		  		 return Response.status(201).entity(result).build();
  	   
  }
     
}
