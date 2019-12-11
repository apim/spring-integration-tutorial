package apim.github.tutorial;

import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import apim.github.tutorial.Employee.LevelEnum;

@Component
public class DataTranslator {

	@Transformer
	public Employee convertData(Person p) {
		Employee e = new Employee();
		e.setFullName(p.getFirstName() + " " + p.getLastName());
		if (p.getAge() <= 30) {
			e.setBand(LevelEnum.LOW);
		} else if (p.getAge() > 30 && p.getAge() < 60) {
			e.setBand(LevelEnum.MEDIUM);
		} else {
			e.setBand(LevelEnum.HIGH);
		}
		return e;
	}

}
