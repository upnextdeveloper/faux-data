package com.upnextdev.datagen;

import java.util.Locale;
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
//		System.out.println(faker.aviation().airport());
//		System.out.println(faker.aviation().aircraft());
//		System.out.println(faker.name().firstName());
//		System.out.println(faker.name().lastName());
//		System.out.println(faker.name().fullName());
//		System.out.println(faker.name().nameWithMiddle());
//		System.out.println(faker.name().username());
//		System.out.println(faker.name().bloodGroup());
//		System.out.println(faker.finance().creditCard());
	}

}
