package kz.astana.university.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        //If an application context hierarchy is not required, applications may return
        // all configuration via getRootConfigClasses() and return null from getServletConfigClasses().
        return new Class[0];
    }

    protected Class<?>[] getServletConfigClasses() {
        //for DispatcherServlet application context (Spring MVC infrastructure) configuration. Add classes where servlets configurated.
        return new Class[] {SpringConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"}; // Servlet Mappings type
    }

    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
}
/*
public abstract class AbstractAnnotationConfigDispatcherServletInitializer extends AbstractDispatcherServletInitializer
*/