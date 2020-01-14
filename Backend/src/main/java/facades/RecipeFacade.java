/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.RecipeDTO;
import dto.RecipesDTO;
import entities.Recipe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class RecipeFacade {
    private static RecipeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private RecipeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static RecipeFacade getRecipeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RecipeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // Create a Recipe
    public RecipeDTO addRecipe(RecipeDTO r){
        EntityManager em = getEntityManager();
        Recipe recipe = new Recipe(r.getIngredientList(), r.getPreparationTime(), r.getDirections());
        try{
            em.getTransaction().begin();
            em.persist(recipe);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RecipeDTO(recipe);
    }
    
    // Find a Recipe
    public RecipeDTO getRecipe(Long recipe_id){
        EntityManager em = getEntityManager();
        Recipe recipeDTO = em.find(Recipe.class, recipe_id);
        return new RecipeDTO(recipeDTO);
    }
    
    // Get all Recipes
    public RecipesDTO getAllRecipes() {
        EntityManager em = getEntityManager();
        try {
            List<Recipe> list = em.createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList();
            return new RecipesDTO(list);
        } finally{
            em.close();
        }
    }
    
    // No of Recpies
    public long getRecipeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long recipeCount = (long)em.createQuery("SELECT COUNT(r) FROM Recipe r").getSingleResult();
            return recipeCount;
        }finally{  
            em.close();
        }
        
    } 
}
