/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Ingredient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrik
 */
public class IngredientsDTO {
    List<IngredientDTO> all = new ArrayList<>();

    public IngredientsDTO(List<Ingredient> ingredientEntities) {
        for (Ingredient ingredientEntity : ingredientEntities) {
            all.add(new IngredientDTO(ingredientEntity));
        }
    }

    public List<IngredientDTO> getAll() {
        return all;
    }

    public void setAll(List<IngredientDTO> all) {
        this.all = all;
    }
}
