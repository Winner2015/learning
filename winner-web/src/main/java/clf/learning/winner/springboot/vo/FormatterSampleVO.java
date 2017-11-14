package clf.learning.winner.springboot.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.number.CurrencyStyleFormatter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import clf.learning.winner.springboot.web.converter.CustomJacksonDateDesirializer;
import clf.learning.winner.springboot.web.converter.CustomJacksonDateSerializer;

/**
 * @author chenlongfei
  */
public class FormatterSampleVO {
	
	@JsonSerialize(using = CustomJacksonDateSerializer.class)
	@JsonDeserialize(using = CustomJacksonDateDesirializer.class)
	private Date dateValue;
	
	private Timestamp timestampValue;
	
	private Double doubleValue;
	
	private BigDecimal bigDecimalValue;

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public Timestamp getTimestampValue() {
		return timestampValue;
	}

	public void setTimestampValue(Timestamp timestampValue) {
		this.timestampValue = timestampValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public BigDecimal getBigDecimalValue() {
		return bigDecimalValue;
	}

	public void setBigDecimalValue(BigDecimal bigDecimalValue) {
		this.bigDecimalValue = bigDecimalValue;
	}

}
