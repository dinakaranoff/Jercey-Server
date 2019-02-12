package com.tutorialspoint;

public class Manager {

	User[] user;
	public User[] getUser() {
		return user;
	}
	public void setUser(User[] user) {
		this.user = user;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	String managerName;
	String salary;
	String address;
	
		
}
