/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrik
 */
public class ItemsDTO {
    List<ItemDTO> all = new ArrayList<>();

    public ItemsDTO(List<Item> itemEntities) {
        for (Item itemEntity : itemEntities) {
            all.add(new ItemDTO(itemEntity));
        }
    }

    public List<ItemDTO> getAll() {
        return all;
    }

    public void setAll(List<ItemDTO> all) {
        this.all = all;
    }
}
