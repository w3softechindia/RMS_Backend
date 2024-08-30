package com.example.rms.serviceImplementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rms.entity.Employee;
import com.example.rms.entity.Interview;
import com.example.rms.entity.Role;
import com.example.rms.repository.EmployeeRepository;
import com.example.rms.repository.InterviewRepository;
import com.example.rms.repository.RoleRepository;
import com.example.rms.service.AdminService;

@Service
public class AdminServiceImplementation implements AdminService{
	
	
//	public String getEncodedPassword(String employeePassword) {
//		return passwordEncoder.encode(employeePassword);
//	}

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private InterviewRepository interviewRepository;
	
	@Override
	public String initRoleAndAdmin() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		roleRepository.save(adminRole);

		Role employeeRole = new Role();
		employeeRole.setRoleName("Developer");
		roleRepository.save(employeeRole);
		
		Role testerRole = new Role();
		testerRole.setRoleName("Tester");
		roleRepository.save(testerRole);
		
		Role teamLeadRole = new Role();
		teamLeadRole.setRoleName("TeamLead");
		roleRepository.save(teamLeadRole);
		
		

		return "Success";
	}

	@Override
	public Employee addAdmin(Employee admin) {

		Role role = roleRepository.findById("Admin").get();
		Set<Role> adminRole = new HashSet<>();
		adminRole.add(role);
		admin.setRoles(adminRole);
		return employeeRepository.save(admin);
	}
	
	@Override
	public Employee addEmployee(Employee employee, String roleName) throws Exception {
		Role role = roleRepository.findById(roleName).get();
		Set<Role> employeeRole = new HashSet<>();
		employeeRole.add(role);
		employee.setRoles(employeeRole);

		String originalPassword = employee.getEmployeePassword();

		employee.setEmployeePassword(originalPassword);

		return employeeRepository.save(employee);
	}
	
	

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> all = employeeRepository.findAll();
		return all;
	}

	@Override
	public Interview scheduleInterview(Interview interview, String employeeId, String teamLeadId) {
	    // Fetch the employee by ID
	    Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
	    if (!employeeOpt.isPresent()) {
	        throw new RuntimeException("Employee not found with ID: " + employeeId);
	    }
	    Employee employee = employeeOpt.get();

	    // Fetch the team lead by ID
	    Optional<Employee> teamLeadOpt = employeeRepository.findById(teamLeadId);
	    if (!teamLeadOpt.isPresent() || !teamLeadOpt.get().getRoles().stream().anyMatch(role -> role.getRoleName().equals("TeamLead"))) {
	        throw new RuntimeException("Team Lead not found or does not have TeamLead role with ID: " + teamLeadId);
	    }
	    Employee teamLead = teamLeadOpt.get();

	    // Set the employee and team lead in the interview
	    interview.setEmployee(employee);
	    interview.setTeamLead(teamLead);

	    // Save and return the scheduled interview
	    return interviewRepository.save(interview);
	}

	    @Override
	    public Interview getInterviewById(int interviewId) {
	        return interviewRepository.findById(interviewId).orElse(null);
	    }

	    @Override
	    public List<Interview> getInterviewsByEmployeeId(String employeeId) {
	        return interviewRepository.findByEmployee_EmployeeId(employeeId);
	    }
	   
	    @Override
	    public List<Employee> getEmployees() {
	        // Fetch all employees
	        List<Employee> allEmployees = employeeRepository.findAll();

	        // Filter out employees with the "Admin" role
	        List<Employee> nonAdminEmployees = allEmployees.stream()
	                .filter(employee -> employee.getRoles().stream()
	                        .noneMatch(role -> role.getRoleName().equalsIgnoreCase("Admin")|| role.getRoleName().equalsIgnoreCase("TeamLead")))
	                .collect(Collectors.toList());

	        return nonAdminEmployees;
	    }
	    
	    @Override
	    public List<Employee> getTeamLeads() {
	        return employeeRepository.findAll().stream()
	                .filter(employee -> employee.getRoles().stream()
	                        .anyMatch(role -> role.getRoleName().equalsIgnoreCase("TeamLead")))
	                .collect(Collectors.toList());
	    }


	    @Override
	    public List<Interview> getAllInterviews() {
	        return interviewRepository.findAll();
	    }
}
