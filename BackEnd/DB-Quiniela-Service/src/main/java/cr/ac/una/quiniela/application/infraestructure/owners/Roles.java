package cr.ac.una.quiniela.application.infraestructure.owners;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Roles {

	ADMINISTRADOR, JUGADOR;

	@JsonValue
	public String getAuthority() {
		return "ROLE_" + this.name();
	}

}
