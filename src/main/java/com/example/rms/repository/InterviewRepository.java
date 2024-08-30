package com.example.rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rms.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Integer>{

	List<Interview> findByEmployee_EmployeeId(String employeeId);

}
