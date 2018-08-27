package com.provectus.demo.services;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import com.provectus.demo.dto.UserDto;
import com.provectus.demo.entities.Company;
import com.provectus.demo.entities.Role;
import com.provectus.demo.entities.User;
import com.provectus.demo.repositories.CompanyRepository;
import com.provectus.demo.repositories.RoleRepository;
import com.provectus.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired UserRepository userRepository;
	@Autowired RoleRepository roleRepository;
	@Autowired CompanyRepository companyRepository;

	@Override
	public UserDto getCurrentUser(Principal principal)
	{
		Map details = (Map) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
		String openId = details.get("sub").toString();
		String email = details.get("email").toString();
		String profile = details.get("profile").toString();
		UserDto user = new UserDto(openId, email, profile, "");
		user.setPrincipal(principal);

		UserDto userOptional = getUserById(openId);
		if (userOptional == null)
		{
			Role role = roleRepository.findByName("ROLE_ADMIN");
			User newUser = new User(openId, email, profile, null, role);
			user.setRole("ROLE_ADMIN");
			userRepository.save(newUser);
		}
		else
		{
			user.setRole(userOptional.getRole());
			user.setCompany(userOptional.getCompany());
		}

		return user;
	}

	@Override
	public void saveUser(User user)
	{
		userRepository.save(user);
	}

	@Override
	public List<UserDto> getAllUsers()
	{
		return userRepository.findAll().stream()
			.map(user -> new UserDto(user.getId(), user.getEmail(), user.getProfile(), user.getCompany() == null ? "" : user.getCompany().getName(), user.getRole().getName()))
			.collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(String id)
	{
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent())
		{
			User user = userOptional.get();
			UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getProfile(), user.getCompany() == null ? "" : user.getCompany().getName());
			userDto.setRole(user.getRole().getName());
			return userDto;
		}
		else
		{
			return null;
		}
	}

	@Override
	public UserDto createUser(UserDto user)
	{
		return new UserDto(userRepository
			.save(new User(user.getOpenId(), user.getEmail(), user.getProfile(), companyRepository.findByName(user.getCompany()), roleRepository.findByName(user.getRole()))));
	}

	@Override
	public UserDto update(UserDto user)
	{
		User currentState = userRepository.findById(user.getOpenId()).get();
		Company company = companyRepository.findByName(user.getCompany());
		currentState.setCompany(company);
		currentState.setEmail(user.getEmail());
		currentState.setProfile(user.getProfile());
		currentState.setRole(roleRepository.findByName(user.getRole()));
		userRepository.save(currentState);
		return new UserDto(currentState);
	}

	@Override
	public UserDto delete(String id)
	{
		UserDto user = getUserById(id);
		if (user != null)
		{
			userRepository.deleteById(user.getOpenId());
		}
		return user;
	}
}
