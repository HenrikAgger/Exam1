/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Recipe;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrik
 */
public class RecipesDTO {
            List<RecipeDTO> all = new ArrayList<>();

    public RecipesDTO(List<Recipe> recipeEntities) {
        for (Recipe recipeEntity : recipeEntities) {
            all.add(new RecipeDTO(recipeEntity));
        }
    }

    public List<RecipeDTO> getAll() {
        return all;
    }

    public void setAll(List<RecipeDTO> all) {
        this.all = all;
    }
}
