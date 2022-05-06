package it.socepi.integration.marketplace.amazon.service;

import it.socepi.integration.marketplace.amazon.model.*;
import it.socepi.integration.marketplace.amazon.repository.ArticleRepository;
import it.socepi.integration.marketplace.amazon.repository.MarketPlaceRepository;
import it.socepi.integration.marketplace.amazon.repository.NationalityRepository;
import it.socepi.integration.marketplace.amazon.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MarketPlaceRepository marketPlaceRepository;


    public Order insertOrder(Order order) {

        return orderRepository.save(order);

    }

    public void deleteOrder(Order order) {

        orderRepository.delete(order);

    }

    public void deleteOrderById(Long id) {

        orderRepository.deleteById(id);
    }

    public Order getOrderById(long id) {

        return orderRepository.findById(id).get();
    }

    @Transactional
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }


    public List<Order> getProcessed() {

        return orderRepository.getByState("PROCESSED");
    }

    public List<Order> getWithError() {

        return orderRepository.getByState("ERROR");
    }

    public List<Order> getPending() {

        return orderRepository.getByState("PENDING");
    }


    public List<Order> getBetweenDate(Date date1, Date date2) {

        return orderRepository.findAllByDataBetween(date1, date2);

    }


    @PostConstruct
    private void setInitialData() {

        List<MarketPlace> marketPlaceList = marketPlaceRepository.findAll();

        if (marketPlaceList.isEmpty()) {

            System.out.println("Initial data is empty");
            Nationality nationality = new Nationality();
            nationality.setName("Italia");


            MarketPlace marketPlace = new MarketPlace();

            marketPlace.setName("ItaliaMarketPlace");
            marketPlace.setNationality(nationality);




            Article article = new Article();
            article.setQuantita(1);
            article.setPartNumber("1");
            article.setNomeVarianteArticolo("nomevariante");
            article.setNomeArticolo("articolo");
            article.setCodiceVarianteArticolo("codice");
            article.setPrezzoUnitario(new BigDecimal(9));
            article.setIva(new BigDecimal(3));

            Article article2 = new Article();
            article2.setQuantita(5);
            article2.setPartNumber("5");
            article2.setNomeVarianteArticolo("nomesecondo");
            article2.setNomeArticolo("articolo2");
            article2.setCodiceVarianteArticolo("codice2");
            article2.setPrezzoUnitario(new BigDecimal(67));
            article2.setIva(new BigDecimal(43));




            List<Order> orderList = new ArrayList<>();


            List<Article> articleList = new ArrayList<>();


            articleList.add(article);
            articleList.add(article2);


            Order order = new Order();
            order.setDenominazione("denominazione");
            order.setVia("Via Dagosto");
            order.setCap("cap");
            order.setProvincia("provincia");
            order.setTelefono("08989897");
            order.setCodice("8767");
            order.setIva(new BigDecimal(7));
            order.setData(new Date());
            order.setTotale(new BigDecimal(50));
            order.setSubTotale(new BigDecimal(8));
            order.setNotes("orderNotes");
            order.setArticleList(articleList);
            order.setMarketPlace(marketPlace);
            order.setState(State.PROCESSED);
            order.setCitta("Milano");



            article.setOrder(order);
            article2.setOrder(order);

            orderRepository.save(order);







            Nationality nationality2 = new Nationality();
            nationality2.setName("Spain");


            MarketPlace marketPlace2 = new MarketPlace();

            marketPlace2.setName("SpainMarketPlace");
            marketPlace2.setNationality(nationality2);




            Article article3 = new Article();
            article3.setQuantita(5);
            article3.setPartNumber("5");
            article3.setNomeVarianteArticolo("nomesecondo");
            article3.setNomeArticolo("articolo2");
            article3.setCodiceVarianteArticolo("codice2");
            article3.setPrezzoUnitario(new BigDecimal(67));
            article3.setIva(new BigDecimal(43));




            List<Article> articleList2 = new ArrayList<>();


            articleList2.add(article3);







            Order order2 = new Order();
            order2.setDenominazione("denom");
            order2.setVia("Via Libera");
            order2.setCap("cap2");
            order2.setProvincia("provincia2");
            order2.setTelefono("9087674");
            order2.setCodice("8564");
            order2.setIva(new BigDecimal(10));
            order2.setData(new Date());
            order2.setTotale(new BigDecimal(40));
            order2.setSubTotale(new BigDecimal(3));
            order2.setNotes("altre note");
            order2.setArticleList(articleList2);
            order2.setMarketPlace(marketPlace2);
            order2.setState(State.PENDING);
            article3.setOrder(order2);
            order2.setCitta("Torino");


            orderRepository.save(order2);

            System.out.println("inserted initial data");

        }else{
            System.out.println("initial data exist ..bypassing ");
        }

    }


    public List<Order> getByKeyWord(String keyword) {
        return orderRepository.getByKeyWord(keyword);
    }


}
