package ru.postboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.postboard.domain.Comment;
import ru.postboard.domain.Post;
import ru.postboard.security.Guest;
import ru.postboard.service.CommentService;
import ru.postboard.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {

    private PostService postService;
    private CommentService commentService;

    public PostPage(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Guest
    @GetMapping({"/post/{id}", "/post/"})
    public String postGet(@PathVariable(required = false) String id, Model model) {
        if (postService.isValidId(id)) {
            model.addAttribute("post", postService.find(Long.parseLong(id)));
            model.addAttribute("comment", new Comment());
        }
        return "PostPage";
    }


    @PostMapping({"/post/{id}", "/post/"})
    public String commentPost(@PathVariable(required = false) String id,
                              @Valid @ModelAttribute Comment comment,
                              BindingResult bindingResult,
                              HttpSession httpSession,
                              Model model) {

        if (postService.isValidId(id)) {
            Post post = postService.find(Long.parseLong(id));

            if (post != null) {
                model.addAttribute("post", post);
                if (!bindingResult.hasErrors()) {
                    comment.setUser(getUser(httpSession));
                    comment.setPost(post);
                    commentService.save(comment);

                    return "redirect:/post/{id}";
                }
            }
        }
        return "PostPage";
    }
}
