package clf.learning.winner.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author chenlongfei
  */
@SpringBootApplication // = @Configuration + @EnableAutoConfiguration + @ComponentScan
//默认以@SpringBootApplication所在包为basePackage = @ComponentScan(basePackages = {"clf.learning.winner.springboot"})
@Import(value= {})
public class SpringBootApp {
	
	public static void main(String [] args) {
		SpringApplication app = new SpringApplication(SpringBootApp.class);
		app.run(args);
	}

	
}
