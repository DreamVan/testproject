package com.provectus.demo.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.provectus.demo.dto.CompanyDto;
import com.provectus.demo.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService
{
	@Autowired CompanyRepository companyRepository;

	@Override
	public Set<CompanyDto> getAllCompanies()
	{
		return companyRepository.findAll().stream()
			.map(company -> new CompanyDto(company.getName()))
			.collect(Collectors.toSet());
	}
}
