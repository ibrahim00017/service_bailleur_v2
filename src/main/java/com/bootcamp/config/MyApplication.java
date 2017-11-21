package com.bootcamp.config;

import com.bootcamp.resources.BailleurService;
import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("/rest")
public class MyApplication extends Application{

	
	 public MyApplication() {

	        BeanConfig beanConfig = new BeanConfig();
	        beanConfig.setVersion("1.0.2");
	        beanConfig.setTitle("SERVICE BAILLEUR");
	        beanConfig.setDescription("Api de la resource bailleur");
	        beanConfig.setSchemes(new String[]{"http"});
	        beanConfig.setHost("localhost:8080");
	        beanConfig.setBasePath("/rest");
	        beanConfig.setResourcePackage("com.bootcamp.resources");
		    beanConfig.setPrettyPrint(true);
	        beanConfig.setScan(true);

	    }
	
	@Override
	    public Set<Class<?>> getClasses() {
	        Set<Class<?>> resources = new java.util.HashSet<>();

	        resources.add(BailleurService.class);

		    resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
	        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);



		return resources;
	    }
}
