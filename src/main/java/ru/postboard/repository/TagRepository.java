package ru.postboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.postboard.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
