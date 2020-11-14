package sort;

import java.util.Arrays;

/***
 * 对数器
 * @author LvWenJin
 *
 */
public class Check {
	public static boolean check(int[] arr) {
		int[] arrCopy = new int[arr.length];
		
		System.arraycopy(arr, 0, arrCopy, 0, arr.length);
		
		Arrays.sort(arrCopy);
		for(int i=0;i<arr.length;i++) {
			if(arr[i] != arrCopy[i]) {
				return false;
			}
		}
		return true;
	}
}
