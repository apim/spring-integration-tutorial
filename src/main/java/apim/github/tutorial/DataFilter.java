package apim.github.tutorial;

import org.springframework.integration.annotation.Filter;
import org.springframework.stereotype.Component;

@Component
public class DataFilter {

	@Filter
	public boolean isValidOrder(Order msg) {
		return msg.getAmount() > 1000;
	}

}
