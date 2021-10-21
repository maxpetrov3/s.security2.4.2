package web.configuration;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class RootConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebConfig.class,
                JpaConfig.class
        };
    }

    protected String[] getServletMappings() {
        return new String[]{
                "/login"
        };
    }
}
