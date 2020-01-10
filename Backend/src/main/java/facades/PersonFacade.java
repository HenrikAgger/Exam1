/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class PersonFacade {
    
    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // Create a Person
    public PersonDTO addPerson(PersonDTO p){
        EntityManager em = getEntityManager();
        Person person = new Person(p.getEmail(), p.getPhone(), p.getfName(), p.getlName());
        try{
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }
    
    // Find a Person
    public PersonDTO getPerson(Long person_id){
        EntityManager em = getEntityManager();
        Person personDTO = em.find(Person.class, person_id);
        return new PersonDTO(personDTO);
    }
    
    // Get all Persons
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            List<Person> list = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
            return new PersonsDTO(list);
        } finally{
            em.close();
        }
    }
    
    
    
//    // Get all Persons
//    public List<PersonDTO> getAllPersons(){
//        EntityManager em = getEntityManager();
//        List<PersonDTO> persons = em.createQuery("SELECT p FROM PersonDTO p", PersonDTO.class).getResultList();
//        return persons;
//    }
    
    
    // No of Persons
    public long getPersonCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long personCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return personCount;
        }finally{  
            em.close();
        }
        
    }
}
