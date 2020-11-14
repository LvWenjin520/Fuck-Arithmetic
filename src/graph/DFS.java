package graph;

/**
 * 深度优先搜索,一条路走到黑
 * @author LvWenJin
 *
 */
public class DFS {
	public static void main(String[] args) {
		int[][] graph = new int[7][7];
		graph[0] = new int[]{0,1,1,0,0,0,0};
		graph[1] = new int[]{1,0,0,1,0,0,0};
		graph[2] = new int[]{1,0,0,1,1,1,0};
		graph[3] = new int[]{0,1,1,0,0,0,0};
		graph[4] = new int[]{0,0,1,0,0,1,1};
		graph[5] = new int[]{0,0,1,0,1,0,0};
		graph[6] = new int[]{0,0,0,0,1,0,0};
		
		DFS d = new DFS();
		d.dfs(graph,0);
		
	}
	
	int[] visited = new int[7];
	
	char[] edges = new char[] {'A','B','C','D','E','F','G'};
	/**
	 * 深度优先搜索
	 * @param graph
	 * @param num
	 * @return
	 */
	public void dfs(int[][] graph, int num) {
		System.out.println(edges[num]);
		visited[num] = 1;
		for(int i = 0;i<graph.length;i++) {
			if(visited[i] == 0 && graph[num][i] == 1) {
				dfs(graph,i);
			}
		}
	}
}
