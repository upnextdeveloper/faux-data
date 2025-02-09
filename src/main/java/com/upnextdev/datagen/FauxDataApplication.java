package com.upnextdev.datagen;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.github.javafaker.Faker;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000")
public class FauxDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(FauxDataApplication.class, args);
		Faker faker = new Faker(new Locale("en-US"));
		System.out.println(faker.date().future(1, TimeUnit.HOURS));
		System.out.println(faker.date().birthday());
		System.out.println(faker.address().city());
		System.out.println(faker.demographic().sex());
		System.out.println(faker.phoneNumber().cellPhone());
	}

}
