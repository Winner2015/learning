package clf.learning.winner.springboot.web.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import clf.learning.winner.springboot.vo.ConvertorSampleVO;
import clf.learning.winner.springboot.vo.FormatterSampleVO;

/**
 * @author chenlongfei
  */
@Controller
@RequestMapping("/formatter")
public class FormatterController {
	
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
	
	
	@ResponseBody
	@RequestMapping("/string2DateByJsonConverter")
	public String string2DateByJsonConverter(@RequestBody FormatterSampleVO sample) {
		System.out.println(JSON.toJSONString(sample, true));
		
		return "OK";
	}
}
