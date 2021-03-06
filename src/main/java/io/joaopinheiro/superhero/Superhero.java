package io.joaopinheiro.superhero;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "superhero")
public class Superhero {

	@Id
	private long id;
	
	private String name;
	private String publisher;
	private String pseudonym;
	
	@ElementCollection(targetClass=String.class)
	private List<String> allies;

	@ElementCollection(targetClass=String.class)
	private List<String> superpowers;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate firstAppearance;

	public Superhero(){};

	public Superhero(long id, String name, String publisher, String pseudonym, List<String> allies, List<String> superpowers, LocalDate firstAppearance) {
		this.id = id;
		this.name = name;
		this.publisher = publisher;
		this.pseudonym = pseudonym;
		this.allies = allies;
		this.superpowers = superpowers;
		this.firstAppearance = firstAppearance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public List<String> getAllies() {
		return allies;
	}

	public void setAllies(List<String> allies) {
		this.allies = allies;
	}

	public List<String> getSuperpowers() {
		return superpowers;
	}

	public void setSuperpowers(List<String> superpowers) {
		this.superpowers = superpowers;
	}

	public LocalDate getFirstAppearance() {
		return firstAppearance;
	}

	public void setFirstAppearance(LocalDate firstAppearance) {
		this.firstAppearance = firstAppearance;
	}



	@Override
	public boolean equals(Object obj){
		if(obj instanceof Superhero){
			return (this.id == ((Superhero) obj).getId() &&
					this.name.equals(((Superhero) obj).name) &&
					this.pseudonym.equals(((Superhero) obj).getPseudonym()) &&
					this.superpowers.equals(((Superhero) obj).getSuperpowers()) &&
					this.allies.equals(((Superhero) obj).getAllies()) &&
					this.publisher.equals(((Superhero) obj).getPublisher())&&
					this.firstAppearance.equals(((Superhero) obj).getFirstAppearance()));
		} else return false;
	}
}
