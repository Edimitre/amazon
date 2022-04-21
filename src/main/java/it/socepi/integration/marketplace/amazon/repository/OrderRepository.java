package it.socepi.integration.marketplace.amazon.repository;

import it.socepi.integration.marketplace.amazon.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query(value="select  * from orders where data between '?1' and '?2'", nativeQuery = true)
    List<Order> findByData(Date date1,Date date2);

    List<Order> findAllByDataBetween(Date start, Date end);

    @Query("SELECT o FROM Order o WHERE o.citta LIKE %?1%")
    List<Order> findByCity(String keyword);

    @Query(value ="select * from orders where state = ?1", nativeQuery = true)
    List<Order> getByState(String state);
    @Query(value = "SELECT * FROM orders WHERE denominazione LIKE %?1% OR citta LIKE %?1% OR telefono LIKE %?1% OR state LIKE %?1%", nativeQuery = true)
    List<Order> getByKeyWord(String param1);


}
