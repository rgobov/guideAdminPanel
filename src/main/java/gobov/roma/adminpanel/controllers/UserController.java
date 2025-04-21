package gobov.roma.adminpanel.controllers;

import gobov.roma.adminpanel.dto.UserCreateDTO;
import gobov.roma.adminpanel.model.User;
import gobov.roma.adminpanel.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPasswordHash(userDTO.getPassword()); // В реальном приложении нужно хешировать
        user.setRole(userDTO.getRole());
        user.setLanguage(userDTO.getLanguage());
        user.setInterests(userDTO.getInterests());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(user));
    }

    @PutMapping("/{id}/interests")
    public ResponseEntity<User> updateInterests(
            @PathVariable Long id,
            @RequestBody List<String> interests) {
        User user = userService.updateInterests(id, interests);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}