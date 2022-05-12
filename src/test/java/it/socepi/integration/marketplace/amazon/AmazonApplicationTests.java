package it.socepi.integration.marketplace.amazon;

import it.socepi.integration.marketplace.amazon.model.*;
import it.socepi.integration.marketplace.amazon.repository.ArticleRepository;
import it.socepi.integration.marketplace.amazon.repository.NationalityRepository;
import it.socepi.integration.marketplace.amazon.repository.OrderRepository;
import it.socepi.integration.marketplace.amazon.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class AmazonApplicationTests {


	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private NationalityRepository nationalityRepository;
	@Test
	public void testCreateOrder(){

		List<Order> orderList = new ArrayList<>();



		MarketPlace marketPlace = new MarketPlace("italiaMarketPlace", new Nationality("Italia"));

		Article article = new Article("SKU",1,"12","4323","uno articolo","black ",new BigDecimal(4),new BigDecimal(3),new BigDecimal(3),new Order());

		List<Article> articleList = new ArrayList<>();
		articleList.add(article);


		Order order = new Order();

		order.setDenominazione("denominazione");
		order.setVia("Via Dagosto");
		order.setCap("cap");
		order.setProvincia("Bari");
		order.setTelefono("08989897");
		order.setCodice("8767");
		order.setIva(new BigDecimal(7));
		order.setData(new Date());
		order.setTotale(new BigDecimal(50));
		order.setSubTotale(new BigDecimal(8));
		order.setNotes("orderNotes");
		order.setArticleList(articleList);
		order.setMarketPlace(marketPlace);






		Assertions.assertThat(orderRepository.save(order)).isInstanceOf(Order.class);

	}

	@Test
	public void testGetNationality(){


		Assertions.assertThat(nationalityRepository.getByName("Italy").get()).isInstanceOf(Nationality.class);
	}

	@Test
	public void testGetOrderById(){

		Optional<Order> order= orderRepository.findById(1L);

		order.ifPresent(value -> Assertions.assertThat(value).isInstanceOf(Order.class));

	}

	@Test
	public void getOrders(){

		List<Order> orderList = orderRepository.findAll();

		Assertions.assertThat(orderList.isEmpty()).isFalse();

	}

	@Test
	public void deleteById(){

		Order order = new Order();
		order.setId(10L);

		orderRepository.deleteById(order.getId());

		Assertions.assertThat(order).isInstanceOf(Order.class);

	}

	@Test
	public void testCreateArticle(){

		String test= "test";

//		Article article = new Article(1, 1L,test,test,test,test,test,test);
//
//		Assertions.assertThat(articleRepository.save(article)).isInstanceOf(Article.class);

	}

	@Test
	public void testGetArticleById(){

		Optional<Article> article = articleRepository.findById(2L);

		article.ifPresent(value -> Assertions.assertThat(value).isInstanceOf(Article.class));
	}

	@Test
	public void getAllArticles(){

		List<Article> articleList = articleRepository.findAll();

		Assertions.assertThat(articleList.isEmpty()).isFalse();

	}



	@Test
	public void getByAny(){

		List<Order> orderList = orderRepository.getByKeyWord("mil");

		Assertions.assertThat(orderList.isEmpty()).isFalse();

	}

	@Test
	public void getProcessed(){

		List<Order> orderList = orderRepository.getByState("PROCESSED");

		Assertions.assertThat(orderList.isEmpty()).isFalse();

	}

	@Test
	public void getByDate(){

		List<Order> orderList = orderRepository.findByData(new Date(),new Date());

		Assertions.assertThat(orderList.isEmpty()).isTrue();

	}


}
