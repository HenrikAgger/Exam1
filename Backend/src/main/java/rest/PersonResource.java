/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import facades.PersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Henrik
 */

@Path("person")
public class PersonResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/exam1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    // Create a Person
    @Path("create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createPerson(PersonDTO personDTO){
        FACADE.addPerson(personDTO);
        return "{\"msg\":\"Person created\"}";
    }
    
    // Find a Person
    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public PersonDTO findPerson(@PathParam("id") Long id) {
        PersonDTO p = FACADE.getPerson(id);
        return p;
    }
    
    // Get all Persons
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<PersonDTO> getAllPersons() {
        return FACADE.getAllPersons().getAll();
    } 
    
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonCount() {
        long count = FACADE.getPersonCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
}
