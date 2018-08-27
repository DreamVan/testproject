package com.provectus.demo.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.demo.dto.CompanyDto;
import com.provectus.demo.services.CompanyService;

@RestController
public class CompanyRestController
{
	@Autowired CompanyService companyService;

	@GetMapping("/companies")
	public Set<CompanyDto> findAllCompanies()
	{
		return companyService.getAllCompanies();
	}
}
