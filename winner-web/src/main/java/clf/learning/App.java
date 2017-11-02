package clf.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chenlongfei
  */
@SpringBootApplication
//@ComponentScan(basePackages = {"clf.learning"})
public class App {
	
	public static void main(String [] args) {
		SpringApplication app = new SpringApplication(App.class);
		//app.setWebEnvironment(true);
		app.run(args);
	}
	
}
