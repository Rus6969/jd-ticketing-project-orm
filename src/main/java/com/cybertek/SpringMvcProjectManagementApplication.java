package com.cybertek;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class SpringMvcProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcProjectManagementApplication.class, args);
	}

	/*
	after we added model mapper dependency for conversion DTO to ENTITIY and Entity to dto ,
	we need we Bean of it we need use object from mapper , 2 option :
	1. create config class and add @Bean annotation third party beans
	2. we can create it here in runner class
	 */


	@Bean
  public ModelMapper modelMapper(){

		return new ModelMapper();
  }



}
