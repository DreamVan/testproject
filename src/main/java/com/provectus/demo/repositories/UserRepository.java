package com.provectus.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.provectus.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
	User findByEmail(String email);

	void deleteById(String id);
}
