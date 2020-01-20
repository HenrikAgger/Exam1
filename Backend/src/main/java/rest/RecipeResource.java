/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.RecipeDTO;
import entities.Recipe;
import facades.RecipeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Henrik
 */
@Path("recipe")
public class RecipeResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/exam1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final RecipeFacade FACADE =  RecipeFacade.getRecipeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    // Create a Recipe
    @Path("create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public RecipeDTO createRecipe(RecipeDTO recipeDTO){
        RecipeDTO rDTO = FACADE.addRecipe(recipeDTO);
        return rDTO;
    }
    
    // Edit a Recipe
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public RecipeDTO editRecipe(String recipe){
        Recipe r = GSON.fromJson(recipe, Recipe.class);
        RecipeDTO rDTO = new RecipeDTO(r);
        return FACADE.editRecipe(rDTO);
    }
    
    // Delete a Recipe
    @DELETE
    @Path("/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public RecipeDTO deleteRecipe(@PathParam("id") Long id){
        return FACADE.deleteRecipe(id);
    }
    
    
    
    // Find a Recipe
    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public RecipeDTO findRecipe(@PathParam("id") Long id) {
        RecipeDTO r = FACADE.getRecipe(id);
        return r;
    }
    
    @Path("populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populate();
        return "{\"msg\":\"Tables populated\"}";
    }
    
    // Get all Recipes
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<RecipeDTO> getAllRecipes() {
        return FACADE.getAllRecipes().getAll();
    } 
    
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRecipeCount() {
        long count = FACADE.getRecipeCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }    
    
    
    
    
    
}
