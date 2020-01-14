/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Recipe;
import java.util.Objects;

/**
 *
 * @author Henrik
 */
public class RecipeDTO {

    private Long id;
    private String ingredientList;
    private double preparationTime;
    private String directions;

    public RecipeDTO(Recipe r) {
        this.ingredientList = r.getIngredientList();
        this.preparationTime = r.getPreparationTime();
        this.directions = r.getDirections();
        this.id = id;
    }

    public RecipeDTO(String ingredientList, double preparationTime, String directions) {
        this.ingredientList = ingredientList;
        this.preparationTime = preparationTime;
        this.directions = directions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(String ingredientList) {
        this.ingredientList = ingredientList;
    }

    public double getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(double preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.ingredientList);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.preparationTime) ^ (Double.doubleToLongBits(this.preparationTime) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.directions);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RecipeDTO other = (RecipeDTO) obj;
        if (Double.doubleToLongBits(this.preparationTime) != Double.doubleToLongBits(other.preparationTime)) {
            return false;
        }
        if (!Objects.equals(this.ingredientList, other.ingredientList)) {
            return false;
        }
        if (!Objects.equals(this.directions, other.directions)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }



}
