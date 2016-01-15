package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavel Drobek
 */
@org.springframework.stereotype.Service
public class DogServiceImpl implements DogService {
    
     final static Logger log = LoggerFactory.getLogger(DogServiceImpl.class);

    @Inject
    DogDao dogDao;

    @Inject
    ServiceDao serviceDao;

    @Override
    public void createDog(Dog dog) {
        try{
            dogDao.addDog(dog);
        } catch(PersistenceException e){
            throw new DataAccessException("Failed to add dog: there is an error on persistence layer. ", e) {
            	private static final long serialVersionUID = 1954956024288924798L;
            	};
        } catch(Exception e) {
            throw new DataAccessException("Failed to add dog: " + e.getMessage(), e) {
            	private static final long serialVersionUID = 1954956024288924798L;
            };
        }
    }

    @Override
    public void deleteDog(Dog dog) {
        try{
            dogDao.removeDog(dog);
        } catch(PersistenceException e){
            throw new DataAccessException("Failed to remove dog: there is an error on persistence layer. ", e) {
            	private static final long serialVersionUID = 1954956024288924798L;
            };
        } catch(Exception e) {
            throw new DataAccessException("Failed to remove dog: " + e.getMessage(), e) {
            	private static final long serialVersionUID = 1954956024288924798L;
            };
        }
    }

    @Override
    public Dog getDogByID(Long dogId) {
        try{
            return dogDao.getDogByID(dogId);
        } catch(PersistenceException e){
            throw new DataAccessException("Failed to get dog by id: there is an error on persistence layer. ", e) {
            	private static final long serialVersionUID = 1954956024288924798L;
            };
        } catch(Exception e) {
            throw new DataAccessException("Failed to get dog by id: " + e.getMessage(), e) {
            	private static final long serialVersionUID = 1954956024288924798L;
            };
        }
    }

    @Override
    public List<Dog> getAllDogs() {
        try{
            return dogDao.getAllDogs();
        } catch(PersistenceException e){
            throw new DataAccessException("Failed to get all dogs: there is an error on persistence layer. ", e) {
            	private static final long serialVersionUID = 1954956024288924798L;
            };
        } catch(Exception e) {
            throw new DataAccessException("Failed to get all dogs: " + e.getMessage(), e) {
            	private static final long serialVersionUID = 1954956024288924798L;
            };
        }
    }

    @Override
    public void subscribeDogForAService(Dog dog, Service service) {
        try{    
            List<Service> services = serviceDao.findByName(service.getServiceName());
            if (services.contains(service)) {
                dog.addService(service);
                dogDao.updateDog(dog);
            } else {
                throw new DogBarberException("No such service in database!");
            }
        } catch(PersistenceException e){
            throw new DataAccessException("Failed to subscribe dog for a service: there is an error on persistence layer. ", e) {
            	private static final long serialVersionUID = 1954956024288924798L;
            };
        }
    }

    @Override
    public void unsubscribeDogForAService(Dog dog, Service service) {
        try{    
            List<Service> services = serviceDao.findByName(service.getServiceName());
            if (!services.contains(service)){
                throw new DogBarberException("No such service in database!");
            }
            if(dog.getServices().contains(service))
            {
                dog.removeService(service);
                dogDao.updateDog(dog);
            } else {
                throw new DogBarberException("Dog is not subscribed for this service!");
            }
        } catch(PersistenceException e){
            throw new DataAccessException("Failed to add dog", e) {
            	private static final long serialVersionUID = 1954956024288924798L;
            };
        }
    }

    @Override
    public double getTotalPrice(Long dogId) {
        log.error("GET TOTAL PRICE FOR DOG DOG SERVICE IMPL CLASS");
       // BigDecimal totalPrice = new BigDecimal("0.00");
        
        
        Set<Service> services = dogDao.getDogByID(dogId).getServices();
        log.error("Set of the services for a dog"+ services);
        double priceAll = 0;
        for(Service s : services){
            
            priceAll += s.getPrice().doubleValue();
            log.error("PRICE FOR A SERVICE IS "+s.getPrice().doubleValue());
           // totalPrice.add(s.getPrice());
        }
        log.error("TOTAL PRICE COMING FROM GET TOTAL DOG SERVICE IMPL IS:"+ priceAll);
        return priceAll;
        
    }

    
     @Override
    public Dog update(Dog dog) {
       return dogDao.updateDog(dog);
    }
}
