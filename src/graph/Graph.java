package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 图的基本操作
 * @author LvWenJin
 *
 */
public class Graph {
	
	//节点数组
	private char[] vertexs;
	
	//图的邻接矩阵
	private int[][] graph;
	
	//判断是否访问过
	private int[] visited = new int[5];
	
	/**
	 * 初始化整个邻接表
	 * @param vertexs 节点数组
	 * @param lineNum  边的数量
	 */
	public Graph(char[] vertexs , int lineNum) {
		this.vertexs = vertexs;
		
		int vertexsNum = vertexs.length;
		
		this.graph = new int[vertexsNum][vertexsNum];
		
	}
	
	/**
	 * 显示整个邻接表
	 */
	public void showGraph() {
		for(int i = 0;i<vertexs.length;i++) {
			for(int j = 0;j<vertexs.length;j++) {
				System.out.print(this.graph[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	//获取点的位置
	private int getpointPos(char point) {
		for(int i = 0;i<vertexs.length;i++) {
			if(vertexs[i] == point) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 放置边
	 * @param point1
	 * @param point2
	 * @param weight
	 */
	public void insertLines(char point1,char point2,int weight) {
		int point1Pos = getpointPos(point1);
		int point2Pos = getpointPos(point2);
		this.graph[point1Pos][point2Pos] = weight;
		this.graph[point2Pos][point1Pos] = weight;
	}

	/**
	 * 图的深度优先遍历,递归法
	 * @return
	 */
	public void graphDfs(char point) {
		int pointPos = getpointPos(point);
		visited[pointPos] = 1;
		
		for(int i = 0;i<vertexs.length;i++) {
			if(visited[i] == 0 && this.graph[pointPos][i] > 0) {
				graphDfs(vertexs[i]);
			}
		}
		System.out.println(point);
	}
	
	
	/**
	 * 深度优先搜索的非递归方式
	 * @param point
	 */
	public void garphDfsWithOutRec(char point) {
		Stack<Character> stac = new Stack<Character>();
		
		stac.push(point);
		int getpointPos = getpointPos(point);
		visited[getpointPos] = 1;
		
		while(!stac.empty()) {
			for(int i = 0;i<vertexs.length;i++) {
				if(visited[i] == 0 && graph[getpointPos][i] > 0) {
					stac.push(vertexs[i]);
					visited[i] = 1;
					getpointPos = i;
				}
			}
			char head = stac.peek();
			int headPos = getpointPos(head);
			for(int i = 0;i<vertexs.length;i++) {
				if(visited[i] == 0 && graph[headPos][i] > 0) {
					stac.push(vertexs[i]);
					visited[i] = 1;
					headPos = i;
				}
			}
			System.out.println(stac.pop());
		}
	}
	
	/**
	 * 广度优先搜索
	 * @param point
	 */
	public void graphBfs(char point) {
		
		Queue<Character> queue = new LinkedList<>();
		
		int pointPos = getpointPos(point);
		
		queue.offer(point);
		
		visited[pointPos] = 1;
		
		while(!queue.isEmpty()) {
			
			for(int i = 0;i<vertexs.length;i++) {
				if(visited[i] == 0 && graph[pointPos][i] > 0) {
					queue.offer(vertexs[i]);
					visited[i] = 1;
				}
			}
			
			System.out.println(queue.poll());
			if(queue.isEmpty()) {
				continue;
			}
			pointPos = getpointPos(queue.peek());
			
		}
	}
	
	/**
	 * 获取指定起始点及结束点的权重
	 * @param start
	 * @param end
	 * @return
	 */
	private int getWeight(char start,char end) {
		
		int startPos = getpointPos(start);
		int endPos = getpointPos(end);
		if(graph[startPos][endPos] > 0) {
			return graph[startPos][endPos];
		}
		return -1;
	}
	
	
	/**
	 * prim算法，求最小生成树
	 * @param starPoint
	 * @return 生成树的总路径
	 */
	public int prim(char starPoint) {
		
		//生成树上的点
		ArrayList<Character> tree = new ArrayList<>();
		//图上未访问过的点
		ArrayList<Character> graph = new ArrayList<>();
		int startPointPos = getpointPos(starPoint);
		
		tree.add(starPoint);
		for(int i = 0;i<vertexs.length;i++) {
			if(i == startPointPos) {
				continue;
			}
			graph.add(vertexs[i]);
		}
		
		int minTreePath = 0;
		
		while(tree.size() < vertexs.length) {
			int index = 0;
			int minWeight = 99999;
			for(int j = 0;j<tree.size();j++) {
				for(int i = 0;i<graph.size();i++) {
					int weight = getWeight(tree.get(j),graph.get(i));
					if(weight > 0 && weight < minWeight) {
						minWeight = weight;
						index = i;
					}
				}
			}
			minTreePath += minWeight;
			tree.add(graph.get(index));
			graph.remove(index);
		}
		return minTreePath;
	}
	
	
	/**
	 * 迪杰斯特拉算法
	 */
	public void dijkstra() {
		
	}
	
	
	
	
	
}
