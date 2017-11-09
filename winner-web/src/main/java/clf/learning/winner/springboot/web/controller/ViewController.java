package clf.learning.winner.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import clf.learning.winner.springboot.vo.MessageVO;

/**
 * @author chenlongfei
  */
@Controller
@RequestMapping("/view")
public class ViewController {
	
	
	@RequestMapping("/message/{to}")
	public String message(@PathVariable("to") String to, Model model) {
		MessageVO message = new MessageVO();
		message.setFrom("沙悟净");
		message.setTo(to);
		message.setContent("师傅被妖怪抓走了！");
		
		model.addAttribute("message", message);
		
		return "message";
	}
	
	
	@RequestMapping("/messageJspOnly")
	public String messageJsp(Model model) {
		MessageVO message = new MessageVO();
		message.setFrom("沙悟净");
		message.setTo("大师兄");
		message.setContent("师傅被妖怪抓走了！");
		
		model.addAttribute("message", message);
		
		return "messageJspOnly";  //该视图名只能匹配上jsp页面，而没有messageJspOnly.ftl页面
	}
	
	
	@RequestMapping("messageFtlOnly")
	public String messageFtl(Model model) {
		MessageVO message = new MessageVO();
		message.setFrom("沙悟净");
		message.setTo("二师兄");
		message.setContent("师傅被妖怪抓走了！");
		
		model.addAttribute("message", message);
		
		return "messageFtlOnly";  //该视图名只能匹配上ftl页面，而没有messageFtlOnly.jsp页面
	}
	
	@RequestMapping("messageNoPage")
	public String messageNoPage(Model model) {
		MessageVO message = new MessageVO();
		message.setFrom("沙悟净");
		message.setTo("二师兄");
		message.setContent("师傅被妖怪抓走了！");
		
		model.addAttribute("message", message);
		
		return "messageNoPage";  //匹配不上任何页面
	}
	
}
