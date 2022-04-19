package it.socepi.integration.marketplace.amazon.controller;


import it.socepi.integration.marketplace.amazon.model.Order;
import it.socepi.integration.marketplace.amazon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @PostMapping("/save")
    private Order saveOrder(@RequestBody Order order){
        return orderService.insertOrder(order);

    }


    @GetMapping("/all")
    private List<Order> getAllOrders(){

        return orderService.getAllOrders();
    }

    @GetMapping("/processed")
    private List<Order> getAllProcessedOrders(){

        return orderService.getProcessed();
    }

    @GetMapping("/pending")
    private List<Order> getAllPendingOrders(){

        return orderService.getPending();
    }

    @GetMapping("/error")
    private List<Order> getAllErrorOrders(){

        return orderService.getWithError();
    }

    @GetMapping("/get/{id}")
    private Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/get/date")
    private List<Order> getOrderByDate(@RequestParam(value="dataDa") @DateTimeFormat(pattern="yyyy-MM-dd") Date dataDa,
                                       @RequestParam(value="dataFine") @DateTimeFormat(pattern="yyyy-MM-dd") Date dataFine) throws ParseException {

        return orderService.getBetweenDate(dataDa,dataFine);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteOrderById(@PathVariable Long id){

        if (id!=null){
            orderService.deleteOrderById(id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
