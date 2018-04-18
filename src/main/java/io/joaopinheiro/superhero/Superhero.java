package io.joaopinheiro.superhero;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "superhero")
public class Superhero {

	@Id
	private long id;
	
	
}
