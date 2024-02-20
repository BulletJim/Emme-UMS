package com.emme;

import com.emme.entities.Role;
import com.emme.repositories.RoleRepository;
import com.emme.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmmeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmmeBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository){
		return args -> {

			roleRepository.save(new Role(1, "USER"));
			roleRepository.save(new Role(2, "TEACHER"));
			roleRepository.save(new Role(3, "ADMIN"));

		};
	}

}
