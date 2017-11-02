package clf.learning.winner.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clf.learning.winner.core.service.HelloWorldService;

/**
 * @author chenlongfei
  */
@RestController // = @ResponseBody ＋ @Controller
public class Controller {
	
	@Autowired
	private HelloWorldService helloWorldService;
	
	
	@RequestMapping("/helloWorld")
	String home() {
		return helloWorldService.sayHello();
	}
}
