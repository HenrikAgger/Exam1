/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.StorageDTO;
import dto.StoragesDTO;
import entities.Storage;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class StorageFacade {

    private static StorageFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private StorageFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static StorageFacade getStorageFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StorageFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Create Storage
    public StorageDTO addStorage(StorageDTO s) {
        EntityManager em = getEntityManager();
        Storage storage = new Storage(s.getAmount());
        try {
            em.getTransaction().begin();
            em.persist(storage);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new StorageDTO(storage);
    }

    // Find Storage
    public StorageDTO getStorage(Long id) {
        EntityManager em = getEntityManager();
        Storage s = em.find(Storage.class, id);
        try {
            StorageDTO storage = new StorageDTO(s);
            return storage;
        } finally {
            em.close();
        }
    }

    // Get all Storage
    public StoragesDTO getAllStorages() {
        EntityManager em = getEntityManager();
        try {
            List<Storage> list = em.createQuery("SELECT s FROM Storage s", Storage.class).getResultList();
            return new StoragesDTO(list);
        } finally {
            em.close();
        }
    }

    // No of Storage
    public long getStoragesCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long storageCount = (long) em.createQuery("SELECT COUNT(s) FROM Storage s").getSingleResult();
            return storageCount;
        } finally {
            em.close();
        }
    }
}
