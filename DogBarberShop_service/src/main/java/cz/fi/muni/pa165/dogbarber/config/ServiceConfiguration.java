/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.config;

import cz.fi.muni.pa165.dogbarber.facade.ServiceFacadeImpl;
import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dogbarber.service.ServiceServiceImpl;
import java.util.Locale.Category;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Severin Simko
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={ServiceServiceImpl.class, ServiceFacadeImpl.class})
public class ServiceConfiguration {
	

	@Bean
	public Mapper dozer(){
		return new DozerBeanMapper();
	}
	
	/**
	 * Custom config for Dozer if needed
	 * @author nguyen
	 *
	 */
	/*public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
	        mapping(Category.class, CategoryDTO.class);
	    }
	}*/
	
}