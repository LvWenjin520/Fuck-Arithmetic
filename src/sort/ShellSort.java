package sort;

import java.util.Arrays;
import java.util.Random;

/***
 * 希尔排序
 * @author LvWenJin
 *
 */
public class ShellSort {
	public static void main(String[] args) {
		int[] arr = getArr(15);
		
		sort(arr);
		
	}
	
	
	/**
	 * 希尔排序
	 * @param arr
	 * @return
	 */
	static int[] sort(int[] arr) {
		int h = 1;
		
		while(h<=arr.length/3) {
			h=h*3+1;
		}
		
		for(int gap = h ;gap>0;gap=(gap-1)/3) {
			
			for(int i=gap;i<arr.length;i++) {
				
				for(int j=i;j>gap-1;j-=gap) {
					if(arr[j]<arr[j-gap]) {
						int temp = arr[j];
						arr[j] = arr[j-gap];
						arr[j-gap] = temp;
					}
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
