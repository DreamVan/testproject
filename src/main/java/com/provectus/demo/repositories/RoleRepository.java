package com.provectus.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.provectus.demo.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>
{
	Role findByName(String name);
}
