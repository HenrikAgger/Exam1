/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Hobby;
import dto.HobbyDTO;
import dto.HobbiesDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class HobbyFacade {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HobbyFacade getHobbyFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Create a Hobby
    public HobbyDTO addHobby(HobbyDTO h) {
        EntityManager em = getEntityManager();
        Hobby hobby = new Hobby(h.getName(), h.getDescription());
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HobbyDTO(hobby);
    }

    // Find a Hobby
    public HobbyDTO getHobby(Long id) {
        EntityManager em = getEntityManager();
        Hobby h = em.find(Hobby.class, id);
        try {
            HobbyDTO hobby = new HobbyDTO(h);
            return hobby;
        } finally {
            em.close();
        }
    }

    // Get all Hobbies
    public HobbiesDTO getAllHobbies() {
        EntityManager em = getEntityManager();
        try {
            List<Hobby> list = em.createQuery("SELECT h FROM Hobby h", Hobby.class).getResultList();
            return new HobbiesDTO(list);
        } finally {
            em.close();
        }
    }

    //TODO Remove/Change this before use
    public long getHobbyCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long hobbyCount = (long) em.createQuery("SELECT COUNT(h) FROM Hobby h").getSingleResult();
            return hobbyCount;
        } finally {
            em.close();
        }

    }
}
