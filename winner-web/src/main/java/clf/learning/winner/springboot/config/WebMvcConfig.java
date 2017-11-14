package clf.learning.winner.springboot.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import clf.learning.winner.springboot.vo.MessageVO;
import clf.learning.winner.springboot.web.converter.CuntomFormatter;
import clf.learning.winner.springboot.web.converter.CustomConverter;
import clf.learning.winner.springboot.web.converter.MessageVOConverter;
import clf.learning.winner.springboot.web.interceptor.AccessLogInterceptor;

/**O
 * @author chenlongfei
 */
@Configuration
@EnableWebMvc // = <mvc:annotation-driven/>，不开启时，Spring Boot默认使用 WebMvcAutoConfiguration中的各种配置
public class WebMvcConfig extends WebMvcConfigurerAdapter {

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
		 * 这个Handler也是用来处理静态文件的，它会尝试映射"/*"
		 * 当DispatcherServelt映射/时（/ 和/* 是有区别的），并且没有找到合适的Handler来处理请求时，
		 * 就会交给DefaultServletHttpRequestHandler 来处理
		 */
		configurer.enable();  // =<mvc:default-servlet-handler/> 
		
		/**
		 * '/' 和 '/*'的区别：
		 * '/' 将会覆盖容器的default servlet, 找不到匹配的URL，才会交给该Servlet处理
		 * '/*'会拦截所有URL
		 * 
		 * DispatcherServlet应该使用'/'
		 */
		
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//适用于不需要controller做数据处理，直接跳转页面的情况
		registry.addViewController("/welcome").setViewName("welcome");
		registry.addViewController("/httpMessageConvert").setViewName("httpMessageConvert");
		registry.addViewController("/formatter").setViewName("formatter");
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		/**
		 * 配置内容裁决解析器ContentNegotiatingViewResolver
		 * 该解析器不进行具体视图的解析，而是管理注册的所有视图解析器，所有的视图会先经过它进行解析，然后由它来决定具体使用哪个解析器进行解析。
		 * 具体的映射规则是根据请求的media types来决定的
		 */
		
		configurer.favorParameter(false)
				.mediaType("xml", MediaType.APPLICATION_XML)  
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
		
		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();  //freeMarker视图
		freeMarkerViewResolver.setCache(true);
		freeMarkerViewResolver.setPrefix("");
		freeMarkerViewResolver.setSuffix(".ftl");
		freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
		//freeMarkerViewResolver.setOrder(0);
		registry.viewResolver(freeMarkerViewResolver);
		
		registry.jsp("/WEB-INF/view/jsp/", ".jsp");  //jsp视图, 例如，返回的视图名称是example，它会返回/WEB-INF/jsp/example.jsp给前端
//		等价于：
//		InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();  //绑定JstlView，DEFAULT_CONTENT_TYPE = "text/html;charset=ISO-8859-1"
//		jspViewResolver.setPrefix("/WEB-INF/view/jsp/");
//		jspViewResolver.setSuffix(".jsp");
//		jspViewResolver.setContentType("text/html;charset=UTF-8");
////		jspViewResolver.setOrder(0);
//		registry.viewResolver(jspViewResolver);
		
		View jsonView = new MappingJackson2JsonView();  //json视图, MappingJackson2JsonView， DEFAULT_CONTENT_TYPE = "application/json"
		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(MessageVO.class);
		View xmlView = new MarshallingView(marshaller);  //xml视图, MarshallingView，DEFAULT_CONTENT_TYPE = "application/xml"
		
		registry.enableContentNegotiation(jsonView, xmlView);  //启用ContentNegotiatingViewResolver，添加默认视图：json与xml
		
		/**
		 * 上面一共添加了四种视图，注册到ContentNegotiatingViewResolver中：
		 * （1）freeMarker视图——》mediaType：text/html
		 * （2）jsp视图——》mediaType：text/html
		 * （3）json视图——》mediaType：application/json
		 * （4）xml视图——》mediaType：application/xml
		 * 
		 * 假如handler返回视图名为："example"
		 * ContentNegotiatingViewResolver会遍历viewResolvers，依次组装出对应的freeMarker视图（有可能组装失败，比如example.ftl文件不存在）与jsp视图
		 * 然后添加上将默认的json视图、xml视图，得到一组候选视图列表
		 * 最后根据请求的mediaType(根据路径后缀或accept请求头来确定)，挑选一个合适的view返回
		 */
	}
	
	@Bean
	public FreeMarkerConfigurer freeMarkerConfiguer() {
		FreeMarkerConfigurer freeMarkerConfiguer = new FreeMarkerConfigurer();
		freeMarkerConfiguer.setTemplateLoaderPath("classpath:/WEB-INF/view/freemarker/");  //默认：classpath:/templates
		freeMarkerConfiguer.setDefaultEncoding("UTF-8");
	
		return freeMarkerConfiguer;
	}

//	@Bean  
//	public FreeMarkerViewResolver freeMarkerViewResolver() {
//		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
//		freeMarkerViewResolver.setCache(true);
//		freeMarkerViewResolver.setPrefix("");
//		freeMarkerViewResolver.setSuffix(".ftl");
//		freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
////		freeMarkerViewResolver.setOrder(-1);
//		
//		return freeMarkerViewResolver;
//	}
	
//	@Bean
//	public InternalResourceViewResolver internalResourceViewResolver() {
//		InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();  //绑定JstlView，DEFAULT_CONTENT_TYPE = "text/html;charset=ISO-8859-1"
//		jspViewResolver.setPrefix("/WEB-INF/view/jsp/");
//		jspViewResolver.setSuffix(".jsp");
//		jspViewResolver.setContentType("text/html;charset=UTF-8");
////		jspViewResolver.setOrder(0);
//
//		return jspViewResolver;
//	}
	
	//在不使用order的情况下，resolver的注册顺序会决定解析view的优先权，比如handler返回的视图名为'example',
	//同时存在example.jsp与example.ftl，freeMarkerViewResolver先注册则会返回example.ftl，反之，则返回example.jsp
	
	//通过此方法添加转换器，会覆盖默认的转换器列表
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		StringHttpMessageConverter stringConvertor = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//		converters.add(stringConvertor);
//		
//		MessageVOConvert messageVOconvertor = new MessageVOConvert();
//		converters.add(messageVOconvertor);
	}
	
	//通过此方法添加转换器，不会覆盖默认的转换器列表，而是追加至原列表
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		MessageVOConverter messageVOConverter = new MessageVOConverter();
		converters.add(messageVOConverter);
		
		//重置默认converter的属性
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof StringHttpMessageConverter) {
				((StringHttpMessageConverter) converter).setDefaultCharset(Charset.forName("UTF-8"));
			}
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				((MappingJackson2HttpMessageConverter) converter).getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			}
		}
	}
	
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		//Formatter与Convertor都是转换接口，都会被适配成GenericConverter，加入到GenericConversionService.converters中
		
		registry.addFormatter(new CuntomFormatter());  //Formatter实现了Printer、Parser接口，会被适配成两个GenericConverter：String——》FormatterSampleVO，FormatterSampleVO——》String
		
		registry.addConverter(new CustomConverter());  //Convertor会被适配成一个GenericConverter: String——》ConvertorSampleVO
	}
}
