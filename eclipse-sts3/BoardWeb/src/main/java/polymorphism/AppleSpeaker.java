package polymorphism;

import org.springframework.stereotype.Component;

public class AppleSpeaker implements Speaker {
	public AppleSpeaker() {
		System.out.println("==> Apple Speaker 객체 생성");
	}
	
	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("Apple Speaker Volume Up!");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("Apple Speaker volume Down");
	}
}
