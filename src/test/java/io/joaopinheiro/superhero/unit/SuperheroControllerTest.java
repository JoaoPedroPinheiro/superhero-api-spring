package io.joaopinheiro.superhero.unit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.joaopinheiro.superhero.Superhero;
import io.joaopinheiro.superhero.SuperheroController;
import io.joaopinheiro.superhero.SuperheroRepository;
import io.joaopinheiro.superhero.TestUtils;
import io.joaopinheiro.superhero.errors.SuperheroNotFound;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SuperheroController.class)
public class SuperheroControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SuperheroRepository repository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllSuperheroesEmpty() throws Exception{
        List<Superhero> allSuperheroes = Collections.emptyList();
        given(repository.findAll()).willReturn(allSuperheroes);


        MvcResult result = mvc.perform(get("http://localhost:8080/superheroes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();

        List<Superhero> resultHeroes = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Superhero>>(){});
        assertEquals(resultHeroes, allSuperheroes);
    }

    @Test
    public void getAllSuperheroesSingle() throws Exception {
        List<Superhero> allSuperheroes = Arrays.asList(TestUtils.BATMAN);
        given(repository.findAll()).willReturn(allSuperheroes);


        MvcResult result = mvc.perform(get("http://localhost:8080/superheroes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();

        List<Superhero> resultHeroes = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Superhero>>(){});
        assertEquals(resultHeroes, allSuperheroes);
    }

    @Test
    public void getAllSuperheroesMultiple() throws Exception{
        List<Superhero> allSuperheroes = Arrays.asList(TestUtils.BATMAN, TestUtils.HULK, TestUtils.SUPERMAN);
        given(repository.findAll()).willReturn(allSuperheroes);


        MvcResult result = mvc.perform(get("http://localhost:8080/superheroes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andReturn();

        List<Superhero> resultHeroes = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Superhero>>(){});
        assertEquals(resultHeroes, allSuperheroes);
    }

    @Test
    public void getSuperheroDetailsSuccess() throws Exception{
        Long id = TestUtils.BATMAN.getId();

        given(repository.findById(id)).willReturn(Optional.of(TestUtils.BATMAN));

        MvcResult result = mvc.perform(get("http://localhost:8080/superheroes/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Superhero resultHero = mapper.readValue(result.getResponse().getContentAsString(), Superhero.class);
        assertEquals(TestUtils.BATMAN, resultHero);
    }

    @Test
    public void getSuperheroDetailsNotFound() throws Exception{
        Long id = Long.MAX_VALUE;

        given(repository.findById(id)).willReturn(Optional.empty());

        mvc.perform(get("http://localhost:8080/superheroes/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSuperheroAllies() throws Exception{
        Long id = TestUtils.BATMAN.getId();

        given(repository.findById(id)).willReturn(Optional.of(TestUtils.BATMAN));

        MvcResult result = mvc.perform(get("http://localhost:8080/superheroes/" + id + "/allies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<String> alliesResult = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){});
        assertNotNull(alliesResult);
        assertEquals(alliesResult, TestUtils.BATMAN.getAllies());

    }

    @Test
    public void getSuperheroAlliesNotFound() throws Exception{
        given(repository.findById(Long.MAX_VALUE)).willReturn(Optional.empty());

        mvc.perform(get("http://localhost:8080/superheroes/" + Long.MAX_VALUE + "/allies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getSuperheroAlliesEmpty() throws Exception{
        Long id = TestUtils.SUPERMAN.getId();

        given(repository.findById(id)).willReturn(Optional.of(TestUtils.SUPERMAN));
        MvcResult result = mvc.perform(get("http://localhost:8080/superheroes/" + id + "/allies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();

        List<String> resultAllies = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){});
        assertNotNull(resultAllies);
        assertEquals(Collections.emptyList(), resultAllies);
    }

    @Test
    public void createSuperheroSuccess() throws Exception{

        given(repository.findById(TestUtils.BATMAN.getId())).willReturn(Optional.empty());
        given(repository.save(TestUtils.BATMAN)).willReturn(TestUtils.BATMAN);

        MvcResult result = mvc.perform(post("http://localhost:8080/superheroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TestUtils.BATMAN)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void createSuperheroAlreadyExists() throws Exception{
        given(repository.findById(TestUtils.BATMAN.getId())).willReturn(Optional.of(TestUtils.BATMAN));
        mvc.perform(post("http://localhost:8080/superheroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TestUtils.BATMAN)))
                .andExpect(status().isConflict());
    }

    @Test
    public void updateSuperheroNotFound() throws Exception{
        given(repository.findById(TestUtils.SUPERMAN.getId())).willReturn(Optional.empty());
        mvc.perform(put("http://localhost:8080/superheroes/" + TestUtils.SUPERMAN.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TestUtils.SUPERMAN)))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void updateSuperheroSuccess() throws Exception{
        given(repository.findById(TestUtils.SUPERMAN.getId())).willReturn(Optional.of(new Superhero()));

        mvc.perform(put("http://localhost:8080/superheroes/" + TestUtils.SUPERMAN.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TestUtils.SUPERMAN)))
                .andExpect(status().isOk());


    }
}