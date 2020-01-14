/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.ItemDTO;
import dto.ItemsDTO;
import entities.Item;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class ItemFacade {
    private static ItemFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ItemFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ItemFacade getItemFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ItemFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Create an Item
    public ItemDTO addItem(ItemDTO i) {
        EntityManager em = getEntityManager();
        Item item = new Item(i.getName(), i.getPrice());
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ItemDTO(item);
    }

    // Find an Item
    public ItemDTO getItem(Long id) {
        EntityManager em = getEntityManager();
        Item i = em.find(Item.class, id);
        try {
            ItemDTO item = new ItemDTO(i);
            return item;
        } finally {
            em.close();
        }
    }

    // Get all Items
    public ItemsDTO getAllItems() {
        EntityManager em = getEntityManager();
        try {
            List<Item> list = em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
            return new ItemsDTO(list);
        } finally {
            em.close();
        }
    }

    // No of Items
    public long getItemCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long itemCount = (long) em.createQuery("SELECT COUNT(i) FROM Item i").getSingleResult();
            return itemCount;
        } finally {
            em.close();
        }

    }    
}
