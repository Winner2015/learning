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
@RequestMapping("/converter")
public class HTTPMessageConvertController {

	@ResponseBody
	@RequestMapping(value = "/sendMessage", consumes = { "application/clf" })
	public String sendMessage(@RequestBody MessageVO message) {

		return String.format("%s收到来自%s发送的消息：%s", message.getTo(), message.getFrom(), message.getContent());
	}
	
	@ResponseBody
	@RequestMapping(value = "/receiveMessage", produces = { "application/clf" })
	public MessageVO receiveMessage() {
		MessageVO message = new MessageVO();
		message.setFrom("沙悟净");
		message.setTo("二师兄");
		message.setContent("师傅被妖怪抓走了！");
		
		return message;
	}
	
}
