package tyche.config;


import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// equivalent du web.xml
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	public static final long MAX_FILE_SIZE = 1024 * 1024 * 1;//1MB
    public static final long MAX_REQUEST_SIZE = 2 * MAX_FILE_SIZE;//2MB
    private static final int FILE_SIZE_THRESHOLD = 0;
    
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { HibernateConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/services/*" };
	}

	@Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("", MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));    
    }
}