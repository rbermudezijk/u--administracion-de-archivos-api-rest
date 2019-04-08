package config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import controllers.AdminFileController;
import controllers.TestController;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	@Bean
	public AdminFileController adminFileController(){ return new AdminFileController(); }
	
	@Bean
	public TestController testController(){ return new TestController(); }
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
	{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());

        // objectMapper.setDateFormat(new StdDateFormat());	
        objectMapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setViewClass(JstlView.class);
		vr.setPrefix("/components/");
		vr.setSuffix(".template.html");
		
		System.out.println(vr.getRedirectHosts());
		return vr;
	}
}
