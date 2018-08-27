package com.provectus.demo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.demo.dto.UserDto;
import com.provectus.demo.entities.Role;
import com.provectus.demo.repositories.RoleRepository;
import com.provectus.demo.services.UserService;

@RestController
public class UserRestController
{
	@Autowired RoleRepository roleRepository;
	@Autowired UserService userService;

	@GetMapping("/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}

	@GetMapping("/roles")
	public List<Role> findAllRoles()
	{
		return roleRepository.findAll();
	}

	@PutMapping("/role/{name}")
	public Role saveRole(@PathVariable String name)
	{
		return roleRepository.save(new Role(name));
	}

	@GetMapping("/user")
	public UserDto getCurrentUser(Principal principal)
	{
		return userService.getCurrentUser(principal);
	}

	@GetMapping("/user/{id}")
	public UserDto getUserById(@PathVariable String id)
	{
		return userService.getUserById(id);
	}

	@GetMapping("/users")
	public List<UserDto> findAllUsers()
	{
		return userService.getAllUsers();
	}

	@PostMapping("/user")
	public UserDto create(@RequestBody UserDto user)
	{
		return userService.createUser(user);
	}

	@PutMapping("/user")
	public UserDto update(@RequestBody UserDto user){
		return userService.update(user);
	}

	@DeleteMapping(path ={"/user/{id}"})
	public UserDto delete(@PathVariable("id") String id) {
		return userService.delete(id);
	}
}
