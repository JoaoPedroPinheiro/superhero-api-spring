package io.joaopinheiro.superhero;
import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SuperheroController {

	@GetMapping(path="superheroes",  produces = "application/json")
	public Collection<Superhero> getAllSuperheroes(){
		return null;
	}
	
	@GetMapping(path = "superheroes/{id}", produces = "application/json")
	public Superhero getSuperheroDetails(String id) {
		return null;
	}
	
	@PostMapping(path="superheroes", consumes = "application/json")
	public void createSuperhero(@RequestBody Superhero hero) {
		
	}
	
}
