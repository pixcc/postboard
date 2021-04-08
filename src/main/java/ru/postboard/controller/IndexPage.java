package ru.postboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.postboard.security.Guest;
import ru.postboard.service.PostService;

import javax.servlet.http.HttpSession;

@Controller
public class IndexPage extends Page {
    private final PostService postService;

    public IndexPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "IndexPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        unsetUser(httpSession);
        return "redirect:/";
    }
}
