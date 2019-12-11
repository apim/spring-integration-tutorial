package apim.github.tutorial;

import java.util.Collection;

import org.springframework.integration.annotation.Aggregator;
import org.springframework.stereotype.Component;

import apim.github.tutorial.Field.FieldDescriptor;

@Component
public class CustomAggregator {

	@Aggregator
	public Customer aggregateMessages(Collection<Field> fields) {
		Customer c = new Customer();
		for (Field field : fields) {
			if (field.getDesc() == FieldDescriptor.NAME) {
				c.setName(field.getValue().toString());
			}
			if (field.getDesc() == FieldDescriptor.ADDRESS) {
				c.setAddress(field.getValue().toString());
			}
			if (field.getDesc() == FieldDescriptor.BALANCE) {
				c.setBalance(Double.valueOf(field.getValue().toString()));
			}
		}
		return c;
	}

}
