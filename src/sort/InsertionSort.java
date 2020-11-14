package sort;

import java.util.Arrays;
import java.util.Random;

/***
 * 插入排序
 * @author LvWenJin
 *
 */
public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = getArr(10);
		
		sort(arr);
		
	}
	
	
	/**
	 * 插入排序
	 * @param arr
	 * @return
	 */
	static int[] sort(int[] arr) {
		
		for(int i=1;i<arr.length;i++) {
			for(int j=i;j>0;j--) {
				int temp = 0;
				int pos = j;
				if(arr[pos]<arr[pos-1]) {
					temp = arr[pos];
					arr[pos] = arr[pos-1];
					arr[pos-1] = temp;
				}
			}
		}
		
		System.out.println(Arrays.toString(arr));
		return arr;
	}
	
	/**
	 * 获取数组
	 * @return
	 */
	static int[] getArr(int size) {
		 int[] arr = new int[size];
		 Random r = new Random();
		 
		 for(int i=0;i<size;i++) {
			 arr[i] = r.nextInt(100);
		 }
		 System.out.println(Arrays.toString(arr));
		 return arr;
	}
}
