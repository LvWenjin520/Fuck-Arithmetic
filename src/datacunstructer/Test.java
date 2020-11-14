package datacunstructer;

public class Test {
	public static void main(String[] args) {
		ArrayQueue queue = new ArrayQueue(5);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		
		for(int i=0;i<4;i++) {
			System.out.println(queue.getdata());
		}
		queue.add(6);
		queue.add(7);
		queue.add(8);
		queue.add(9);
		for(int i=0;i<4;i++) {
			System.out.println(queue.getdata());
		}
	
	}
}
