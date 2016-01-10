package cz.fi.muni.pa165.dogbarbershop_rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.facade.ServiceFacade;
import cz.fi.muni.pa165.dogbarbershop_rest.ApiUris;
import cz.fi.muni.pa165.dogbarbershop_rest.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.dogbarbershop_rest.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.dogbarbershop_rest.exceptions.ResourceNotModifiedException;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Severin Simko
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_SERVICES)
public class ServicesController {

    final static Logger logger = LoggerFactory.getLogger(ServicesController.class);

    @Autowired
    private ServiceFacade serviceFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<ServiceDTO> getUServices() throws JsonProcessingException {

        logger.debug("rest getUsers()");

        return serviceFacade.getAllServices();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ServiceDTO getService(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getService({})", id);
        ServiceDTO serviceDTO = serviceFacade.getServiceById(id);
        if (serviceDTO == null) {
            throw new Exception();
        }
        return serviceDTO;
    }

    @RequestMapping(value = "/findbyname/{surname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<ServiceDTO> getServiceByName(@PathVariable("surname") String surname) throws Exception {

        logger.debug("rest getServiceByName({})", surname);

        List<ServiceDTO> name = serviceFacade.getServicesByName(surname);

        return name;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ServiceDTO createService(@RequestBody ServiceDTO service) throws Exception {
        logger.debug("Creating ServiceDTO" + service);

        try {
            Long id = serviceFacade.createService(service);
            return serviceFacade.getServiceById(id);

        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ServiceDTO updateService(@PathVariable("id") long id, @RequestBody ServiceDTO updatedService) throws Exception {
        logger.debug("Updating service" + updatedService);

        try {
            ServiceDTO service = serviceFacade.getServiceById(id);

        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

        try {
            updatedService.setId(id);
            serviceFacade.updateService(updatedService);

        } catch (Exception ex) {
            throw new ResourceNotModifiedException();

        }
        return updatedService;

    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteService(@PathVariable("id") long id) throws Exception {
        logger.debug("Deleting Service with id" + id);

        try {
            serviceFacade.deleteService(id);

        } catch (Exception ex) {
           throw new ResourceNotFoundException();
        }

    } 
   

}
