package clf.learning.winner.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import clf.learning.winner.springboot.vo.MessageVO;

/**
 * @author chenlongfei
  */
@Controller
@RequestMapping("/test")
public class TestController {
	
	@ResponseBody
	@RequestMapping(value = "/receiveMessage", produces = {"application/clf"})
	public MessageVO receiveMessage(@RequestBody MessageVO message) {
		
		MessageVO message2 = new MessageVO();
		message2.setFrom("111");
		message2.setTo("22");
		message2.setContent("OK");
		
		return message2;
	}
}
