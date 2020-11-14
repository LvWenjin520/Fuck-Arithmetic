package sort;

public class FindMax {
	public static void main(String[] args) {
		
		int[] arr = {1,2,3,4,5,6,7,9,8,4};
		
		int maxPos = 0;
		
		for(int i=maxPos;i<arr.length-1;i++) {
			if(arr[maxPos] < arr[i+1]) {
				maxPos = i+1;
			}
		}
		System.out.println(maxPos);
		
	}
}
