package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Severin Simko
 */

//transactional annotation, because I want each method in different transaction
@Transactional
@Repository
public class ServiceDaoImpl implements ServiceDao {

    @PersistenceContext
    EntityManager em ;
    
    
    @Override
    public Service findbyId(Long id) {
        return (Service) em.createQuery("select s from Service where id = :id", Service.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void createService(Service service) {
        em.persist(service);
    }

    @Override
    public void removeService(Service service) {

        em.remove(em.merge(service));
            
    }

    @Override
    public Set<Service> getAllServices() {
        return (Set<Service>) em.createQuery("select s from Service s", Service.class).getResultList();
        
    }

}
