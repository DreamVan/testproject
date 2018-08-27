package com.provectus.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.provectus.demo.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>
{
	Company findByName(String name);
}
