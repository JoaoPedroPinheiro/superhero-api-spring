package io.joaopinheiro.superhero;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "superhero")
public class Superhero {

	@Id
	private long id;
	
	private String name;
	private String publisher;
	private String pseudonym;
	
	@OneToMany
	private List<Superhero> allies;
	
	@ElementCollection(targetClass=String.class)
	private List<String> superpowers;
	
	@Temporal(TemporalType.DATE)
	private Date firstAppearance;
}
