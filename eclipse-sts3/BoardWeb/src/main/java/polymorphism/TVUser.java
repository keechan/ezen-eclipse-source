package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//BeanFactory factory = new BeanFactory();
		//TV tv = (TV)factory.getBean(args[0]);
		
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		TV tv = (TV)factory.getBean("tv", 300000);

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		factory.close();
	}
}