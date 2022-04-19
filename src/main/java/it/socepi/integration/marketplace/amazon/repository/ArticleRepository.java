package it.socepi.integration.marketplace.amazon.repository;

import it.socepi.integration.marketplace.amazon.model.Article;
import it.socepi.integration.marketplace.amazon.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {





}
