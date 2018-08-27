package com.provectus.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User
{
	@Id
	private String id;

	private String profile;

	private String email;

	@ManyToOne
	private Company company;

	@ManyToOne
	private Role role;

	public User()
	{
	}

	public User(String id, String email, String profile, Company company, Role role)
	{
		this.id = id;
		this.email = email;
		this.profile = profile;
		this.company = company;
		this.role = role;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getProfile()
	{
		return profile;
	}

	public void setProfile(String profile)
	{
		this.profile = profile;
	}

	public Company getCompany()
	{
		return company;
	}

	public void setCompany(Company company)
	{
		this.company = company;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
