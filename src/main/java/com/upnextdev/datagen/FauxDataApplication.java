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
	}
}
