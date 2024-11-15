package br.laemcasa.tasks_manager.controller;

import br.laemcasa.tasks_manager.domain.user.UserRegisterDTO;
import br.laemcasa.tasks_manager.dto.ApiResponse;
import br.laemcasa.tasks_manager.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody UserRegisterDTO data) {
        this.service.register(data);

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.CREATED.value(), "User created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
