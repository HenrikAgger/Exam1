/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Ingredient;
import dto.IngredientDTO;
import dto.IngredientsDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class IngredientFacade {
    private static IngredientFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private IngredientFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IngredientFacade getIngredientFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new IngredientFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Create an Ingredient
    public IngredientDTO addIngredient(IngredientDTO i) {
        EntityManager em = getEntityManager();
        Ingredient ingredient = new Ingredient(i.getAmount());
        try {
            em.getTransaction().begin();
            em.persist(ingredient);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new IngredientDTO(ingredient);
    }

    // Find an Ingredient
    public IngredientDTO getIngredient(Long id) {
        EntityManager em = getEntityManager();
        Ingredient i = em.find(Ingredient.class, id);
        try {
            IngredientDTO ingredient = new IngredientDTO(i);
            return ingredient;
        } finally {
            em.close();
        }
    }

    // Get all Ingredients
    public IngredientsDTO getAllIngredients() {
        EntityManager em = getEntityManager();
        try {
            List<Ingredient> list = em.createQuery("SELECT i FROM Ingredient i", Ingredient.class).getResultList();
            return new IngredientsDTO(list);
        } finally {
            em.close();
        }
    }

    // No of Ingredients
    public long getIngredientCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long ingredientCount = (long) em.createQuery("SELECT COUNT(i) FROM Ingredient i").getSingleResult();
            return ingredientCount;
        } finally {
            em.close();
        }

    }    
}
