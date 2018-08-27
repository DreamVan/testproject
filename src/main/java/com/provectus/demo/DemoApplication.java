package com.provectus.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaadin.spring.annotation.EnableVaadin;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
@EnableVaadin
@EnableAutoConfiguration
public class DemoApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
	}
}
