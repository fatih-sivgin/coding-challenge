package de.sivgin.coding_challenge;

import org.springframework.boot.SpringApplication;

public class TestCodingChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.from(CodingChallengeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
