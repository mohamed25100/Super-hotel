package fr.fms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperHotelApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SuperHotelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataHotel();
	}

	private void dataHotel() {
		System.out.println("tout va bien");
	}
}
