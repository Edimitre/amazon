package it.socepi.integration.marketplace.amazon.repository;

import it.socepi.integration.marketplace.amazon.model.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalityRepository extends JpaRepository<Nationality, Long> {

}
