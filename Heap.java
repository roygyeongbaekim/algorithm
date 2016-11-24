package heapSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Heap {
	public ArrayList<Node> array;
	
	public Heap() { // heap에 대한 생성자
		array = new ArrayList<Node>();
	}

	public int Parent(int i) {
		return (i - 1) / 2;
	}

	public static int LeftChild(int i) {
		int left = 2 * i + 1;		// 배열이 0부터 시작하므로
		return left;				// 왼쪽 자식 index는 2 * i + 1
	}

	public static int RightChild(int i) {
		int right = 2 * i + 2;		// 배열이 0부터 시작하므로 
		return right;				// 2 * i + 2 값 반환
	}

	public static Node max(ArrayList<Node> data) { // 최대 힙에서 최대 항목은 항상 뿌리에 있으므로
		return data.get(0);
	}

	public static void swap(ArrayList<Node> data, int a, int b){
		Node temp = data.get(a);		// a index 노드와
		data.set(a, data.get(b));		// b index 노드 swap
		data.set(b, temp);
	}
	public static void Max_Heapify(ArrayList<Node> data, int i) {	// 힙 구현
		int l = LeftChild(i);		// 해당 왼쪽 자식 index
		int r = RightChild(i);		// 오른쪽 자식 index
		int largest = i;			// 가장 큰값 
		if (l < data.size() && data.get(l).getKey() > data.get(i).getKey()) {
			largest = l;	// 우선순위가 왼쪽이 높을때
		}
		else{
			largest = i;	// 부모가 높을때
		}
		if (r < data.size() && data.get(r).getKey() > data.get(largest).getKey()) {
			largest = r;	// 우선순위가 오른쪽이 높을때
		}
		if (largest != i) {	// 가장 큰 것과 index가 같지 않으면 swap
			swap(data,i,largest);
			Max_Heapify(data, largest); // 재정렬
		}
		
	}
	
	public static void delete(ArrayList<Node> data,int num) { // 삭제 할때
		data.set(num - 1, data.get(data.size() - 1));			// arraylist 크기 줄여주고
		data.remove(data.size() - 1);							// 해당값 삭제
		for (int i = data.size()/2; i >= 0; i--) {				// 다시 부모부터 재정렬
			Max_Heapify(data, i);
		}
	}

	public static void extract_max(ArrayList<Node> data) {	// 가장 앞에 있는 값이 가장 큰 값이므로
		data.set(0, data.get(data.size() - 1));				// 가장 끝에 있는 값을 앞으로 옮겨주고
		data.remove(data.size() - 1);						// 노드는 삭제
		Max_Heapify(data, 0);								// root부터 재정렬
	}

	public static void insert(ArrayList<Node> data, Node input) {
		data.add(input);			//	새로 추가할 값을 arrayList에 추가 시켜준다.
		Build_Max_Heap(data);		// max heap 다시 만들어준다.
	}

	public static void Build_Max_Heap(ArrayList<Node> data) {
		for (int i = data.size()/2; i >= 0; i--) {	// 전체 사이즈의 절반 즉, 마지막 노드의 부모 노드부터
			Max_Heapify(data, i);					// Max Heap 재정렬  max heap check
		}
	}
	
	public static void increase_key(ArrayList<Node> data, int i, int key) {
		Node tmp = data.get(i);		// 초기 노드 값 가져온다.
		tmp.setKey(key);			// key값을 바꿔준다.
		data.remove(i);				// 원래 있던 노드 값 삭제
		data.set(i, tmp);			// 변경된 key값을 가진 노드 삽입
		Build_Max_Heap(data);		// 재정렬
	}
	
	public static void main(String args[]) throws Exception {
		FileReader file = new FileReader("data03.txt");	// 파일 가지고 온다.
		BufferedReader bufread = new BufferedReader(file);
		ArrayList<Node> array = new ArrayList<Node>();	// 전체 노드를 저장할 변수
		String i;		
		String[] tmp = null;
		while ((i = bufread.readLine()) != null) {
			tmp = i.split(",");							// ,를 기준으로 나눈다.
			Node tNode = new Node(Integer.valueOf(tmp[0]), tmp[1]); // 첫번째는 int, 두번째는 String
			array.add(tNode);							// ArrayList에 추가한다.
		}
		
		file.close();
		
		while (true) {									// 종료전까지 계속 반복
			Build_Max_Heap(array);						// 초기에 저장된 값 또는 변경된 값을 다시 정렬
			for (int j2 = 0; j2 < array.size(); j2++) {	// 전체 노드값을 출력
				System.out.println(array.get(j2).getKey()+", "+ array.get(j2).getClass_name());;
			}
			System.out.println("--------------------------------------------");
			System.out.println("1. 작업추가	2. 최대값	3. 최대 우선순위 작업 처리");
			System.out.println("4. 원소 키값 증가	5. 작업 제거	6. 종료");
			System.out.println("--------------------------------------------");
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			switch (s.nextLine()) {
			case "1": // insert
				System.out.print("신규 작업명 (20 Bytes 이내) : ");
				String value = s.nextLine();
				System.out.print("우선 순위 (0~999) : ");
				String priority = s.nextLine(); // 우선순위
				Node tmpInsert = new Node(Integer.valueOf(priority), value); //임시로 저장 
				insert(array, tmpInsert);					// 전체 array에 추가
				System.out.println("작업 추가 완료");
				break;
			case "2": // max
				System.out.println("MAX : " + max(array).getKey()+ ", " + max(array).getClass_name());
				break;
			case "3": // extract_max
				extract_max(array);
				System.out.println("한 개의 작업을 처리했습니다.");
				break;
			case "4": // increase_key
				System.out.print("수정할 노드 x : ");
				String tmp_x = s.nextLine();
				System.out.print("수정할 Key : ");
				String key = s.nextLine();
				increase_key(array, Integer.valueOf(tmp_x),
						Integer.valueOf(key));
				
				break;
			case "5": // delete
				System.out.println("삭제할 노드 x : ");
				String del_x = s.nextLine();
				delete(array, Integer.valueOf(del_x));
				break;
			case "6":
				System.out.println("종료");
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
}
