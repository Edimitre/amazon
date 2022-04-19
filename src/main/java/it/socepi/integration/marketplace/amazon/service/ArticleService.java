package it.socepi.integration.marketplace.amazon.service;


import it.socepi.integration.marketplace.amazon.model.Article;
import it.socepi.integration.marketplace.amazon.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article insertArticle(Article article){

        return articleRepository.save(article);

    }

    public void deleteArticle(Article article) {

        articleRepository.delete(article);

    }

    public Article getArticleById(long id){

        return articleRepository.getById(id);
    }

    public List<Article> getAllArticles(){

        return articleRepository.findAll();
    }




}
