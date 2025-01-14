package br.laemcasa.tasks_manager.repositories;

import br.laemcasa.tasks_manager.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> getUserById(UUID id);
    Optional<User> getUserByEmail(String email);

}
