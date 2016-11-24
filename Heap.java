package heapSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Heap {
	public ArrayList<Node> array;
	
	public Heap() { // heap�� ���� ������
		array = new ArrayList<Node>();
	}

	public int Parent(int i) {
		return (i - 1) / 2;
	}

	public static int LeftChild(int i) {
		int left = 2 * i + 1;		// �迭�� 0���� �����ϹǷ�
		return left;				// ���� �ڽ� index�� 2 * i + 1
	}

	public static int RightChild(int i) {
		int right = 2 * i + 2;		// �迭�� 0���� �����ϹǷ� 
		return right;				// 2 * i + 2 �� ��ȯ
	}

	public static Node max(ArrayList<Node> data) { // �ִ� ������ �ִ� �׸��� �׻� �Ѹ��� �����Ƿ�
		return data.get(0);
	}

	public static void swap(ArrayList<Node> data, int a, int b){
		Node temp = data.get(a);		// a index ����
		data.set(a, data.get(b));		// b index ��� swap
		data.set(b, temp);
	}
	public static void Max_Heapify(ArrayList<Node> data, int i) {	// �� ����
		int l = LeftChild(i);		// �ش� ���� �ڽ� index
		int r = RightChild(i);		// ������ �ڽ� index
		int largest = i;			// ���� ū�� 
		if (l < data.size() && data.get(l).getKey() > data.get(i).getKey()) {
			largest = l;	// �켱������ ������ ������
		}
		else{
			largest = i;	// �θ� ������
		}
		if (r < data.size() && data.get(r).getKey() > data.get(largest).getKey()) {
			largest = r;	// �켱������ �������� ������
		}
		if (largest != i) {	// ���� ū �Ͱ� index�� ���� ������ swap
			swap(data,i,largest);
			Max_Heapify(data, largest); // ������
		}
		
	}
	
	public static void delete(ArrayList<Node> data,int num) { // ���� �Ҷ�
		data.set(num - 1, data.get(data.size() - 1));			// arraylist ũ�� �ٿ��ְ�
		data.remove(data.size() - 1);							// �ش簪 ����
		for (int i = data.size()/2; i >= 0; i--) {				// �ٽ� �θ���� ������
			Max_Heapify(data, i);
		}
	}

	public static void extract_max(ArrayList<Node> data) {	// ���� �տ� �ִ� ���� ���� ū ���̹Ƿ�
		data.set(0, data.get(data.size() - 1));				// ���� ���� �ִ� ���� ������ �Ű��ְ�
		data.remove(data.size() - 1);						// ���� ����
		Max_Heapify(data, 0);								// root���� ������
	}

	public static void insert(ArrayList<Node> data, Node input) {
		data.add(input);			//	���� �߰��� ���� arrayList�� �߰� �����ش�.
		Build_Max_Heap(data);		// max heap �ٽ� ������ش�.
	}

	public static void Build_Max_Heap(ArrayList<Node> data) {
		for (int i = data.size()/2; i >= 0; i--) {	// ��ü �������� ���� ��, ������ ����� �θ� ������
			Max_Heapify(data, i);					// Max Heap ������  max heap check
		}
	}
	
	public static void increase_key(ArrayList<Node> data, int i, int key) {
		Node tmp = data.get(i);		// �ʱ� ��� �� �����´�.
		tmp.setKey(key);			// key���� �ٲ��ش�.
		data.remove(i);				// ���� �ִ� ��� �� ����
		data.set(i, tmp);			// ����� key���� ���� ��� ����
		Build_Max_Heap(data);		// ������
	}
	
	public static void main(String args[]) throws Exception {
		FileReader file = new FileReader("data03.txt");	// ���� ������ �´�.
		BufferedReader bufread = new BufferedReader(file);
		ArrayList<Node> array = new ArrayList<Node>();	// ��ü ��带 ������ ����
		String i;		
		String[] tmp = null;
		while ((i = bufread.readLine()) != null) {
			tmp = i.split(",");							// ,�� �������� ������.
			Node tNode = new Node(Integer.valueOf(tmp[0]), tmp[1]); // ù��°�� int, �ι�°�� String
			array.add(tNode);							// ArrayList�� �߰��Ѵ�.
		}
		
		file.close();
		
		while (true) {									// ���������� ��� �ݺ�
			Build_Max_Heap(array);						// �ʱ⿡ ����� �� �Ǵ� ����� ���� �ٽ� ����
			for (int j2 = 0; j2 < array.size(); j2++) {	// ��ü ��尪�� ���
				System.out.println(array.get(j2).getKey()+", "+ array.get(j2).getClass_name());;
			}
			System.out.println("--------------------------------------------");
			System.out.println("1. �۾��߰�	2. �ִ밪	3. �ִ� �켱���� �۾� ó��");
			System.out.println("4. ���� Ű�� ����	5. �۾� ����	6. ����");
			System.out.println("--------------------------------------------");
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			switch (s.nextLine()) {
			case "1": // insert
				System.out.print("�ű� �۾��� (20 Bytes �̳�) : ");
				String value = s.nextLine();
				System.out.print("�켱 ���� (0~999) : ");
				String priority = s.nextLine(); // �켱����
				Node tmpInsert = new Node(Integer.valueOf(priority), value); //�ӽ÷� ���� 
				insert(array, tmpInsert);					// ��ü array�� �߰�
				System.out.println("�۾� �߰� �Ϸ�");
				break;
			case "2": // max
				System.out.println("MAX : " + max(array).getKey()+ ", " + max(array).getClass_name());
				break;
			case "3": // extract_max
				extract_max(array);
				System.out.println("�� ���� �۾��� ó���߽��ϴ�.");
				break;
			case "4": // increase_key
				System.out.print("������ ��� x : ");
				String tmp_x = s.nextLine();
				System.out.print("������ Key : ");
				String key = s.nextLine();
				increase_key(array, Integer.valueOf(tmp_x),
						Integer.valueOf(key));
				
				break;
			case "5": // delete
				System.out.println("������ ��� x : ");
				String del_x = s.nextLine();
				delete(array, Integer.valueOf(del_x));
				break;
			case "6":
				System.out.println("����");
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
}
