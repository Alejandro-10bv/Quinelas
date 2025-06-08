package cr.ac.una.quiniela.application.cases.ownerAuth;

import cr.ac.una.quiniela.application.infraestructure.owners.Usuario;
import cr.ac.una.quiniela.application.infraestructure.owners.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public void saveIfNotExists(Usuario usuario) {
        if (usuarioRepository.findByCorreoElectronico(usuario.getCorreoElectronico()).orElse(null) == null) {
            usuarioRepository.save(usuario);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(username).orElse(null);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }

}
