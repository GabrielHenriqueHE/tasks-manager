package br.laemcasa.tasks_manager.services;

import br.laemcasa.tasks_manager.domain.user.User;
import br.laemcasa.tasks_manager.domain.user.UserRegisterDTO;
import br.laemcasa.tasks_manager.exceptions.UserAlreadyExistsException;
import br.laemcasa.tasks_manager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public void register(UserRegisterDTO data) {
        var optionalUser = repository.getUserByEmail(data.email().toLowerCase());

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("Email is already in use.");
        }

        User user = new User(data.email().toLowerCase(), data.username(), data.password());

        this.repository.save(user);
    }

}
