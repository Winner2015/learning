package clf.learning.winner.springboot.web.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import clf.learning.winner.springboot.vo.ConvertorSampleVO;
import clf.learning.winner.springboot.vo.FormatterSampleVO;
import clf.learning.winner.springboot.vo.MessageVO;

/**
 * @author chenlongfei
 */
@Controller
@RequestMapping("/converter")
public class ConvertController {

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
	
	//使用Spring自带的转换器将String转换Date
	@ResponseBody
	@RequestMapping("/convertBySpring")
	public String convertBySpring(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  Date date) {
		System.out.println(date.getTime());
		
		return "OK";
	}
	
	//使用自定义的Formatter将String转换成FormatterSampleVO
	@ResponseBody
	@RequestMapping("/convertByCuntomFormatter")
	public String convertByCuntomFormatter(@RequestParam(name="sample") FormatterSampleVO sample) {
		System.out.println(JSON.toJSONString(sample, true));
		
		return "OK";
	}
	
	//使用自定义的Convertor将String转换成ConvertorSampleVO
	@ResponseBody
	@RequestMapping("/convertByCuntomConvertor")
	public String convertByCuntomConvertor(@RequestParam(name="sample") ConvertorSampleVO sample) {
		System.out.println(JSON.toJSONString(sample, true));
		
		return "OK";
	}
	
	
	//JacksonConvertor本质上是一个HttpMessageConverter，是从request的body体中获取json数据
	//然后转换成@RequestBody标注的参数，Spring组件不参与Jackson的转换过程，例如：
	//@DateTimeFormat是Spring的注解，对Jackson是不起作用的
	//Jackson如果想定制Date格式，需要实现JsonSerializer与JsonDeserializer接口
	//并在字段上使用
	@ResponseBody
	@RequestMapping("/convertByJacksonConvertor")
	public String convertByJacksonConvertor(@RequestBody FormatterSampleVO sample) {
		
		ObjectMapper mapper = new ObjectMapper();  
        String json = null;
		try {
			json = mapper.writeValueAsString(sample);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}  
        System.out.println(json);
		
		return "OK";
	}
	
}
