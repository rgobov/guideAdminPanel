package gobov.roma.adminpanel.dto;

import gobov.roma.adminpanel.model.User.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class UserCreateDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    private String password;
    private Role role;
    private String language;
    private List<String> interests;
}