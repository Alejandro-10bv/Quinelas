package cr.ac.una.quiniela.config;

import cr.ac.una.quiniela.application.cases.ownerAuth.UserDetailsServiceImpl;
import cr.ac.una.quiniela.application.infraestructure.owners.Role;
import cr.ac.una.quiniela.application.infraestructure.owners.RoleRepository;
import cr.ac.una.quiniela.application.infraestructure.owners.Roles;
import cr.ac.una.quiniela.application.infraestructure.owners.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class UserDataInitializar implements ApplicationRunner {
    private final UserDetailsServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role ADMIN_ROLE = roleRepository.findByNombre(Roles.ADMINISTRADOR).orElse(null);
        if (ADMIN_ROLE == null) roleRepository.save(new Role(null, Roles.ADMINISTRADOR));
        Role USER_ROLE = roleRepository.findByNombre(Roles.JUGADOR).orElse(null);
        if (USER_ROLE == null) roleRepository.save(new Role(null, Roles.JUGADOR));

        Usuario admin = new Usuario();
        admin.setNombre("Carlos");
        admin.setApellido1("Gonzalez");
        admin.setApellido2("Lopez");
        admin.setCorreoElectronico("admin@example.com");
        admin.setContrasenha(passwordEncoder.encode("admin123"));
        admin.setRol(ADMIN_ROLE);
        admin.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        usuarioService.saveIfNotExists(admin);

        Usuario user = new Usuario();
        user.setNombre("Juan");
        user.setApellido1("Perez");
        user.setApellido2("Lopez");
        user.setCorreoElectronico("usuario@example.com");
        user.setContrasenha(passwordEncoder.encode("usuario123"));
        user.setRol(USER_ROLE);
        user.setFechaNacimiento(LocalDate.of(1995, 1, 1));
        usuarioService.saveIfNotExists(user);
    }
}
