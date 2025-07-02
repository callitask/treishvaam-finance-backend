package com.treishvaam.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class FinanceBackendAppApplication implements CommandLineRunner {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(FinanceBackendAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// This method runs once the application starts.
		// It's useful for initial setup or debugging.

		// Optional: Print key environment variables for debugging purposes.
		System.out.println("DEBUG: DB_URL = " + env.getProperty("DB_URL"));
		System.out.println("DEBUG: MAIL_HOST = " + env.getProperty("MAIL_HOST"));
		// System.out.println("DEBUG: JWT_SECRET = " + env.getProperty("JWT_SECRET")); // Caution: Sensitive!
		// System.out.println("DEBUG: MAIL_USERNAME = " + env.getProperty("MAIL_USERNAME"));
	}
}