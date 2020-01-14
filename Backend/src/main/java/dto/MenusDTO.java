/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Menu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrik
 */
public class MenusDTO {
    List<MenuDTO> all = new ArrayList<>();
    
    public MenusDTO(List<Menu> menuEntities){
        for (Menu menuEntity : menuEntities){
            all.add(new MenuDTO(menuEntity));
        }
    }

    public List<MenuDTO> getAll() {
        return all;
    }

    public void setAll(List<MenuDTO> all) {
        this.all = all;
    }
    

}
