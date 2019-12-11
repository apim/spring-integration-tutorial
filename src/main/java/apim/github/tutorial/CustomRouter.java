package apim.github.tutorial;

import java.util.ArrayList;
import java.util.List;

import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

@Component
public class CustomRouter {

	@Router
	public List<String> routeMessage(Item msg) {
		List<String> rsp = new ArrayList<>();
		if (msg.getDimension() > 100) {
			rsp.add("alternateChannel");
		} else {
			rsp.add("outputChannel");
			rsp.add("alternateChannel");
		}
		return rsp;
	}

}
