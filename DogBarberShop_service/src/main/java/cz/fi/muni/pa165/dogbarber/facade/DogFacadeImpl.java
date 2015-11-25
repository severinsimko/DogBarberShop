package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.DogCreatedDTO;
import cz.fi.muni.pa165.dogbarber.dto.DogDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.DogService;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Pavel Drobek
 */
public class DogFacadeImpl implements DogFacade {
    
    @Inject
    private DogService dogService;
    
    @Inject
    private ServiceService serviceService;
    
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public void createDog(DogCreatedDTO dog) {
        Dog dogy = new Dog(dog.getName(), dog.getBreed(), dog.getBornDate(), dog.getColor());
        dogService.createDog(dogy);
    }
    
    @Override
    public void deleteDog(DogDTO dog) {
        dogService.deleteDog(dogService.getDogByID(dog.getId()));
    }
    
    @Override
    public DogDTO getDogByID(Long dogId) {
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
    
}
