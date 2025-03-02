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
		Faker f = new Faker();
		System.out.println(f.music().instrument());
	}

}
