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

@RestController
public class UserController {

    CreateUserUseCase createUserUseCase;
    FindUserUseCase userUseCase;
    UpdateUserUseCase updateUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, FindUserUseCase userUseCase, UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.userUseCase = userUseCase;
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

    @GetMapping("api/v1/users/{promptuary}")
    public ResponseEntity<UserResponse> findUserByPromptuary(@PathVariable("promptuary") String promptuary){
        User user = userUseCase.findByPromptuary(promptuary).orElseThrow(
                () -> new GenericResourceException("User not found!", "User")
        );
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }
}
