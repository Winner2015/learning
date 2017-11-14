package clf.learning.winner.springboot.web.converter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.format.Formatter;

import clf.learning.winner.springboot.vo.FormatterSampleVO;

/**
 * @author chenlongfei
  */
public class CuntomFormatter implements Formatter<FormatterSampleVO>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Override
	public String print(FormatterSampleVO vo, Locale locale) {
		return "print FormatterSampleVO";
	}

	//将定制化的字符串转换成FormatterSampleVO
	//http://localhost:8080/formatter/convertByCuntomFormatter?sample=2017-11-14 12:00:00~2017-11-14 13:00:00~123456789~12345.6789
	@Override
	public FormatterSampleVO parse(String text, Locale locale) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT);
		
		String [] valueArr = text.split("~");
		
		FormatterSampleVO vo = new FormatterSampleVO();
		vo.setDateValue(format.parse(valueArr[0]));
		vo.setTimestampValue(new Timestamp(format.parse(valueArr[1]).getTime()));
		vo.setDoubleValue(Double.parseDouble(valueArr[2]));
		vo.setBigDecimalValue(new BigDecimal(valueArr[3]));
		
		return vo;
	}
	

}
