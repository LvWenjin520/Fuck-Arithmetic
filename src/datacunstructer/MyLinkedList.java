package datacunstructer;

import java.util.Arrays;

public class MyLinkedList {
	
	public static void main(String[] args) {
		MyLinkedList ml = new MyLinkedList();
		ml.addByOrder(new Node(1,"w"));
		ml.addByOrder(new Node(3,"w"));
		ml.addByOrder(new Node(2,"w"));
		ml.showList();
		System.out.println(ml.size());
		Node[] array = ml.toArray();
		System.out.println(Arrays.toString(array));
	}
	
	private Node head = new Node(0,"");
	private int size = 0;
	
	public void add(Node node) {
		Node temp = head;
		while(true) {
			if(temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = node;
		size = size+1;
	}
	
	public int size() {
		return this.size;
	}
	
	public Node[] toArray() {
		Node[] nodes = new Node[this.size];
		Node temp = head.next;
		int index = 0;
		while(true) {
			if(temp == null) {
				break;
			}
			
			nodes[index] = temp;
			
			temp = temp.next;
			index++;
		}
		return nodes;
	}
	
	public void addByOrder(Node node) {
		Node temp = head;
		boolean flag = false;
		while(true) {
			if(temp.next == null) {
				break;
			}
			
			if(temp.next.getAge() > node.getAge()) {
				break;
			}else if(temp.next.getAge() == node.getAge()) {
				flag = true;  //存在同样的年龄的时候
				break;
			}
			temp = temp.next;
		}
		
		if(flag) {
			System.out.println("已经存在，无法添加");
		}else {
			node.next = temp.next;
			temp.next = node;
		}
		size++;
	}
	
	
	public void showList() {
		Node temp = head.next;
		
		if(temp == null) {
			System.out.println("链表为空");
			return;
		}
		
		while(true) {
			if(temp == null) {
				break;
			}
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
}

class Node{
	private int age;
	private String name;
	public Node next;
	
	public Node(int age,String name) {
		this.age = age;
		this.name = name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+"-"+this.age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

