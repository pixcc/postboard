package ru.postboard.service;

import org.springframework.stereotype.Service;
import ru.postboard.domain.Post;
import ru.postboard.domain.Role;
import ru.postboard.domain.Tag;
import ru.postboard.domain.User;
import ru.postboard.form.UserCredentials;
import ru.postboard.repository.RoleRepository;
import ru.postboard.repository.TagRepository;
import ru.postboard.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    /** @noinspection FieldCanBeLocal, unused */
    private final RoleRepository roleRepository;
    private final TagRepository tagRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tagRepository = tagRepository;
        for (Role.Name name : Role.Name.values()) {
            if (roleRepository.countByName(name) == 0) {
                roleRepository.save(new Role(name));
            }
        }
    }

    public User register(UserCredentials userCredentials) {
        User user = new User();
        user.setLogin(userCredentials.getLogin());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), userCredentials.getLogin(), userCredentials.getPassword());
        return user;
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public User findById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }

    public void writePost(User user, Post post, String[] tagNames) {
        for (String name : tagNames) {
            Set<Tag> postTags = post.getTags();
            Tag tag = tagRepository.findByName(name);
            if (tag == null) {
                tag = new Tag(name);
                tagRepository.save(tag);
            }
            postTags.add(tag);
        }
        user.addPost(post);
        userRepository.save(user);
    }
}
