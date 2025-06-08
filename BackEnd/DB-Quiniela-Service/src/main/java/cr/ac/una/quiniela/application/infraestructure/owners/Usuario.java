package cr.ac.una.quiniela.application.infraestructure.owners;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

	public Usuario(String correoElectronico, String contrasenha, String nombre, String apellido1, String apellido2,
			LocalDate fechaNacimiento, Role rol) {
		this.correoElectronico = correoElectronico;
		this.contrasenha = contrasenha;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
		this.rol = rol;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID", columnDefinition = "uniqueidentifier")
	private UUID id;

	@Column(name = "correo_electronico", nullable = false, length = 50, unique = true)
	private String correoElectronico;

	@Column(name = "contrasenha", nullable = false)
	@JsonIgnore
	private String contrasenha;

	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "apellido_1", nullable = false, length = 50)
	private String apellido1;

	@Column(name = "apellido_2", nullable = false, length = 50)
	private String apellido2;

	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_rol", nullable = false)
	private Role rol;

	@JsonIgnore
	public String getApellidos() {
		return apellido1 + " " + apellido2;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(rol.getNombre().name());
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return getCorreoElectronico();
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return getContrasenha();
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

}
