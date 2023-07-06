package com.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

@SpringBootApplication
public class EmployeesCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesCrudApplication.class, args);
	}

}
