/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Dog;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Crumby
 */
@Transactional
@Repository
public class DogDaoImpl implements DogDao {
    @PersistenceContext
    EntityManagerFactory emf;
    
    @Override
    public void addDog(Dog dog) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(dog);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void removeDog(Dog dog) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(dog);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Dog getDogByID(Long id) {
        Dog dog;
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        dog = em.find(Dog.class, id);
        em.getTransaction().commit();
        em.close();
        
        return dog;
    }

    @Override
    public Set<Dog> getAllDogs() {
        Set<Dog> dogs;
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        dogs = (Set<Dog>)em.createQuery("select d from Dog d", Dog.class).getResultList();
        em.getTransaction().commit();
        em.close();
        
        return dogs;
    }
}
