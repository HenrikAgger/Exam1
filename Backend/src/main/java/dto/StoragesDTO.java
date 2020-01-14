/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Storage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrik
 */
public class StoragesDTO {

    List<StorageDTO> all = new ArrayList<>();

    public StoragesDTO(List<Storage> storageEntities) {
        for (Storage storageEntity : storageEntities) {
            all.add(new StorageDTO(storageEntity));
        }
    }

    public List<StorageDTO> getAll() {
        return all;
    }

    public void setAll(List<StorageDTO> all) {
        this.all = all;
    }
}
