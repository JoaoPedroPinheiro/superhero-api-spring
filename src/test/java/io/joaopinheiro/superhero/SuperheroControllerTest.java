package io.joaopinheiro.superhero;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SuperheroController.class)
public class SuperheroControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SuperheroController controller;

    @Test
    public void getAllSuperheroes() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        List<Superhero> allSuperheroes = Arrays.asList(TestUtils.BATMAN);

        given(controller.getAllSuperheroes()).willReturn(allSuperheroes);

        MvcResult result = mvc.perform(get("http://localhost:8080/superheroes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();

        List<Superhero> resultHeroes = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Superhero>>(){});



        assertEquals(resultHeroes, allSuperheroes);




    }

    @Test
    public void getSuperheroDetails() {
    }

    @Test
    public void getSuperheroAllies() {
    }

    @Test
    public void createSuperhero() {
    }

    @Test
    public void updateSuperhero() {
    }
}