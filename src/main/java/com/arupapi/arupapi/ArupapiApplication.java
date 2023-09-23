package com.arupapi.arupapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class ArupapiApplication {

	// @Autowired
	// private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(ArupapiApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	System.out.println(this.passwordEncoder.encode("xyz"));
	// }

}
