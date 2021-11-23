package com.devsuperior.movieflix.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoleTests {

	@Test
	void roleShouldHaveCorrectStructure() {
	
		Role entity = new Role();
		entity.setId(1L);
		entity.setAuthority("ROLE_MEMBER");
	
		Assertions.assertNotNull(entity.getId());
		Assertions.assertNotNull(entity.getAuthority());
	}
}
