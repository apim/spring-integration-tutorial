package apim.github.tutorial;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

public class TestCode {

	public static void main(String args[]) {
		testTransformer();
		testFilter();
		testRouter();
		testSplitter();
		testAggregator();
	}

	private static void testTransformer() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
		MessageChannel channel = (MessageChannel) ctx.getBean("inputChannel");
		PollableChannel outputChannel = (PollableChannel) ctx.getBean("outputChannel");

		Person p = new Person();
		p.setFirstName("Joe");
		p.setLastName("Walsh");
		p.setAge(65);
		Message<Person> msg = MessageBuilder.withPayload(p).build();

		channel.send(msg);

		Message<?> msgRcvd = outputChannel.receive();
		Employee e = (Employee) msgRcvd.getPayload();
		System.out.println("Message received : " + e.getFullName() + ", " + e.getBand());
		ctx.close();
	}

	private static void testFilter() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
		MessageChannel channel = (MessageChannel) ctx.getBean("inputChannel");
		PollableChannel outputChannel = (PollableChannel) ctx.getBean("outputChannel");

		Order o = new Order();
		o.setId(1);
		o.setAmount(800);
		Message<Order> msg = MessageBuilder.withPayload(o).build();

		channel.send(msg);
		Message<?> msgRcvd = outputChannel.receive(1000);
		System.out.println("Message received : " + msgRcvd);

		o.setId(2);
		o.setAmount(1200);
		msg = MessageBuilder.withPayload(o).build();

		channel.send(msg);
		msgRcvd = outputChannel.receive();
		System.out.println("Message received : " + (Order) msgRcvd.getPayload());

		ctx.close();
	}

	private static void testRouter() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
		MessageChannel channel = (MessageChannel) ctx.getBean("inputChannel");
		PollableChannel outputChannel = (PollableChannel) ctx.getBean("outputChannel");
		PollableChannel alternateChannel = (PollableChannel) ctx.getBean("alternateChannel");

		Item it = new Item();
		it.setName("item-1");
		it.setDimension(150);
		Message<Item> msg = MessageBuilder.withPayload(it).build();

		channel.send(msg);
		Message<?> msgRcvd1 = outputChannel.receive(1000);
		Message<?> msgRcvd2 = alternateChannel.receive();
		System.out.println("Message received at outputChannel: " + msgRcvd1);
		System.out.println("Message received at alternateChannel: " + (Item) msgRcvd2.getPayload());

		it.setName("item-2");
		it.setDimension(50);
		msg = MessageBuilder.withPayload(it).build();

		channel.send(msg);
		msgRcvd1 = outputChannel.receive();
		msgRcvd2 = alternateChannel.receive();
		System.out.println("Message received at outputChannel: " + (Item) msgRcvd2.getPayload());
		System.out.println("Message received at alternateChannel: " + (Item) msgRcvd2.getPayload());

		ctx.close();
	}

	private static void testSplitter() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
		MessageChannel channel = (MessageChannel) ctx.getBean("inputChannel");
		PollableChannel outputChannel = (PollableChannel) ctx.getBean("outputChannel");

		Customer cust = new Customer();
		cust.setName("Robert");
		cust.setAddress("City Quarter Apartments, Athlone");
		cust.setBalance(1350.75);
		Message<Customer> msg = MessageBuilder.withPayload(cust).build();

		channel.send(msg);
		Message<?> msgRcvd1 = outputChannel.receive();
		Message<?> msgRcvd2 = outputChannel.receive();
		Message<?> msgRcvd3 = outputChannel.receive();
		System.out.println("Splitted message at outputChannel: " + msgRcvd1.getPayload());
		System.out.println("Splitted message at outputChannel: " + msgRcvd2.getPayload());
		System.out.println("Splitted message at outputChannel: " + msgRcvd3.getPayload());

		ctx.close();
	}

	private static void testAggregator() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
		MessageChannel channel = (MessageChannel) ctx.getBean("inputChannel");
		PollableChannel aggregatorOutputChannel = (PollableChannel) ctx.getBean("aggregatorOutputChannel");

		Customer cust = new Customer();
		cust.setName("Robert");
		cust.setAddress("City Quarter Apartments, Athlone");
		cust.setBalance(1350.75);
		Message<Customer> msg = MessageBuilder.withPayload(cust).build();

		channel.send(msg);
		Message<?> msgRcvd = aggregatorOutputChannel.receive();
		Customer c = (Customer) msgRcvd.getPayload();
		System.out.println("Aggregated response : " + c.getName() + "-" + c.getAddress() + "-" + c.getBalance());

		ctx.close();
	}

}