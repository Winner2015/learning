package clf.learning.winner.springboot.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author chenlongfei
 */
@XmlRootElement(name = "message")
public class MessageVO {
	private String to;
	private String from;
	private String content;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
