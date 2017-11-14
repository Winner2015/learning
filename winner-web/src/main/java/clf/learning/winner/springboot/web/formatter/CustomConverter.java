package clf.learning.winner.springboot.web.formatter;

import org.springframework.core.convert.converter.Converter;

import clf.learning.winner.springboot.vo.ConvertorSampleVO;

/**
 * @author chenlongfei
  */
public class CustomConverter implements Converter<String, ConvertorSampleVO>{
	
	//将定制化的字符串转换成ConvertorSampleVO
	//http://localhost:8080/formatter/convertByCuntomConvertor?sample=123456~陈龙飞
	@Override
	public ConvertorSampleVO convert(String source) {
		String [] valueArr = source.split("~");
		ConvertorSampleVO sample = new ConvertorSampleVO();
		sample.setId(Long.parseLong(valueArr[0]));
		sample.setName(valueArr[1]);
		return sample;
	}

}
