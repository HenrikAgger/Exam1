/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Address;
import dto.AddressDTO;
import dto.AddressesDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class AddressFacade {
       
    private static AddressFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private AddressFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static AddressFacade getAddressFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // Create an Address
    public AddressDTO addAddress(AddressDTO a) {
        EntityManager em = getEntityManager();
        // Address eller AddressDTO ?
        Address address = new Address(a.getStreet(), a.getCity(), a.getZip());
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AddressDTO(address);
    }
    
    // Find an Address
    public AddressDTO getAddress(Long id) {
        EntityManager em = getEntityManager();
        // Address eller AddressDTO ?
        Address a = em.find(Address.class, id);
        try {
            AddressDTO address = new AddressDTO(a);
            return address;
        } finally {
            em.close();
        }
    }
    
    // Get all Addresses
    public AddressesDTO getAllAddresses() {
        EntityManager em = getEntityManager();
        try {
            List<Address> list = em.createQuery("Select a FROM Address a", Address.class).getResultList();
            return new AddressesDTO(list);
        } finally {
            em.close();
        }
    }
    
    // Get no of Addresses
    public long getAddressCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long addressCount = (long) em.createQuery("SELECT COUNT(a) FROM Address a").getSingleResult();
            return addressCount;
        } finally {
            em.close();
        }
    }
    
    
    
}
