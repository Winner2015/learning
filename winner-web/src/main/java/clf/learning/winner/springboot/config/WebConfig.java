package clf.learning.winner.springboot.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import clf.learning.winner.springboot.web.servlet.ServletFromContextInitializer;
import clf.learning.winner.springboot.web.servlet.ServletFromRegistrationBean;

/**
 * @author chenlongfei
  */
@Configuration
@ServletComponentScan  //使用@ServletComponentScan注解，Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册
public class WebConfig {
	
	@Bean
	public ServletRegistrationBean registerServlet() {
		//代码注册通过ServletRegistrationBean、 FilterRegistrationBean 和 ServletListenerRegistrationBean 获得控制
		return new ServletRegistrationBean(new ServletFromRegistrationBean(), "/ServletFromRegistrationBean");
	}
	
	@Bean
	public ServletContextInitializer registerServletByInitializer() {
		return new ServletFromContextInitializer();
	}
	
//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//		
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//        
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(filter);
//        registration.setOrder(-1);
//        
//        return registration;
//	}
	
}
