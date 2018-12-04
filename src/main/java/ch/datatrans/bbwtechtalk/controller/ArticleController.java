package ch.datatrans.bbwtechtalk.controller;

import ch.datatrans.bbwtechtalk.domain.Article;
import ch.datatrans.bbwtechtalk.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
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
    //@ModelAttribute("articles")
    public void getArticles(HttpServletRequest request, Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("webhookUrl", getUrl(request, "listener"));
        model.addAttribute("successUrl", getUrl(request, "success"));
        model.addAttribute("errorUrl", getUrl(request, "error"));
        model.addAttribute("cancelUrl", getUrl(request, "cancel"));
    }

    private String getUrl(HttpServletRequest request, String type) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/payment/" + type;
    }

}
