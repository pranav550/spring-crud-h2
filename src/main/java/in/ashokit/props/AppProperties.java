package in.ashokit.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

	
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "plan-api")
@Configuration
public class AppProperties {
	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}

	private Map<String, String> messages = new HashMap<>();


}
