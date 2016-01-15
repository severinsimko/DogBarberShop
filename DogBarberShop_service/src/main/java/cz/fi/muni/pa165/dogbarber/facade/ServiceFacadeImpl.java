package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.ServiceChangeNameDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.DogService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Severin Simko
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceFacadeImpl implements ServiceFacade {
 
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Autowired
    private ServiceService service;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private DogService dogService;
    
    @Override
    public void changeServiceName(ServiceChangeNameDTO newName){
        if(newName==null){
            throw new DogBarberException("New name cannot be null");
        }
        
        service.changeServiceName(service.findById(newName.getServiceId()), newName.getServiceName());
    }
    
    @Override
    public void addEmployee(Long serviceId, Long employeeId ){
        if(service.findById(serviceId)==null){
            throw new DogBarberException("Service does not exist!");
        }
        
        if(employeeService.findEmployeeById(employeeId)==null){
            throw new DogBarberException("Employee does not exist!");
        }
        
        service.addEmployee(service.findById(serviceId),employeeService.findEmployeeById(employeeId) );
    }
    
    @Override
    public void deleteEmployee(Long serviceId, Long employeeId){
        if(service.findById(serviceId)==null){
            throw new DogBarberException("Service does not exist!");
        }
        
        if(employeeService.findEmployeeById(employeeId)==null){
            throw new DogBarberException("Employee does not exist!");
        }
        
        service.removeEmployee(service.findById(serviceId),employeeService.findEmployeeById(employeeId));
        
    }
    
    @Override
    public List<ServiceDTO> getSortedServices(){
        return beanMappingService.mapTo(service.sortedServicesByPrice(), ServiceDTO.class);
    }
    
    @Override
    public List<ServiceDTO> getAllServices(){
        return beanMappingService.mapTo(service.getAllServices(), ServiceDTO.class);
    }
    
    @Override
    public List<ServiceDTO> getServicesByName(String surName){
    	return beanMappingService.mapTo(service.getServicesByName(surName), ServiceDTO.class);
    }

    @Override
    public ServiceDTO getServiceById(Long id){
        
        if(service.findById(id)==null){
            throw new DogBarberException("Service does not exist!");
        }
      
        return beanMappingService.mapTo(service.findById(id), ServiceDTO.class);
    }
    
    @Override
    public Long createService(ServiceDTO s){
        if(s==null){
            throw new IllegalArgumentException("Service cannot be null!");
        }
    
        cz.fi.muni.pa165.dogbarber.entity.Service serv = new cz.fi.muni.pa165.dogbarber.entity.Service();
        serv.setServiceName(s.getServiceName());
        serv.setLengthInMinutes(s.getLengthInMinutes());
        serv.setPrice(s.getPrice());
        service.create(serv);

        return serv.getId();
    }

    @Override
    public void deleteService(Long serviceId){
        cz.fi.muni.pa165.dogbarber.entity.Service deleteService =  service.findById(serviceId);
        if(deleteService==null){
            throw new DogBarberException("Service does not exist!");            
        }
        service.remove(deleteService);
    }
    
    @Override
    public void updateService(ServiceDTO s){
       /* Service serviceEntity = service.findById(s.getId());
        if(serviceEntity==null){
            throw new NullPointerException("Service does not exist!");
        }
        
        serviceEntity.setLengthInMinutes(s.getLengthInMinutes());
        serviceEntity.setPrice(s.getPrice());
        serviceEntity.setServiceName(s.getServiceName());
        service.update(serviceEntity);
    */
        if(s==null){
            throw new DogBarberException("Service does not exist!");
        }
        
        service.update(beanMappingService.mapTo(s, cz.fi.muni.pa165.dogbarber.entity.Service.class));
    }
    
}
