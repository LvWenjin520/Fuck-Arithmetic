package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 * @author LvWenJin
 *
 */
public class QuickSort {
	public static void main(String[] args) {
		int[] arr = getArr(7);
		sort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
		
		
		
		
	}
	
	
	
	static void sort(int[] arr,int leftBound,int rightBound) {
		if(leftBound >= rightBound) {
			return;
		}
		//轴的位置
		int mid = Quicksort(arr,leftBound,rightBound);
	
		sort(arr,leftBound,mid-1);
		sort(arr,mid+1,rightBound);
	}
	
	/**
	 * 快速排序
	 * @param arr
	 * @return
	 */
	static int Quicksort(int[] arr,int leftBound,int rightBound) {
		
		int pivot = arr[rightBound];
		int left = leftBound;
		int right = rightBound-1;
		
		/**
		 * 
		 * 判断大小，移动指针
		 */
		while(left <= right) {
			while(left <= right && arr[left] <= pivot) {
				left++;
			}
			while(left <= right && arr[right] > pivot) {
				right--;
			}
			
			if(left < right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
			}
			
		}
		
		//将边界放入指定的位置
		int temp = arr[left];
		arr[left] = arr[rightBound];
		arr[rightBound] = temp;
		
		return left;
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
