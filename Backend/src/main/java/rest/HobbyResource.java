/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.HobbyDTO;
import facades.HobbyFacade;
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
@Path("hobby")
public class HobbyResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/exam1",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final HobbyFacade FACADE = HobbyFacade.getHobbyFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    // Create Hobby
    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createHobby(HobbyDTO hobbyDTO){
        FACADE.addHobby(hobbyDTO);
        return "{\"msg\":\"Hobby created\"}";
    }
    
    // Find a Hobby
    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public HobbyDTO findHobbies(@PathParam("id") Long id){
        HobbyDTO h = FACADE.getHobby(id);
        return h;
    }
    
    // Get all Hobbies
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<HobbyDTO> getAllHobbies() {
        return FACADE.getAllHobbies().getAll();
    }
    
    
    
}
