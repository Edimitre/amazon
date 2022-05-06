package it.socepi.integration.marketplace.amazon;

import it.socepi.integration.marketplace.amazon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmazonApplication{

	@Autowired
	private OrderService orderService;


	public static void main(String[] args) {
		SpringApplication.run(AmazonApplication.class, args);
	}




}


