package clf.learning.winner.controller.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clf.learning.winner.core.spring.base.MessagePrinter;
import clf.learning.winner.core.spring.base.MessageService;

/**
 * @author chenlongfei
  */
@RestController
public class Controller {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessagePrinter messagePrinter;
	
	@RequestMapping("/")
	String home() {
		messagePrinter.printMessage();
		return messageService.getMessage();
		//return "Hello World!";
	}
}
