package it.socepi.integration.marketplace.amazon;

import it.socepi.integration.marketplace.amazon.model.Article;
import it.socepi.integration.marketplace.amazon.model.Order;
import it.socepi.integration.marketplace.amazon.model.State;
import it.socepi.integration.marketplace.amazon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class AmazonApplication implements CommandLineRunner {

	@Autowired
	private OrderService orderService;


	public static void main(String[] args) {
		SpringApplication.run(AmazonApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		Article article = new Article(1,"12","4323","articolo1","black ",new BigDecimal(3),new BigDecimal(3));
//		Article article2 = new Article(3,"8","4345","articolo2","white ",new BigDecimal(2),new BigDecimal(5));
//		Article article3 = new Article(5,"3","4333","articolo3","green",new BigDecimal(4),new BigDecimal(8));
//
//
//		String sDate1 = "19/4/2022";
//		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
//
//		String sDate2 = "18/4/2022";
//		Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
//
//		String sDate3 = "17/4/2022";
//		Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate3);
//
//		Order order = new Order("Order1","via Stelvio","1 cap","milano ","provincia1","123456789","324323",date1,new BigDecimal(2),new BigDecimal(2),new BigDecimal(2),"note1", State.PROCESSED,article);
//		Order order2 = new Order("Order2","via Stelvio","1 cap","Peruggia","provincia2 ","678909087","324044",date2,new BigDecimal(2),new BigDecimal(2),new BigDecimal(2),"note2" ,State.PENDING,article2);
//		Order order3 = new Order("Order3","via Stelvio","1 cap","Bari","provincia 3","123476354","324332",date3,new BigDecimal(2),new BigDecimal(2),new BigDecimal(2), "note3",State.ERROR,article3);
//
//
//		orderService.insertOrder(order);
//		orderService.insertOrder(order2);
//		orderService.insertOrder(order3);


	}
}


