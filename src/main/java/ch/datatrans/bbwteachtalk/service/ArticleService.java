package ch.datatrans.bbwteachtalk.service;

import ch.datatrans.bbwteachtalk.domain.Article;
import ch.datatrans.bbwteachtalk.domain.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dominik.mengelt@gmail.com
 */
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
}
