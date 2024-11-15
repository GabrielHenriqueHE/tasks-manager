package br.laemcasa.tasks_manager.controller;

import br.laemcasa.tasks_manager.domain.user.User;
import br.laemcasa.tasks_manager.domain.user.UserUpdateDTO;
import br.laemcasa.tasks_manager.dto.ApiResponse;
import br.laemcasa.tasks_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<User>>> findAllUsers() {
        var users = this.service.findAll();

        ApiResponse<List<User>> response = new ApiResponse<List<User>>(HttpStatus.OK.value(), null, users);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable UUID id, @RequestBody UserUpdateDTO data) {
        this.service.update(id, data);

        ApiResponse<Void> response = new ApiResponse<Void>(HttpStatus.OK.value(), "User updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        this.service.delete(id);

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "User has been deleted.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
