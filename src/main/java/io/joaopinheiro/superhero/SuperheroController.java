package io.joaopinheiro.superhero;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public Superhero getSuperheroDetails(@PathVariable("id") Long id) {
		Optional<Superhero> result = repository.findById(id);
		if(result.isPresent()){
			return result.get();
		} else {
			//TODO: Build Error message as a response value. Use .orElseThrow(...)
			return null;
		}
	}

	@GetMapping(path ="superheroes/{id}/allies", produces = "application/json")
	public List<String> getSuperheroAllies(@PathVariable("id") Long id){
		Optional<Superhero> result = repository.findById(id);

		if(result.isPresent()){
			return result.get().getAllies();
		} else return null;
	}

	@PostMapping(path="superheroes", consumes = "application/json")
	public Superhero createSuperhero(@RequestBody Superhero hero) {
		Long id = hero.getId();

		Optional<Superhero> result = repository.findById(id);
		repository.save(hero);
		return getSuperheroDetails(hero.getId());
	}

	@PutMapping(path = "superheroes/{id}", consumes = "application/json")
	public void updateSuperhero(@RequestBody Superhero hero, @PathVariable("id") Long id){
		repository.findById(id).ifPresent(value -> repository.save(hero));
	}
	
}
