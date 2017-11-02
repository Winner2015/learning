package clf.learning.winner.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenlongfei
  */
@SpringBootApplication // = @Configuration + @EnableAutoConfiguration + @ComponentScan
//默认以@SpringBootApplication所在包为basePackage = @ComponentScan(basePackages = {"clf.learning.winner.springboot"})
public class SpringBootApp {
	
	public static void main(String [] args) {
		SpringApplication app = new SpringApplication(SpringBootApp.class);
		app.run(args);
	}
	
}
