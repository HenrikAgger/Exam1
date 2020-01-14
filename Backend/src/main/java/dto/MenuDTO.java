/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Menu;
import java.util.Objects;

/**
 *
 * @author Henrik
 */
public class MenuDTO {

    private Long id;
    private String listRecipes;
    private String weekNo;
    private String theYear;

    public MenuDTO(Menu m) {
        this.listRecipes = m.getListRecipes();
        this.weekNo = m.getWeekNo();
        this.theYear = m.getTheYear();
        this.id = m.getId();
    }

    public MenuDTO(String listRecipes, String weekNo, String theYear) {
        this.listRecipes = listRecipes;
        this.weekNo = weekNo;
        this.theYear = theYear;
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

    public String getTheYear() {
        return theYear;
    }

    public void setTheYear(String theYear) {
        this.theYear = theYear;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.listRecipes);
        hash = 79 * hash + Objects.hashCode(this.weekNo);
        hash = 79 * hash + Objects.hashCode(this.theYear);
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
        final MenuDTO other = (MenuDTO) obj;
        if (!Objects.equals(this.listRecipes, other.listRecipes)) {
            return false;
        }
        if (!Objects.equals(this.weekNo, other.weekNo)) {
            return false;
        }
        if (!Objects.equals(this.theYear, other.theYear)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
