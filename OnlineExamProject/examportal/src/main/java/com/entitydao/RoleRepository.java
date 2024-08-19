package com.entitydao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Role;


public interface RoleRepository extends JpaRepository<Role,Integer> {
	     
         
}
