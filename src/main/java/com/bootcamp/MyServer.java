/**
 * les deux methodes web xml et application class ne sont pas adaptes pour un embedded jetty server
 * on le fait par la creation d'une class destinée au lancement du serveur
 */
package com.bootcamp;

import com.bootcamp.resources.BailleurService;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import java.net.URISyntaxException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author soul
 */
public class MyServer {

	private static final Logger LOG = LoggerFactory.getLogger(MyServer.class);

	private static final int SERVER_PORT = 8082;

	public static void main(String[] args) throws URISyntaxException {

		try {
			// Workaround for resources from JAR files
			Resource.setDefaultUseCaches( false );

			// Build the Swagger Bean.
			buildSwagger();

			// Holds handlers
			final HandlerList handlers = new HandlerList();

			// Handler for Swagger UI, static handler.
			handlers.addHandler( buildSwaggerUI() );

			// Handler for Entity Browser and Swagger
			handlers.addHandler( buildContext() );

			// Start server
			Server server = new Server( SERVER_PORT );
			server.setHandler( handlers );
			server.start();
			server.join();
		} catch ( Exception e ) {
			LOG.error( "There was an error starting up the Bailleur proframme ", e );
		}

	}

	private static void buildSwagger() {
		// This configures Swagger
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setResourcePackage(BailleurService.class.getPackage().getName());        //"com.bootcamp.rest.controllers"
		beanConfig.setScan(true);
		beanConfig.setBasePath("/rest");
		beanConfig.setDescription("Programme Rest API to access all the programs ressources");
		beanConfig.setTitle("Bailleurs");
//        System.out.println(BailleurRestController.class.getPackage().getName() );
	}

	// This starts the Swagger UI at http://localhost:9090/docs
	private static ContextHandler buildSwaggerUI() throws URISyntaxException {
		//to configure swagger UI

		final ResourceHandler swaggerUIResourceHandler = new ResourceHandler();
		swaggerUIResourceHandler.setResourceBase( MyServer.class.getClassLoader().getResource("webapp" ).toURI().toString() );
		System.out.println(MyServer.class.getClassLoader().getResource("webapp" ).toURI().toString());
		final ContextHandler swaggerUIContext = new ContextHandler();
		swaggerUIContext.setContextPath( "/docs/" );
		swaggerUIContext.setHandler( swaggerUIResourceHandler );

		return swaggerUIContext;
	}

	private static ContextHandler buildContext() {


		ResourceConfig resourceConfig = new ResourceConfig();
		// io.swagger.jaxrs.listing loads up Swagger resources
		resourceConfig.packages( BailleurService.class.getPackage().getName(),ApiListingResource.class.getPackage().getName() );
		ServletContainer servletContainer = new ServletContainer( resourceConfig );
		ServletHolder servletHolder = new ServletHolder( servletContainer );
		ServletContextHandler servletContextHandler = new ServletContextHandler( ServletContextHandler.SESSIONS );
       // servletContextHandler.setContextPath( "/" );
        servletContextHandler.addServlet( servletHolder, "/*" );

		return servletContextHandler;
	}

}