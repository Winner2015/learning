package clf.learning.winner.springbase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import clf.learning.winner.springbase.aspect.RestaurantCustomer;

/**
 * @author chenlongfei
  */
@ComponentScan(basePackages = {"clf.learning.winner.springbase"})
@EnableAspectJAutoProxy
public class SpringBaseApp {
	
	public static void main(String [] args) throws Exception {
		
		//AnnotationConfigApplicationContext 搭配上 @Configuration 和 @Bean 注解，可以替代传统的XML配置
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringBaseApp.class);
		
//		HelloWorldService helloWorldService = context.getBean(HelloWorldService.class);
//		
//		helloWorldService.sayHello();
//		
		RestaurantCustomer customer = (RestaurantCustomer)context.getBean("restaurantCustomer");
		customer.eat();
		
//		BaseService sampleService = context.getBean(BaseService.class);
//		System.out.println("SampleService实现类： " + sampleService.getClass().getSimpleName());
//		sampleService.doBusiness();
		
	}
	
	
}
