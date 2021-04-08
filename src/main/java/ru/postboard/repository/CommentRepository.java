package ru.postboard.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.postboard.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
