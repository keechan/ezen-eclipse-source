package polymorphism;

public class SamsungTV implements TV {
	private Speaker speaker;
	private int price;
	
	//클래스와 이름이 같고, 리턴이 없다
	public SamsungTV() {
		System.out.println("SamsungTV -- 객체 생성(1)");
	}

	//setter
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	//setter
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public void powerOn() {
		System.out.println("price : " + price);
		System.out.println("SamsungTV -- 전원을 켠다.");		
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV -- 전원을 끈다.");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		//System.out.println("SamsungTV -- 소리를 올린다");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		//System.out.println("SamsungTV -- 소리를 줄인다");
		speaker.volumeDown();
		
	}	
}

