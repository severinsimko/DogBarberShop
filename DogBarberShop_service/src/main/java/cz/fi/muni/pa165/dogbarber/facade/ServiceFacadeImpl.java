
package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.ServiceChangeNameDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Severin Simko
 */
@Service
@Transactional
public class ServiceFacadeImpl implements ServiceFacade {
 
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Autowired
    private ServiceService service;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Override
    public void changeServiceName(ServiceChangeNameDTO newName){
        service.changeServiceName(service.findById(newName.getServiceId()), newName.getServiceName());
    
    }
    
    @Override
    public void addEmployee(Long serviceId, Long employeeId ){
    service.addEmployee(service.findById(serviceId),employeeService.findEmployeeById(employeeId) );
    }
    
    @Override
    public void deleteEmployee(Long serviceId, Long employeeId){
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
      
        return beanMappingService.mapTo(service.findById(id), ServiceDTO.class);
    }
    
    @Override
    public Long createService(ServiceDTO s){
    
        cz.fi.muni.pa165.dogbarber.entity.Service serv = new cz.fi.muni.pa165.dogbarber.entity.Service();
        serv.setServiceName(s.getServiceName());
        serv.setLengthInMinutes(s.getLengthInMinutes());
        serv.setPrice(s.getPrice());
        service.create(serv);
        
        
        return serv.getId();
    }
    
    
    
    @Override
    public void deleteService(Long serviceId){
    
        service.remove(service.findById(serviceId));
    }
    
    @Override
    public void updateService(ServiceDTO s){
    
        service.update(beanMappingService.mapTo(s, cz.fi.muni.pa165.dogbarber.entity.Service.class));
    }
    
}
