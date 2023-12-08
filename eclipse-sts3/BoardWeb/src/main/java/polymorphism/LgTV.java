package polymorphism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
	@Autowired
	private Speaker speaker;
	private int price;
	
	//클래스와 이름이 같고, 리턴이 없다
	public LgTV() {
		System.out.println("LgTV -- 객체 생성(1)");
	}

	@Override
	public void powerOn() {
		System.out.println("price : " + price);
		System.out.println("LgTV -- 전원을 켠다.");		
	}
	@Override
	public void powerOff() {
		System.out.println("LgTV -- 전원을 끈다.");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		speaker.volumeDown();
		
	}	
}
