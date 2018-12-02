package ch.datatrans.bbwtechtalk.service;

import ch.datatrans.bbwtechtalk.domain.Article;
import ch.datatrans.bbwtechtalk.domain.ArticleRepository;
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
