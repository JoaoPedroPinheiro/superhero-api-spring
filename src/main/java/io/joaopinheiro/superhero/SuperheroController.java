package io.joaopinheiro.superhero;
import java.util.Collection;
import java.util.List;

import io.joaopinheiro.superhero.errors.SuperheroAlreadyExists;
import io.joaopinheiro.superhero.errors.SuperheroNotFound;
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
		Superhero result = repository.findById(id).orElseThrow(()-> new SuperheroNotFound("The Superhero with id : "+ id + " was not found"));;
		return result;

	}

	@GetMapping(path ="superheroes/{id}/allies", produces = "application/json")
	public List<String> getSuperheroAllies(@PathVariable("id") Long id){
		Superhero result = repository.findById(id).orElseThrow(()-> new SuperheroNotFound("The Superhero with id : "+ id + " was not found"));
		return result.getAllies();
	}

	@PostMapping(path="superheroes", consumes = "application/json")
	public Superhero createSuperhero(@RequestBody Superhero hero) {
		Long id = hero.getId();
		if(id != null){
			if(repository.findById(id).isPresent()){
				throw new SuperheroAlreadyExists("A Superhero with the given id("+id+") already exists");
			}
		}

		return repository.save(hero);
	}

	@PutMapping(path = "superheroes/{id}", consumes = "application/json")
	public Superhero updateSuperhero(@RequestBody Superhero hero, @PathVariable("id") Long id){
		repository.findById(id).orElseThrow(()-> new SuperheroNotFound("The Superhero with id : "+ id + " was not found"));
		return repository.save(hero);
	}
	
}
