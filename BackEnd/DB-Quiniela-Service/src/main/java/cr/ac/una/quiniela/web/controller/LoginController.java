/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cr.ac.una.quiniela.web.controller;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import cr.ac.una.quiniela.application.infraestructure.owners.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for the token resource.
 *
 * @author Josh Cummings
 */
@RequiredArgsConstructor
@RestController
public class LoginController {

	private final JwtEncoder encoder;

	@PostMapping("/login")
	public String token(Authentication authentication) {
		Usuario user = (Usuario) authentication.getPrincipal();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Instant now = Instant.now();
		long expiry = 36000L;
		// @formatter:off
		String scope = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.expiresAt(now.plusSeconds(expiry))
				.subject(authentication.getName())
				.claim("id", user.getId())
				.claim("name", user.getNombre() + " " + user.getApellidos())
				.claim("birthDate", user.getFechaNacimiento().format(formatter))
				.claim("scope", scope)
				.build();
		// @formatter:on
		return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

}
