package com.provectus.demo.dto;

import java.security.Principal;

import com.provectus.demo.entities.User;

public class UserDto
{
	private String openId;
	private String email;
	private String profile;
	private String role = "ROLE_USER";
	private String company;
	private Principal principal;

	public UserDto()
	{
	}

	public UserDto(User user)
	{
		this.openId = user.getId();
		this.email = user.getEmail();
		this.profile = user.getProfile();
		this.role = user.getRole().getName();
		this.company = user.getCompany().getName();
	}

	public UserDto(String openId, String email, String profile, String company)
	{
		this.openId = openId;
		this.email = email;
		this.profile = profile;
		this.company = company;
	}

	public UserDto(String openId, String email, String profile, String company, String role)
	{
		this.openId = openId;
		this.email = email;
		this.profile = profile;
		this.company = company;
		this.role = role;
	}

	public String getOpenId()
	{
		return openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getProfile()
	{
		return profile;
	}

	public void setProfile(String profile)
	{
		this.profile = profile;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public Principal getPrincipal()
	{
		return principal;
	}

	public void setPrincipal(Principal principal)
	{
		this.principal = principal;
	}
}
