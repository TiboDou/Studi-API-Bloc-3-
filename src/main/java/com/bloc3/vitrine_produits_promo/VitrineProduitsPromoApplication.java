package com.bloc3.vitrine_produits_promo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) //le exclude pour pouvoir faire mes tests en toute liberté
public class VitrineProduitsPromoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitrineProduitsPromoApplication.class, args);
	}

}
