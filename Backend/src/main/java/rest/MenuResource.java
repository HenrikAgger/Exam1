/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MenuDTO;
import dto.PersonDTO;
import facades.MenuFacade;
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
@Path("menu")
public class MenuResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/exam1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final MenuFacade FACADE =  MenuFacade.getMenuFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    // Create a Menu
    @Path("create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createMenu(MenuDTO menuDTO){
        FACADE.addMenu(menuDTO);
        return "{\"msg\":\"Menu created\"}";
    }
    
    // Find a Menu
    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public MenuDTO findPerson(@PathParam("id") Long id) {
        MenuDTO m = FACADE.getMenu(id);
        return m;
    }
    
    // Get all Menus
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<MenuDTO> getAllMenus() {
        return FACADE.getAllMenus().getAll();
    } 
    
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMenuCount() {
        long count = FACADE.getMenuCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }    
}
