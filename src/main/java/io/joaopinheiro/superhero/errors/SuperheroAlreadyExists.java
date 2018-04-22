package io.joaopinheiro.superhero.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SuperheroAlreadyExists extends RuntimeException {
    public SuperheroAlreadyExists(String message){
        super(message);
    }
}
