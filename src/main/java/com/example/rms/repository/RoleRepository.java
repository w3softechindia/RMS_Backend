package com.example.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rms.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
