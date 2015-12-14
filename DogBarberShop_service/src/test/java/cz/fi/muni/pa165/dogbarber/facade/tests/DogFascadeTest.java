package cz.fi.muni.pa165.dogbarber.facade.tests;

import cz.fi.muni.pa165.dogbarber.dto.CustomerDTO;
import cz.fi.muni.pa165.dogbarber.dto.DogCreatedDTO;
import cz.fi.muni.pa165.dogbarber.dto.DogDTO;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.enums.Color;
import cz.fi.muni.pa165.dogbarber.facade.DogFacadeImpl;
import cz.fi.muni.pa165.dogbarber.service.DogService;
import java.util.Calendar;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Pavel Drobek
 */
public class DogFascadeTest {
    @Mock
    private DogService dogService;
    
    @Autowired
    @InjectMocks
    private DogFacadeImpl dogFacade;
    
    CustomerDTO customer;
    
    DogCreatedDTO dogCreated;
    
    DogDTO dogDTO;
    
    Dog dog;
    
    @BeforeClass
    public void prepare(){
        MockitoAnnotations.initMocks(this);
        
        customer = new CustomerDTO();
        customer.setName("Honza Novak");
        
        Calendar bornDate = Calendar.getInstance();
        bornDate.set(2011, 9, 12);
        dogCreated = new DogCreatedDTO("Johny", "Buldog", Color.BLACK, bornDate, customer);
        
        dogDTO = new DogDTO();
        dogDTO.setId(1L);
        dogDTO.setName("Happy");
        dogDTO.setBreed("Lazy");
        dogDTO.setBornDate(bornDate);
        dogDTO.setColor(Color.BLACK);
        
        dog = new Dog(dogCreated.getName(), dogCreated.getBreed(), dogCreated.getBornDate(), dogCreated.getColor());
        
        when(dogService.getDogByID(1L)).thenReturn(dog);
    }
    
    @Test
    public void createDogTest(){
        dogFacade.createDog(dogCreated);
        verify(dogService, times(1)).createDog(dog);
    }
    
    @Test
    public void deleteDogTest(){
        dogFacade.deleteDog(dogDTO);
        verify(dogService, times(1)).deleteDog(dog);
    }
}
