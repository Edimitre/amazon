package it.socepi.integration.marketplace.amazon;

import it.socepi.integration.marketplace.amazon.model.Article;
import it.socepi.integration.marketplace.amazon.model.Order;
import it.socepi.integration.marketplace.amazon.model.State;
import it.socepi.integration.marketplace.amazon.repository.ArticleRepository;
import it.socepi.integration.marketplace.amazon.repository.OrderRepository;
import it.socepi.integration.marketplace.amazon.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
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

	@Test
	public void testCreateOrder(){



		Article article = new Article(1,"12","4323","uno articolo","black ",new BigDecimal(3),new BigDecimal(3));


		Order order = new Order("Qualcosa Sia","via Stelvio","1 cap","milano ","provincia ","123456789","324323",new Date(),new BigDecimal(2),new BigDecimal(2),new BigDecimal(2), State.PENDING,article);


		Assertions.assertThat(orderRepository.save(order)).isInstanceOf(Order.class);

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
	public void getByCityName(){

		List<Order> orderList = orderRepository.findByCity("mil");

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