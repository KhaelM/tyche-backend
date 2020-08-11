package tyche.config;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "tyche" })
public class WebMvcConfig implements WebMvcConfigurer {

	// This function makes lazy load working with jackson
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		Hibernate5Module module = new Hibernate5Module(); // or Hibernate4Module ... depends on hibernate version you
															// are using

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(module);

		converters.add(new MappingJackson2HttpMessageConverter(mapper));

	}

	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		return source;
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Register resource handler for CSS and JS
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/statics/")
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

		// Register resource handler for images
		registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/")
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
	   return new StandardServletMultipartResolver() {
	     @Override
	     public boolean isMultipart(HttpServletRequest request) {
	        String method = request.getMethod().toLowerCase();
	        //By default, only POST is allowed. Since this is an 'update' we should accept PUT.
	        if (!Arrays.asList("put", "post").contains(method)) {
	           return false;
	        }
	        String contentType = request.getContentType();
	        return (contentType != null &&contentType.toLowerCase().startsWith("multipart/"));
	     }
	   };
	}
}