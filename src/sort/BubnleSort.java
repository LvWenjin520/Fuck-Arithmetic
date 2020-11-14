package sort;

import java.util.Arrays;
import java.util.Random;

/***
 * 冒泡排序
 * @author LvWenJin
 *
 */
public class BubnleSort {
	public static void main(String[] args) {
		int[] arr = getArr(10);
		
		sort(arr);
		
	}
	
	
	/**
	 * 冒泡排序
	 * @param arr
	 * @return
	 */
	static int[] sort(int[] arr) {
		for(int j=arr.length-1;j>0;j--) {
			for(int i=0;i<arr.length-1;i++) {
				int pos = i;
				int temp = 0;
				if(arr[pos] > arr[pos+1]) {
					temp = arr[pos];
					arr[pos] = arr[pos+1];
					arr[pos+1] = temp;
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
