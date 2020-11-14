package test01;

import java.util.Arrays;

/**
 * 最小生成树，普莱姆算法
 * @author LvWenJin
 *
 */
public class Prim {
	
	public static void main(String[] args) {
		int m = 99999;
		
		int[] h1 = {m,5,7,m,m,m,2};
		int[] h2 = {5,m,m,9,m,m,3};
		int[] h3 = {7,m,m,m,8,m,m};
		int[] h4 = {m,9,m,m,m,4,m};
		int[] h5 = {m,m,8,m,m,5,4};
		int[] h6 = {m,m,m,4,5,m,6};
		int[] h7 = {2,3,m,m,4,6,m};
		
		int[][] ma = new int[7][7];
		
		ma[0] = h1;
		ma[1] = h2;
		ma[2] = h3;
		ma[3] = h4;
		ma[4] = h5;
		ma[5] = h6;
		ma[6] = h7;
		
		int[][] prim = prim(ma,0);
		
		for(int[] re : prim) {
			System.out.println(Arrays.toString(re));
		}
		
	}
	
	public static int[][] prim(int[][] matrix ,int v){
		
		int[] visited = new int[matrix.length];
		
		visited[v] = 1;
		
		int[][] result = new int[matrix.length][matrix.length];
		
		int h1 = -1;
		int h2 = -1;
		int min = 99999;
		for(int k = 1;k<matrix.length;k++) {
			
			for(int i=0;i<matrix.length;i++) {
				
				for(int j = 0;j<matrix.length;j++) {
					
					if(visited[i] == 1 && visited[j] == 0 && matrix[i][j] < min) {
						min = matrix[i][j];
						h1 = i;
						h2 = j;
					}
					
				}
				
			
			}
			visited[h2] = 1;
			result[h1][h2] = min;
			System.out.println(h1+"->"+h2+":"+min);
			min = 99999;
			
		}
		
		return result;
	}
	
	
}

/**
 * 输出的最小生成树
 * @author LvWenJin
 *
 */
class Mintree{
	
	int[][] matrix;
	
	
	
}
