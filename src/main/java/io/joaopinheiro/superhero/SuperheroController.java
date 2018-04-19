package io.joaopinheiro.superhero;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SuperheroController {

	@Autowired
	SuperheroRepository repository;

	@GetMapping(path="superheroes",  produces = "application/json")
	public Collection<Superhero> getAllSuperheroes(){
		return repository.findAll();
	}
	
	@GetMapping(path = "superheroes/{id}", produces = "application/json")
	public Superhero getSuperheroDetails(Long id) {
		Optional<Superhero> result = repository.findById(id);
		if(result.isPresent()){
			return result.get();
		} else {
			//TODO: Build Error message as a response value. Use .orElseThrow(...)
			return null;
		}
	}

	@GetMapping(path ="superheroes/{id}/allies", produces = "application/json")
	public List<String> getSuperheroAllies(Long id){
		Optional<Superhero> result = repository.findById(id);

		if(result.isPresent()){
			return result.get().getAllies();
		} else return null;
	}

	@PostMapping(path="superheroes", consumes = "application/json")
	public Superhero createSuperhero(@RequestBody Superhero hero) {
		repository.save(hero);
		return getSuperheroDetails(hero.getId());
	}
	
}
