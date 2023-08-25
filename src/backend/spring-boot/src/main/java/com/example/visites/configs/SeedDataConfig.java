
package com.example.visites.configs;

import com.example.visites.dto.UserRequest;
import com.example.visites.models.Role;
import com.example.visites.models.User;
import com.example.visites.repositories.BureauRepository;
import com.example.visites.repositories.RoleRepository;
import com.example.visites.repositories.UserRepository;
import com.example.visites.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final BureauRepository bureauRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        try {
            if (roleRepository.count() < 2) {
                Role adminRole = new Role();
                adminRole.setId(AppConstants.ADMIN_ID);
                adminRole.setNom("ADMIN");
                adminRole.setDescription("Administrateur");

                Role userRole = new Role();
                userRole.setId(AppConstants.USER_ID);
                userRole.setNom("USER");
                userRole.setDescription("Utilisateur");

                List<Role> roles = List.of(adminRole, userRole);

                List<Role> savedRoles = roleRepository.saveAll(roles);

                savedRoles.forEach(System.out::println);
            }
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setPassword(passwordEncoder.encode("password"));
                admin.setNom("admin");
                admin.setPrenom("admin");
                admin.setEmail("admin@admin.com");
                admin.setSexe("masculin");
                admin.setDateNais(Date.valueOf("2000-01-01"));
                admin.setPoste("Administrateur");
                admin.setPassword("password");
                admin.setUsername("admin");
                admin.setTel("+237671234567");
                if (bureauRepository.count() > 0)
                    admin.setBureau(bureauRepository.findAll().get(0));
                if (roleRepository.count() > 0)
                    admin.setRoles(roleRepository.findByNomContaining("ADMIN"));

                userService.create(modelMapper.map(admin, UserRequest.class));
                log.debug("created ADMIN user - {}", admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

