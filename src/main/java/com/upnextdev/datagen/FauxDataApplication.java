package com.upnextdev.datagen;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.github.javafaker.Faker;

import net.datafaker.providers.base.Vehicle;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000", maxAge = 20)
public class FauxDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(FauxDataApplication.class, args);
		Faker f = new Faker(new Locale("en-US"));
		// multiple options
		System.out.println(f.options().option("Pass","Fail"));
		System.out.println(f.medical().hospitalName());
		System.out.println(f.finance().creditCard());
		System.out.println(f.aviation().airport());
		
		// datafaker version
		net.datafaker.Faker faker = new net.datafaker.Faker();
		System.out.println(faker.address().cityName());
		System.out.println(faker.address().state());
		Vehicle v = faker.vehicle();
		System.out.println(f.currency().name());
		System.out.println(faker.finance().creditCard());
		System.out.println(faker.transport());
	}

}
