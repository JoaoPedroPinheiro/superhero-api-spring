package io.joaopinheiro.superhero;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Util class to generate Superhero objects for testing
 *
 * @author Joao Pedro Pinheiro
 */
public class SuperheroBuilder {

    private int id = 1;
    private String name = "default name";
    private String publisher = "default publisher";
    private String pseudonym = "default date";
    private List<String> allies = Arrays.asList("default ally 1", "default ally 2");
    private List<String> superpowers = Arrays.asList("default superpower 1", "default superpower 2");
    private LocalDate firstAppearance = LocalDate.now();

    public SuperheroBuilder(){};

    public SuperheroBuilder withID(int i){
        this.id = i;
        return this;
    }

    public SuperheroBuilder withName(String name){
        this.name = name;
        return this;
    }

    public SuperheroBuilder withPublisher(String publisher){
        this.publisher = publisher;
        return this;
    }

    public SuperheroBuilder withPseudonym(String pseudonym){
        this.pseudonym = pseudonym;
        return this;
    }

    public SuperheroBuilder withAllies(String ... allies){
        this.allies = Arrays.asList(allies);
        return this;
    }

    public SuperheroBuilder withSuperpowers(String ... superpowers){
        this.allies = Arrays.asList(superpowers);
        return this;
    }

    public SuperheroBuilder withfirstAppearance(LocalDate date){
        this.firstAppearance = date;
        return this;
    }

    public Superhero build(){
        return new Superhero(id, name, publisher, pseudonym, allies, superpowers, firstAppearance);
    }
}
