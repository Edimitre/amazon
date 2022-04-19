package it.socepi.integration.marketplace.amazon.service;

import it.socepi.integration.marketplace.amazon.model.Order;
import it.socepi.integration.marketplace.amazon.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    public Order insertOrder(Order order){

        return orderRepository.save(order);

    }

    public void deleteOrder(Order order) {

        orderRepository.delete(order);

    }

    public void deleteOrderById(Long id){

        orderRepository.deleteById(id);
    }

    public Order getOrderById(long id){

        return orderRepository.findById(id).get();
    }

    public List<Order> getAllOrders(){

        return orderRepository.findAll();
    }

    public List<Order> getByCityName(String cityName){

        return orderRepository.findByCity(cityName);
    }

    public List<Order> getProcessed(){

        return orderRepository.getByState("PROCESSED");
    }

    public List<Order> getWithError(){

        return orderRepository.getByState("ERROR");
    }

    public List<Order> getPending(){

        return orderRepository.getByState("PENDING");
    }

    public List<Order> getByDate(Date date1, Date date2){

        return orderRepository.findByData(date1, date2);

    }

    public List<Order> getBetweenDate(Date date1, Date date2){

        return orderRepository.findAllByDataBetween(date1,date2);

    }



}
