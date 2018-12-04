package ch.datatrans.bbwtechtalk.controller;

import ch.datatrans.bbwtechtalk.domain.Article;
import ch.datatrans.bbwtechtalk.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * @author dominik.mengelt@gmail.com
 */
@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/articles/";
    }

    @GetMapping("/articles")
    @ModelAttribute("articles")
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

}
