package cr.ac.una.quiniela.application.cases.ownerAuth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class UsuarioMixin {

	@JsonCreator
	public UsuarioMixin(@JsonProperty("email") String correoElectronico, @JsonProperty("name") String nombre,
			@JsonProperty("firstSurname") String apellido1, @JsonProperty("secondSurname") String apellido2,
			@JsonProperty("birthDate") String fechaNacimiento, @JsonProperty("role") String idRol) {
	}

}
