package apim.github.tutorial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.integration.annotation.Splitter;
import org.springframework.stereotype.Component;

import apim.github.tutorial.Field.FieldDescriptor;

@Component
public class CustomSplitter {

	@Splitter
	public Collection<Field> splitMessage(Customer c) {
		List<Field> messages = new ArrayList<>();
		Field field = new Field(FieldDescriptor.NAME, c.getName());
		messages.add(field);
		field = new Field(FieldDescriptor.ADDRESS, c.getAddress());
		messages.add(field);
		field = new Field(FieldDescriptor.BALANCE, c.getBalance());
		messages.add(field);
		return messages;
	}

}
