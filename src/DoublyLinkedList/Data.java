package DoublyLinkedList;

public class Data {
	private int key=0;
	private int value=0;
	
	public Data() {
		this.key= 1; //Auto Incremented
		this.value=(int)(Math.random() * ((900 - 100) + 1)) + 100;
	}
	public Data(int key) {
		this.key= key;
		this.value=(int)(Math.random() * ((900 - 100) + 1)) + 100;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
