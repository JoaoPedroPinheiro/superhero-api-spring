package io.joaopinheiro.superhero;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class TestUtils {

    public static final Superhero BATMAN;
    public static final Superhero HULK;
    public static final Superhero SUPERMAN;

    static{
        BATMAN = new Superhero();
        BATMAN.setId(1);
        BATMAN.setName("Bruce Wayne");
        BATMAN.setPseudonym("Batman");
        BATMAN.setPublisher("DC");
        BATMAN.setAllies(Arrays.asList("Robin", "Catwoman", "Justice League"));
        BATMAN.setSuperpowers(Arrays.asList("Money", "Discipline"));
        BATMAN.setFirstAppearance(new Date());

        HULK = new Superhero();
        HULK.setId(2);
        HULK.setName("Bruce Banner");
        HULK.setPseudonym("Hulk");
        HULK.setPublisher("Marvel");
        HULK.setAllies(Arrays.asList("Captain America", "Iron Man","The Avengers"));
        HULK.setSuperpowers(Arrays.asList("Mutant Strength", "Is always angry"));
        HULK.setFirstAppearance(new Date());

        SUPERMAN = new Superhero();
        SUPERMAN.setId(3);
        SUPERMAN.setName("Clark Kent");
        SUPERMAN.setPseudonym("Superman");
        SUPERMAN.setPublisher("DC");
        SUPERMAN.setAllies(Collections.emptyList());
        SUPERMAN.setSuperpowers(Arrays.asList("Flying", "Super Strength", "Laser Eyes"));
        SUPERMAN.setFirstAppearance(new Date());
    }
}
