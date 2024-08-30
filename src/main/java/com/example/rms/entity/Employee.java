package com.example.rms.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@Id
	private String employeeId;
	private String firstName;
	private String lastName;
	private String address;
	private String webMail;
	private String webMailPassword;
	private String employeeEmail;
	private String employeePassword;
	private long phoneNumber;
	private String imagePath;
	private String dateOfJoin;
	private String status;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Employee_Roles", joinColumns = { @JoinColumn(name = "Employee_Id") }, inverseJoinColumns = {
			@JoinColumn(name = "Role_Name") })
	private Set<Role> roles = new HashSet<>();
}
