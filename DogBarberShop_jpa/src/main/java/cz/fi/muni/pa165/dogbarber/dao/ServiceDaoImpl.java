package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Severin Simko
 */

@Transactional
@Repository
public class ServiceDaoImpl implements ServiceDao {

    @PersistenceContext
    private EntityManager em ;
    
    
    @Override
    public Service findbyId(Long id) {
        return em.find(Service.class, id);
    }

    @Override
    public void createService(Service service) {
        em.persist(service);
    }
    
    @Override
    public Service updateService(Service service){

        return em.merge(service);
    }

    @Override
    public void removeService(Service service) {

        em.remove(findbyId(service.getId()));
            
    }

    @Override
    public List<Service> getAllServices() {
        return em.createQuery("select s from Service s", Service.class).getResultList();
        
    }
    
    @Override
    public List<Service> findByName(String serviceName){
        
    return em.createQuery("select s from Service s where s.serviceName =:serviceName", Service.class).setParameter("serviceName", serviceName).getResultList();
    }
    
    
}