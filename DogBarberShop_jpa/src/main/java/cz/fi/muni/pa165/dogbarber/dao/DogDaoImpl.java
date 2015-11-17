package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Dog;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pavel Drobek
 */

@Transactional
@Repository
public class DogDaoImpl implements DogDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void addDog(Dog dog) {
        em.persist(dog);
    }

    @Override
    public void removeDog(Dog dog) {
        em.remove(dog);
    }

    @Override
    public Dog getDogByID(Long id) {
        return em.find(Dog.class, id);
    }

    @Override
    public Set<Dog> getAllDogs() {
        return (Set<Dog>)em.createQuery("select d from Dog d", Dog.class).getResultList();
    }
}