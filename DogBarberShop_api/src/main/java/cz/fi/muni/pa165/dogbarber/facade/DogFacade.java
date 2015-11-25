package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.DogCreatedDTO;
import cz.fi.muni.pa165.dogbarber.dto.DogDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import java.util.List;

/**
 *
 * @author Crumby
 */
public interface DogFacade {
    
    void createDog(DogCreatedDTO dog);
    
    void deleteDog(DogDTO dog);
    
    DogDTO getDogByID(Long dogId);
    
    List<DogDTO> getAllDogs();
    
    void subscribeDogForAService(DogDTO dog, ServiceDTO service);
    
    void unsubscribeDogForAService(DogDTO dog, ServiceDTO service);
}