package heapSort;

public class Node {
	private int key;			// �켱���� ����
	private String class_name;	// ������ ����
	
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

	
	Node(int key, String class_name){ // Node ������
		this.key = key;
		this.class_name = class_name;
	}
}
