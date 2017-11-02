package clf.learning.winner.springbase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import clf.learning.winner.core.service.HelloWorldService;

/**
 * @author chenlongfei
  */
@ComponentScan(basePackages = {"clf.learning.winner.springbase"})
public class SpringBaseApp {
	
	public static void main(String [] args) {
		
		//AnnotationConfigApplicationContext 搭配上 @Configuration 和 @Bean 注解，可以替代传统的XML配置
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringBaseApp.class);
		
		HelloWorldService helloWorldService = context.getBean(HelloWorldService.class);
		
		System.out.println(helloWorldService.sayHello());
		
	}
	
	
}
