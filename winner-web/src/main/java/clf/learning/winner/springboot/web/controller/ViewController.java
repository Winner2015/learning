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
	
	
	@RequestMapping("/message/{id}")
	public String message(@PathVariable("id") Long id, Model model) {
		MessageVO message = new MessageVO();
		message.setId(id);
		message.setName("陈龙飞");
		message.setMessage("师傅被妖怪抓走了");
		
		model.addAttribute("data", message);
		
		return "message";
	}
	
	@RequestMapping("/message2")
	public String message2() {
		return "no-matched-view";
	}
	
}
