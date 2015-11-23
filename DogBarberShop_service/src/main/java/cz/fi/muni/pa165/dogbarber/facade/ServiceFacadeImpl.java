
package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.ServiceChangeNameDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.util.Collection;
import javax.inject.Inject;

/**
 *
 * @author Severin Simko
 */
public class ServiceFacadeImpl implements ServiceFacade {
 
    @Inject
    private BeanMappingService beanMappingService;
    
    @Inject
    private ServiceService service;
    
    @Inject
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
    public Collection<ServiceDTO> getAllServices(){
    
        return beanMappingService.mapTo(service.getAllServices(), ServiceDTO.class);
    }
    
    @Override
    public ServiceDTO getServiceById(Long id){
    
        return beanMappingService.mapTo(service.findById(id), ServiceDTO.class);
    }
    
    @Override
    public Long createService(ServiceDTO s){
    
        Service serv = new Service();
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
    
        service.update(beanMappingService.mapTo(s, Service.class));
    }
    
}
