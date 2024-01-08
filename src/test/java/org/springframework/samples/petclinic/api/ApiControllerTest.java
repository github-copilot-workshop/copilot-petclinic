package org.springframework.samples.petclinic.api;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ApiController.class)
public class ApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ApiController apiController;

	@Test
	public void testGetOwners() throws Exception {
		// Arrange
		List<Owner> owners = Arrays.asList(new Owner(), new Owner());
		when(apiController.getOwners()).thenReturn(owners);

		// Act & Assert
		mockMvc.perform(get("/api/owners")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));

		// Verify
		verify(apiController).getOwners();
	}

	@Test
	public void testGetPetsByName() throws Exception {
		// Arrange
		String name = "Fluffy";
		List<Map<String, Object>> pets = Arrays.asList(createPetMap("Fluffy", "Cat"), createPetMap("Fluffy", "Dog"));
		given(apiController.getPetsByName(name)).willReturn(pets);

		// Act & Assert
		mockMvc.perform(get("/api/pets/{name}", name))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].name", is("Fluffy")))
			.andExpect(jsonPath("$[0].type", is("Cat")))
			.andExpect(jsonPath("$[1].name", is("Fluffy")))
			.andExpect(jsonPath("$[1].type", is("Dog")));

		// Verify
		verify(apiController).getPetsByName(name);
	}

	private Map<String, Object> createPetMap(String name, String type) {
		Map<String, Object> petMap = new HashMap<>();
		petMap.put("name", name);
		petMap.put("type", type);
		return petMap;
	}

}
