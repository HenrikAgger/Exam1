/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.MenuDTO;
import dto.MenusDTO;
import entities.Menu;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class MenuFacade {

    private static MenuFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MenuFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MenuFacade getMenuFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MenuFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Create a Menu
    public MenuDTO addMenu(MenuDTO m) {
        EntityManager em = getEntityManager();
        Menu menu = new Menu(m.getListRecipes(), m.getWeekNo(), m.getYear());
        try {
            em.getTransaction().begin();
            em.persist(menu);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MenuDTO(menu);
    }

    // Find a Menu
    public MenuDTO getMenu(Long menu_id) {
        EntityManager em = getEntityManager();
        Menu menuDTO = em.find(Menu.class, menu_id);
        return new MenuDTO(menuDTO);
    }

    // Get all Menus
    public MenusDTO getAllMenus() {
        EntityManager em = getEntityManager();
        try {
            List<Menu> list = em.createQuery("SELECT m FROM Menu m", Menu.class).getResultList();
            return new MenusDTO(list);
        } finally {
            em.close();
        }
    }

    // No of Menus
    public long getMenuCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long menuCount = (long) em.createQuery("SELECT COUNT(m) FROM Menu m").getSingleResult();
            return menuCount;
        } finally {
            em.close();
        }

    }
}
