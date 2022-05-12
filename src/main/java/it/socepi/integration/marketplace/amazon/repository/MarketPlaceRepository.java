package it.socepi.integration.marketplace.amazon.repository;

import it.socepi.integration.marketplace.amazon.model.MarketPlace;
import it.socepi.integration.marketplace.amazon.model.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MarketPlaceRepository extends JpaRepository<MarketPlace,Long> {


    Optional<MarketPlace> findByName(String name);
}
