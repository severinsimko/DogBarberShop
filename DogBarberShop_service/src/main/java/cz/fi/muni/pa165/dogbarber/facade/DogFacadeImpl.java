package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.DogCreateDTO;
import cz.fi.muni.pa165.dogbarber.dto.DogDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.CustomerService;
import cz.fi.muni.pa165.dogbarber.service.DogService;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pavel Drobek
 */

@Service
@Transactional
public class DogFacadeImpl implements DogFacade {
    
    @Autowired
    private DogService dogService;
    
    @Autowired
    private ServiceService serviceService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public void createDog(DogCreateDTO dog) {
        if(dog == null){
            throw new IllegalArgumentException("Dog cannot be null!");
        }
        Dog dogy = new Dog(dog.getName(), dog.getBreed(), dog.getBornDate(), dog.getColor());
        dogy.setCustomer(customerService.findById(dog.getCustomerId()));
        dogService.createDog(dogy);
    }
    
    @Override
    public void deleteDog(DogDTO dog) {
        if(dog ==null){
            throw new DogBarberException("A dog does not exist.");
        }
        dogService.deleteDog(dogService.getDogByID(dog.getId()));
    }
    
    @Override
    public DogDTO getDogByID(Long dogId) {
        Dog dog = dogService.getDogByID(dogId);
        
        if(dog == null){
            throw new DogBarberException("A dog does not exist.");
        }
        return beanMappingService.mapTo(dogService.getDogByID(dogId), DogDTO.class);
    }
    
    @Override
    public List<DogDTO> getAllDogs() {
        return beanMappingService.mapTo(dogService.getAllDogs(), DogDTO.class);
    }
    
    @Override
    public void subscribeDogForAService(DogDTO dog, ServiceDTO service) {
        dogService.subscribeDogForAService(dogService.getDogByID(dog.getId()), 
                serviceService.findById(service.getId()));
    }
    
    @Override
    public void unsubscribeDogForAService(DogDTO dog, ServiceDTO service) {
        dogService.unsubscribeDogForAService(dogService.getDogByID(dog.getId()), 
                serviceService.findById(service.getId()));
    }

    @Override
    public double getTotalPrice(Long dogId) {
        return dogService.getTotalPrice(dogId);
    }
    
    public void update(DogDTO d){
        
          if(d==null){
            throw new DogBarberException("Dog does not exist!");
        }
        
        dogService.update(beanMappingService.mapTo(d, Dog.class));
    
    }
    
}
