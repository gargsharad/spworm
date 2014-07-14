package com.spworm.config.initialize;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.spworm.config.infrastructure.PersistenceConfig;
import com.spworm.config.infrastructure.PropertiesLoader;
import com.spworm.config.web.ControllerConfiguration;

public class ApplicationInitializer implements WebApplicationInitializer {
    
    // These classes contains bean wiring information
    private static final Class<?>[] configurationClasses = new Class<?>[] {PropertiesLoader.class, PersistenceConfig.class};
	//TestDataContextConfiguration.class, InfrastructureContextConfiguration.class };

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
	registerListener(servletContext);
	registerDispatcherServlet(servletContext);
    }

    private void registerDispatcherServlet(ServletContext servletContext) {
	AnnotationConfigWebApplicationContext dispatcherContext = createContext(ControllerConfiguration.class);
	DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
	ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
	dispatcher.setLoadOnStartup(1);
	dispatcher.addMapping("/web/*");

    }

    private void registerListener(ServletContext servletContext) {
	AnnotationConfigWebApplicationContext rootContext = createContext(configurationClasses);
	servletContext.addListener(new ContextLoaderListener(rootContext));
	servletContext.addListener(new RequestContextListener());
    }

    private AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClasses) {
	AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	context.register(annotatedClasses);
	return context;
    }

}
