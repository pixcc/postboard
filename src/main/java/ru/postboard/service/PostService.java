package ru.postboard.service;

import org.springframework.stereotype.Service;
import ru.postboard.domain.Post;
import ru.postboard.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post find(Long id) {
        return id == null ? null : postRepository.findById(id).orElse(null);
    }

    public boolean isValidId(String idStr) {
        try {
            Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
