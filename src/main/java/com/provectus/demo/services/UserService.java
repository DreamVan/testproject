package com.provectus.demo.services;

import java.security.Principal;
import java.util.List;

import com.provectus.demo.dto.UserDto;
import com.provectus.demo.entities.User;

public interface UserService
{
	void saveUser(User user);

	List<UserDto> getAllUsers();

	UserDto getUserById(String id);

	UserDto getCurrentUser(Principal principal);

	UserDto createUser(UserDto user);

	UserDto update(UserDto user);

	UserDto delete(String id);
}
