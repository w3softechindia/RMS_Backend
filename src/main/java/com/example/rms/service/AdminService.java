package com.example.rms.service;

import java.util.List;

import com.example.rms.entity.Employee;
import com.example.rms.entity.Interview;

public interface AdminService {

	public String initRoleAndAdmin();

	public Employee addAdmin(Employee admin);

	Employee addEmployee(Employee employee, String roleName) throws Exception;
	
	public List<Employee> getAllEmployees();

	public Interview getInterviewById(int interviewId);

	public List<Interview> getInterviewsByEmployeeId(String employeeId);

	public List<Interview> getAllInterviews();
						
	List<Employee> getEmployees();

	List<Employee> getTeamLeads();

	Interview scheduleInterview(Interview interview, String employeeId, String teamLeadId);

}
