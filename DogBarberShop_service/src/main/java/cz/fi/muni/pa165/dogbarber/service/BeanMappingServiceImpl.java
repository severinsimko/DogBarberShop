package cz.fi.muni.pa165.dogbarber.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author artur
 */



public class BeanMappingServiceImpl implements BeanMappingService {
    	
    @Autowired
    private Mapper m;

    @Override
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(m.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public  <T> T mapTo(Object u, Class<T> mapToClass)
    {
        return m.map(u,mapToClass);
    }
    
    @Override
    public Mapper getMapper(){
    	return m;
    }
}
