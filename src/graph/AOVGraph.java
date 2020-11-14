package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * AOV图，有向无环图，使用拓扑排序，顶点表示活动，使用边表示制约关系
 * 出度的点是与之连接的入读的点的依赖，就是必须出度的活动做完才能做入度的点
 * @author LvWenJin
 *
 */
public class AOVGraph {
	// 节点数组
	private char[] vertexs;

	// 图的邻接矩阵
	private int[][] graph;

	// 判断是否访问过
	private int[] visited;

	//图的边数
	public int lineNum;
	
	/**
	 * 初始化整个邻接表
	 * 
	 * @param vertexs 节点数 组
	 */
	public AOVGraph(char[] vertexs) {
		this.vertexs = vertexs;
		visited = new int[vertexs.length];
		int vertexsNum = vertexs.length;
		this.graph = new int[vertexsNum][vertexsNum];
	}
	
	/**
	 * 显示整个邻接表
	 */
	public void showGraph() {
		for (int i = 0; i < vertexs.length; i++) {
			for (int j = 0; j < vertexs.length; j++) {
				System.out.print(this.graph[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// 获取点的位置
	private int getpointPos(char point) {
		for (int i = 0; i < vertexs.length; i++) {
			if (vertexs[i] == point) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 放置边的方法
	 * 
	 * @param point1
	 * @param point2
	 */
	public void insertLines(char star, char end) {
		lineNum ++;
		int starPos = getpointPos(star);
		int endPos = getpointPos(end);
		this.graph[starPos][endPos] = 1;
	}
	
	/**
	 * 拓扑排序
	 * @return
	 */
	public List<Character> topoSort() {
		
		List<Character> list = new ArrayList<>();
		while(list.size() < vertexs.length) {
			
			for(int i = 0;i<vertexs.length;i++) {
				int inNum = 0;
				for(int j = 0;j<vertexs.length;j++) {
					if(visited[i] == 0 && graph[j][i] == 0) {
						inNum++;
					}
				}
				if(inNum == vertexs.length) {
					visited[i] = 1;
					//第i行全部清零
					for(int k = 0;k<vertexs.length;k++) {
						graph[i][k] = 0;
					}
					list.add(vertexs[i]);
				}
			}
		}
		return list;
	}
}
