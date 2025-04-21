package gobov.roma.adminpanel.services;

import gobov.roma.adminpanel.dto.AdminRegistrationDTO;
import gobov.roma.adminpanel.model.auth.Admin;
import gobov.roma.adminpanel.repository.auth.AdminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found: " + username));
        return new org.springframework.security.core.userdetails.User(
                admin.getUsername(),
                admin.getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + admin.getRole().name()))
        );
    }

    public Admin registerAdmin(AdminRegistrationDTO dto) {
        if (adminRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (adminRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        Admin admin = new Admin();
        admin.setUsername(dto.getUsername());
        admin.setEmail(dto.getEmail());
        admin.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        admin.setRole(Admin.Role.ADMIN);
        return adminRepository.save(admin);
    }

    public Admin updateLastLogin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        admin.setLastLogin(LocalDateTime.now());
        return adminRepository.save(admin);
    }

    @PostConstruct
    public void initAdminUser() {
        String adminUsername = "admin";
        String adminEmail = "admin@example.com";
        String adminPassword = "admin123"; // Пароль будет зашифрован

        // Проверяем, существует ли администратор с именем "admin"
        if (adminRepository.findByUsername(adminUsername).isEmpty()) {
            AdminRegistrationDTO adminDto = new AdminRegistrationDTO();
            adminDto.setUsername(adminUsername);
            adminDto.setEmail(adminEmail);
            adminDto.setPassword(adminPassword);

            registerAdmin(adminDto);
            System.out.println("Admin user created with username: " + adminUsername);
        } else {
            System.out.println("Admin user already exists with username: " + adminUsername);
        }
    }
}