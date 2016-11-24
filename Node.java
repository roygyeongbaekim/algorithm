package heapSort;

public class Node {
	private int key;			// 우선순위 저장
	private String class_name;	// 수업명 저장
	
	public int getKey() {		// getter
		return key;
	}


	public void setKey(int key) {	// setter
		this.key = key;
	}


	public String getClass_name() { // getter
		return class_name;
	}


	public void setClass_name(String class_name) {
		this.class_name = class_name; // setter
	}

	
	Node(int key, String class_name){ // Node 생성자
		this.key = key;
		this.class_name = class_name;
	}
}
