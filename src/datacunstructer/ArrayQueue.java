package datacunstructer;

/**
 * 环形队列
 * @author LvWenJin
 *
 */
public class ArrayQueue {
	private int maxSize;
	private int front;
	private int rear;
	private int[] arr;
	
	public ArrayQueue(int size) {
		this.maxSize = size;
		this.arr = new int[size];
		front = 0;
		rear = 0;
	}
	
	/**
	 * 判断是否满
	 * @return
	 */
	public boolean isFull() {
		return (rear+1) % maxSize == front;
	}
	
	/**
	 * 判断是都为空
	 * @return
	 */
	public boolean isEmpty() {
		return rear == front;
	}
	
	/**
	 * 存入数据
	 * @param data
	 */
	public void add(int data) {
		if(isFull()) {
			System.out.println("队列已满");
			return;
		}
		arr[rear] = data;
		rear = ( rear + 1 ) % maxSize;
	}
	
	/**
	 * 取出数据
	 * @return
	 */
	public int getdata() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空");
		}
		int temp = arr[front];
		front = (front+1)%maxSize;
		return temp;
	}
	
	/**
	 * 显示所有数据的方法
	 */
	public void showArray() {
		if(isEmpty()) {
			System.out.println("队列为空");
			return;
		}
		for(int i=front;i<getCount();i++) {
			System.out.printf("arr[%d] = %d\n",i%maxSize,arr[i%maxSize]);
		}
		
	}
	
	/**
	 * 求出当前队列的有效数据
	 * @return
	 */
	private int getCount() {
		return (rear+maxSize+front)% maxSize;
	}
	
	public int getHead() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空");
		}
		
		return arr[front];
	}
}
