package clf.learning.winner.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import clf.learning.winner.springboot.web.interceptor.AccessLogInterceptor;

/**
 * @author chenlongfei
 */
@Configuration
@EnableWebMvc // = <mvc:annotation-driven/>，不开启时，Spring Boot默认使用 WebMvcAutoConfiguration中的各种配置
public class CustomWebMvcConfig extends WebMvcConfigurerAdapter {

	//通过WebMvcConfigurerAdapter可以实现spring-mvc.xml的配置功能
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 添加拦截器
		registry.addInterceptor(new AccessLogInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		/**
		 * 默认的静态资源处理器为：ResourceHttpRequestHandler 
		 * 默认配置的 "/**" 映射到"/static"（或"/public"、"/resources"、"/META-INF/resources"），见ResourceProperties
		 * 默认配置的 "/webjars/**" 映射到 "classpath:/META-INF/resources/webjars/", 见ResourceProperties
		 * 例如,请求"/images/head.jpg"，会默认去"src/main/resources/static/images/head.jpg"下面寻找
		 * 如果资源路径与默认不符，需要新增resourHandler来处理
		 */
		
		//用户访问"/images/"下的静态资源时，会转到实际目录"/clf/images/"
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/clf/images/");
		super.addResourceHandlers(registry);
		
		//还有一种全局配置方式，在 application.properties中添加：
		//spring.mvc.static-path-pattern=
		//spring.resources.static-locations=
	}

	//对静态资源的请求转发到容器缺省的servlet，而不使用DispatcherServlet
	//会把"/**" url,注册到SimpleUrlHandlerMapping的urlMap中,
	//把对静态资源的访问由HandlerMapping转到org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler处理并返回
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();  // =<mvc:default-servlet-handler/>  
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		super.addViewControllers(registry);
	}

}
