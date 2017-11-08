package clf.learning.winner.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import clf.learning.winner.springboot.vo.MessageVO;
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
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/clf/images/");  // = <mvc:resources mapping="/images/**" location="classpath:/clf/images/"/>  
		super.addResourceHandlers(registry);
		
		//还有一种全局配置方式，在 application.properties中添加：
		//spring.mvc.static-path-pattern=
		//spring.resources.static-locations=
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		/**
		 * 对静态资源的请求转发到容器缺省的servlet，而不使用DispatcherServlet 会把"/**"
		 * url,注册到SimpleUrlHandlerMapping的urlMap中,
		 * 把对静态资源的访问由HandlerMapping转到org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler处理并返回
		 */
		configurer.enable();  // =<mvc:default-servlet-handler/>  
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		super.addViewControllers(registry);
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		/**
		 * 配置内容裁决解析器ContentNegotiatingViewResolver
		 * 该解析器不进行具体视图的解析，而是管理注册的所有视图解析器，所有的视图会先经过它进行解析，然后由它来决定具体使用哪个解析器进行解析。
		 * 具体的映射规则是根据请求的media types来决定的
		 */
		
		configurer.favorParameter(false)
				.defaultContentType(MediaType.TEXT_HTML)  
				.mediaType("xml", MediaType.TEXT_XML)  
				.mediaType("json", MediaType.APPLICATION_JSON);  
		
		/**
		 * 默认配置见：ContentNegotiationManagerFactoryBean
		 * 
		 * order：如果存在多个viewResolver则order值小的被使用，如果没有合适的viewResolver则会使用另外的
		 * 
		 * favorPathExtension：判定MediaType的方式之一，是否启用扩展名支持，默认为true（支持），扩展名指的xxx.json、xxx.xml等形式, 
		 * 
		 * favorParameter：判定MediaType的方式之二，是否启用参数支持，默认为true（支持），即xxx?format=json、xxx?format=xml等形式，这里的参数名默认为'format'
		 * 
		 * ignoreAcceptHeader：判定MediaType的方式之三，是否忽略accept header，默认是false（不忽略），即根据请求的contentType:application/json判定
		 * 
		 * mediaTypes：配置扩展名到MediaType的映射
		 * 
		 * defaultViews：配置默认视图
		 */

	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		View jsonView = new MappingJackson2JsonView();  //json视图
		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(MessageVO.class);
		View xmlView = new MarshallingView(marshaller);  //xml视图
		
		registry.enableContentNegotiation(jsonView, xmlView);  //启用ContentNegotiatingViewResolver，默认视图为MappingJackson2JsonView
		
		
		
		registry.jsp("/WEB-INF/view/jsp/", ".jsp");  //例如，返回的视图名称是example，它会返回/WEB-INF/jsp/example.jsp给前端
//		等价于：
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//      viewResolver.setPrefix("/WEB-INF/views/");
//      viewResolver.setSuffix(".jsp");
//      registry.viewResolver(viewResolver);
		
		
		
	}

}
