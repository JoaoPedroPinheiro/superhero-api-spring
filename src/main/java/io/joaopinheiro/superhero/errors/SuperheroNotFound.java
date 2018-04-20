package io.joaopinheiro.superhero.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SuperheroNotFound extends RuntimeException{
    public SuperheroNotFound(){
        super();
    }

    public SuperheroNotFound(String message){
        super(message);
    }
}
