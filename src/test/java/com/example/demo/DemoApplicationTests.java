package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		String javaVersion = System.getProperty("java.version");
		System.out.println("==========================================");
		System.out.println("Running tests on Java runtime version: " + javaVersion);
		System.out.println("==========================================");
		assertNotNull(javaVersion);
	}

}
