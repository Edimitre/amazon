package it.socepi.integration.marketplace.amazon.repository;

import it.socepi.integration.marketplace.amazon.model.Nationality;
import it.socepi.integration.marketplace.amazon.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NationalityRepository extends JpaRepository<Nationality, Long> {

    @Query(value ="select * from nationality_table where name = ?1", nativeQuery = true)
    Optional<Nationality> getByName(String name);

}
