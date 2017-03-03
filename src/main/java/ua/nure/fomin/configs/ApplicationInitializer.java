package ua.nure.fomin.configs;
import com.backendless.Backendless;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ua.nure.fomin.filter.AccessFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class ApplicationInitializer implements WebApplicationInitializer {

    private static final String SERVLET = "DispatcherServlet";

    private static final String PATH = "/";

    private static final String LOCATION = "ua.nure.fomin.configs";


    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.addFilter("filter", AccessFilter.class).addMappingForUrlPatterns(null,false,"/funBlog");
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(SERVLET, new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(PATH);

        Backendless.initApp("BA37451B-0CD4-ECDE-FF9E-6A1E669E6100","CECDA472-FED8-5EC3-FFA3-A65BCA9A2A00","v1");

    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(LOCATION);
        return context;
    }


}
