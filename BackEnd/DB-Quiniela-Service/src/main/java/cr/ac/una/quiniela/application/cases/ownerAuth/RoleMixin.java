package cr.ac.una.quiniela.application.cases.ownerAuth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class RoleMixin {

	@JsonCreator
	public RoleMixin(@JsonProperty("authorities") String nombre) {
	}

}
