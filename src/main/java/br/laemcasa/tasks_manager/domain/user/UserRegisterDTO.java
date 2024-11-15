package br.laemcasa.tasks_manager.domain.user;

import lombok.NonNull;

public record UserRegisterDTO(@NonNull String email, @NonNull String username, @NonNull String password) {
}
