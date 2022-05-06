package it.socepi.integration.marketplace.amazon.repository;

import it.socepi.integration.marketplace.amazon.model.MarketPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketPlaceRepository extends JpaRepository<MarketPlace,Long> {
}
