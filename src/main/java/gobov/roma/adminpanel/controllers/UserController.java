package gobov.roma.adminpanel.controllers;

import gobov.roma.mvpguide.model.User;
import gobov.roma.mvpguide.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}/interests")
    public ResponseEntity<User> updateInterests(
            @PathVariable Long id,
            @RequestBody List<String> interests) {
        User user = userService.updateInterests(id, interests);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}