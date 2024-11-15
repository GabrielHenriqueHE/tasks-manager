package br.laemcasa.tasks_manager.services;

import br.laemcasa.tasks_manager.domain.user.User;
import br.laemcasa.tasks_manager.domain.user.UserUpdateDTO;
import br.laemcasa.tasks_manager.exceptions.UserNotFoundException;
import br.laemcasa.tasks_manager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return this.repository.findAll();
    }

    @Transactional
    public void update(UUID id, UserUpdateDTO data) {
        var optionalUser = this.repository.getUserById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }

        var user = optionalUser.get();

        if (data.username() != null && !data.username().isEmpty()) {
            user.setUsername(data.username().toLowerCase());
        }

        if (data.password() != null && !data.password().isEmpty()) {
            user.setPassword(data.password());
        }

        this.repository.save(user);
    }

    @Transactional
    public void delete(UUID id) {
        var optionalUser = this.repository.getUserById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }

        var user = optionalUser.get();

        this.repository.delete(user);
    }

}
