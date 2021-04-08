package ru.postboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.postboard.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    int countByName(Role.Name name);
}
