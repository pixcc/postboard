package ru.postboard.service;

import org.springframework.stereotype.Service;
import ru.postboard.domain.Comment;
import ru.postboard.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
