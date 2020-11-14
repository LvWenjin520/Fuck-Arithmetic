package sort;

import java.util.Arrays;

/**
 * 选择排序
 * @author LvWenJin
 *
 */
public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = {5,3,5,2,1};
		
		for(int j=0;j<arr.length-1;j++) {
			int minPos = j;
			for(int i=j+1;i<arr.length;i++) {
				minPos = arr[i]<arr[minPos] ? i: minPos;
			}
			swap(arr,j,minPos);
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(Check.check(arr));
	}
	
	/**
	 * 交换
	 * @param arr
	 * @param i
	 * @param j
	 */
	static void swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
