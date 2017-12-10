package clf.learning.winner.springbase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import clf.learning.winner.core.service.HelloWorldService;

/**
 * @author chenlongfei
  */
@Configuration
public class SpringBaseConfig {
	
	@Bean //标注 @Bean的方法的返回值会被识别为 Spring Bean，并注册到容器中，受 IoC 容器管理
	public HelloWorldService getHelloWorldService() {
		return new HelloWorldService(){

			@Override
			public String sayHello() {
				System.out.println("Hello World! ---from SpringBaseConfig");
				return "Hello World! ---from SpringBaseConfig";
			}};
	}
	
}
