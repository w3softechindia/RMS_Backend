package com.example.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rms.entity.Employee;
import com.example.rms.entity.Interview;
import com.example.rms.service.AdminService;

import jakarta.annotation.PostConstruct;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	
	@PostConstruct
	public void initRoleAndAdmin() {
		adminService.initRoleAndAdmin();
	}
	
	@PostMapping("/addAdmin")
	public ResponseEntity<Employee> addAdmin(@RequestBody Employee admin) {

		Employee addAdmin = adminService.addAdmin(admin);
		return new ResponseEntity<Employee>(addAdmin, HttpStatus.OK);

	}
	
	@PostMapping("/addEmployee/{roleName}")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee, @PathVariable String roleName)
			throws Exception {

		Employee addEmployee = adminService.addEmployee(employee, roleName);
		return new ResponseEntity<Employee>(addEmployee, HttpStatus.OK);
	}
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> saveEmployee = adminService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(saveEmployee, HttpStatus.OK);

	}
	
	@GetMapping("/getTeamLeads")
	public ResponseEntity<List<Employee>> getTeamLeads() {
		List<Employee> saveEmployee = adminService.getTeamLeads();
		return new ResponseEntity<List<Employee>>(saveEmployee, HttpStatus.OK);

	}
	
	@GetMapping("/getEmployees")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> saveEmployee = adminService.getEmployees();
		return new ResponseEntity<List<Employee>>(saveEmployee, HttpStatus.OK);

	}
	
	

	@PostMapping("/scheduleInterview/{employeeId}/{teamLeadId}")
	public ResponseEntity<Interview> scheduleInterview(@RequestBody Interview interview,@PathVariable String employeeId,@PathVariable String teamLeadId) {
	    try {
	        Interview scheduledInterview = adminService.scheduleInterview(interview, employeeId, teamLeadId);
	        return new ResponseEntity<>(scheduledInterview, HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@GetMapping("/getInterviewById/{interviewId}")
	public ResponseEntity<Interview> getInterviewById(@PathVariable int interviewId) {
		Interview interview = adminService.getInterviewById(interviewId);
		return interview != null ? ResponseEntity.ok(interview) : ResponseEntity.notFound().build();
	}

	@GetMapping("/getInterviewsByEmployeeId/{employeeId}")
	public ResponseEntity<List<Interview>> getInterviewsByEmployeeId(@PathVariable String employeeId) {
		List<Interview> interviews = adminService.getInterviewsByEmployeeId(employeeId);
		return ResponseEntity.ok(interviews);
	}

	@GetMapping("/getAllInterviews")
	public ResponseEntity<List<Interview>> getAllInterviews() {
		List<Interview> interviews = adminService.getAllInterviews();
		return ResponseEntity.ok(interviews);
	}
}
