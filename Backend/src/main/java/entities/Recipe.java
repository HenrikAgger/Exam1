/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

/**
 *
 * @author Henrik
 */
@Entity
@NamedQuery(name = "Recipe.deleteAllRows", query = "DELETE from Recipe")
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ingredientList;
    private double preparationTime;
    private String directions;

//    @ManyToMany(mappedBy = "menus")
//    private List<Recipe> menus = new ArrayList();
//
//    public List<Recipe> getMenus() {
//        return menus;
//    }
//
//    public void setMenus(List<Recipe> menus) {
//        this.menus = menus;
//    }
//
//    @OneToMany()
//    private Ingredient ingredient;
//
//    public Ingredient getIngredient() {
//        return ingredient;
//    }
//
//    public void setIngredient(Ingredient ingredient) {
//        this.ingredient = ingredient;
//    }
//    @ManyToMany(mappedBy = "recipes")
//    private List<Menu> menus;
    
//    @ManyToMany
//    private List<Menu> menus = new ArrayList();
//
//    
    
    public Recipe() {
    }

    public Recipe(String ingredientList, double preparationTime, String directions) {
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

    
}
