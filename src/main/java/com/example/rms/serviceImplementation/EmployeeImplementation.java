package com.example.rms.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rms.repository.EmployeeRepository;
import com.example.rms.repository.InterviewRepository;
import com.example.rms.service.EmployeeService;

@Service
public class EmployeeImplementation implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private InterviewRepository interviewRepository;


	


}
