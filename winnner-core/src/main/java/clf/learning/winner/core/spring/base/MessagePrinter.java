package clf.learning.winner.core.spring.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenlongfei
  */
@Component
public class MessagePrinter {
	
	@Autowired
	private MessageService messageService;
	
	public void printMessage() {
		System.out.println("chen long fei, " + messageService.getMessage());
	}
}
