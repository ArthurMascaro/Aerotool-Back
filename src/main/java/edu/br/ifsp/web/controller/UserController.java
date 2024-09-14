package edu.br.ifsp.web.controller;

import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.user.CreateUserUseCase;
import edu.br.ifsp.domain.usecases.user.FindUserUseCase;
import edu.br.ifsp.domain.usecases.user.UpdateUserUseCase;
import edu.br.ifsp.web.exception.GenericResourceException;
import edu.br.ifsp.web.model.user.request.UserRequest;
import edu.br.ifsp.web.model.user.request.UserUpdateRequest;
import edu.br.ifsp.web.model.user.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class UserController {

    CreateUserUseCase createUserUseCase;
    FindUserUseCase findUserUseCase;
    UpdateUserUseCase updateUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, FindUserUseCase findUserUseCase, UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.findUserUseCase = findUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest){
        User user = createUserUseCase.insert(userRequest.toUser());
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }

    @PatchMapping("api/v1/users/update/{promptuary}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdateRequest updateRequest,
                                                   @PathVariable String promptuary){
        User user = updateUserUseCase.update(updateRequest.toUser(promptuary));
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }

    @GetMapping("api/v1/users/promptuary/{promptuary}")
    public ResponseEntity<UserResponse> findUserByPromptuary(@PathVariable("promptuary") String promptuary){
        User user = findUserUseCase.findByPromptuary(promptuary).orElseThrow(
                () -> new GenericResourceException("User not found!", "User")
        );
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }

    @GetMapping("api/v1/users/id/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable UUID id){
        User user = findUserUseCase.findById(id).orElseThrow(
                () -> new GenericResourceException("User not found!", "User")
        );
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }

    @GetMapping("api/v1/users")
    public ResponseEntity<List<UserResponse>> findAllUsers(){
        List<User> users = findUserUseCase.findAll();
        return ResponseEntity.ok(users.stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList()));
    }
}
