package clf.learning.winner.springboot.core.service;

import org.springframework.stereotype.Service;

import clf.learning.winner.core.service.HelloWorldService;

/**
 * @author chenlongfei
  */
@Service("helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService{

	@Override
	public String sayHello() {
		
		return "Hello World! ---from spring boot service";
	}

}
