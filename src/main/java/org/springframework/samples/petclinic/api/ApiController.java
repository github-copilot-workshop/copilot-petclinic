package org.springframework.samples.petclinic.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/owners", produces = "application/json")
	public List<Owner> getOwners() {
		List<Owner> ownerList = jdbcTemplate.query("select id, first_name, last_name from owners", (rs, rowNum) -> {
			Owner o = new Owner();
			o.setId(rs.getInt("id"));
			o.setFirstName(rs.getString("first_name"));
			o.setLastName(rs.getString("last_name"));
			return o;
		}).stream().toList();
		return ownerList;
	}

	@RequestMapping(value = "/owners/{ownerId}/pets", produces = "application/json")
	public List<Pet> getPetsByOwnerId(@PathVariable("ownerId") int ownerId) {
		List<Pet> pets = jdbcTemplate
			.query("select id, name, birth_date from pets where owner_id = " + ownerId, (rs, rowNum) -> {
				Pet p = new Pet();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setBirthDate(rs.getDate("birth_date").toLocalDate());
				return p;
			})
			.stream()
			.toList();
		return pets;
	}

	/**
	 * Retrieves a list of pets with the given name.
	 * @param name the name of the pets to retrieve
	 * @return a list of pets with the given name
	 */
	@RequestMapping(value = "/pets/{name}", produces = "application/json") 
	public List<Map<String, Object>> getPetsByName(@PathVariable("name") String name) { 
		try {
			List<Map<String, Object>> pets = jdbcTemplate // Query the database for pets
															// with the given name
				.queryForList("select id, name, birth_date from pets where name = ?", name);
			return pets; // Return the list of pets
		}
		catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage()); 
			e.printStackTrace(); // Print stack trace for the exception
			return Collections.emptyList(); // Return an empty list if an exception occurs
		}
	}

}
