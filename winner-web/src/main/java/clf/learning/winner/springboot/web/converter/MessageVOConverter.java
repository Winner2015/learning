package clf.learning.winner.springboot.web.converter;

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
public class MessageVOConverter extends AbstractHttpMessageConverter<MessageVO> {
	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	public MessageVOConverter() {
		//该转换器绑定自定义mime类型：application/clf
		super(new MediaType("application", "clf", DEFAULT_CHARSET));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		//只支持MessageVO类
		return clazz.isAssignableFrom(MessageVO.class);
	}
	
	//将HttpInputMessage的body体中读取信息，转换成MessageVO
	//满足以下条件会被触发：
	//（1）request的contentType为"application/clf"
	//（2）对应的controller方法入参包含MessageVO且有@RequestBody注解
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
	
	//将MessageVO转换成字节，写入HttpOutputMessage的body体中
	//满足以下条件会被触发：
	//（1）request的Accept类型为"application/clf"（若没有指定，可以通过@RequestMapping的produces设置）
	//（2）对应的controller方法有@ResponseBody注解
	@Override
	protected void writeInternal(MessageVO messageVO, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String message = messageVO.getFrom() + ":" + messageVO.getTo() + ":" + messageVO.getContent(); 
		outputMessage.getBody().write(message.getBytes());
	}

}
