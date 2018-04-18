package io.joaopinheiro.superhero;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	@ElementCollection(targetClass=String.class)
	private List<String> allies;
	
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

	public Date getFirstAppearance() {
		return firstAppearance;
	}

	public void setFirstAppearance(Date firstAppearance) {
		this.firstAppearance = firstAppearance;
	}

	@ElementCollection(targetClass=String.class)
	private List<String> superpowers;
	
	@Temporal(TemporalType.DATE)
	private Date firstAppearance;
}
