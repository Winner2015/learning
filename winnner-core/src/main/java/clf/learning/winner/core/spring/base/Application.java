package clf.learning.winner.core.spring.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenlongfei
  */
@Configuration
@ComponentScan
public class Application {
	
	@Bean
	MessageService getMessageService() {
		return new MessageService() {
			
			@Override
			public String getMessage() {
				return "Hello World!";
			}
		};
	}
	
	public static void main(String [] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		MessagePrinter printer = context.getBean(MessagePrinter.class);
		printer.printMessage();
	}
	
	
}
