package clf.learning.winner.springboot.web.convertor;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import clf.learning.winner.springboot.vo.MessageVO;

/**
 * @author chenlongfei
  */
public class ClfConvert extends AbstractHttpMessageConverter<MessageVO> {
	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	public ClfConvert() {
		super(new MediaType("application", "clf", DEFAULT_CHARSET));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(MessageVO.class);
	}

	@Override
	protected MessageVO readInternal(Class<? extends MessageVO> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		String requestBody = StreamUtils.copyToString(inputMessage.getBody(), DEFAULT_CHARSET);
		String [] messageSlices = requestBody.split(":");
		MessageVO messageVO = new MessageVO();
		messageVO.setFrom(messageSlices[0]);
		messageVO.setTo(messageSlices[1]);
		messageVO.setContent(messageSlices[2]);
		
		return messageVO;
	}

	@Override
	protected void writeInternal(MessageVO messageVO, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String message = messageVO.getFrom() + ":" + messageVO.getTo() + ":" + messageVO.getContent(); 
		outputMessage.getBody().write(message.getBytes());
	}

}
