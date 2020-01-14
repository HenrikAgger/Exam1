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
@NamedQuery(name = "Menu.deleteAllRows", query = "DELETE from Menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String listRecipes;
    private String weekNo; // change to int
    private String year; // change to int

    @ManyToMany
    private List<Recipe> recipes = new ArrayList();

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    
    public Menu() {
    }

    public Menu(String listRecipes, String weekNo, String year) {
        this.listRecipes = listRecipes;
        this.weekNo = weekNo;
        this.year = year;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListRecipes() {
        return listRecipes;
    }

    public void setListRecipes(String listRecipes) {
        this.listRecipes = listRecipes;
    }

    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(String weekNo) {
        this.weekNo = weekNo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    

    
}
