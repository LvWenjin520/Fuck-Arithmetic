package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * <<<<<<< HEAD 图的基本操 作
 * 
 * @author LvWenJin
 *
 */
public class Graph {

	// 节点数组
	private char[] vertexs;

	// 图的邻接矩阵
	private int[][] graph;

	// 判断是否访问过
	private int[] visited = new int[5];

	//图的边数
	public int lineNum;
	
	//边的集合
	public List<Graph.Edge> linesList = new ArrayList<Graph.Edge>();
	
	/**
	 * 初始化整个邻接表
	 * 
	 * @param vertexs 节点数 组
	 */
	public Graph(char[] vertexs) {
		this.vertexs = vertexs;

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
	 * @param weight
	 */
	public void insertLines(char point1, char point2, int weight) {
		lineNum ++;
		
		//将边放入边的集合中
		Graph.Edge edge = new Graph.Edge(point1, point2, weight);
		linesList.add(edge);
		
		int point1Pos = getpointPos(point1);
		int point2Pos = getpointPos(point2);
		this.graph[point1Pos][point2Pos] = weight;
		this.graph[point2Pos][point1Pos] = weight;
	}

	/**
	 * 图的深度优先遍历,递归法
	 * 
	 * @return
	 */
	public void graphDfs(char point) {
		int pointPos = getpointPos(point);
		visited[pointPos] = 1;

		for (int i = 0; i < vertexs.length; i++) {
			if (visited[i] == 0 && this.graph[pointPos][i] > 0) {
				graphDfs(vertexs[i]);
			}
		}
		System.out.println(point);
	}

	/**
	 * 深度优先搜索的非递归方式
	 * 
	 * @param point
	 */
	public void garphDfsWithOutRec(char point) {
		Stack<Character> stac = new Stack<Character>();

		stac.push(point);
		int getpointPos = getpointPos(point);
		visited[getpointPos] = 1;

		while (!stac.empty()) {
			for (int i = 0; i < vertexs.length; i++) {
				if (visited[i] == 0 && graph[getpointPos][i] > 0) {
					stac.push(vertexs[i]);
					visited[i] = 1;
					getpointPos = i;
				}
			}
			char head = stac.peek();
			int headPos = getpointPos(head);
			for (int i = 0; i < vertexs.length; i++) {
				if (visited[i] == 0 && graph[headPos][i] > 0) {
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
	 * 
	 * @param point
	 */
	public void graphBfs(char point) {

		Queue<Character> queue = new LinkedList<>();

		int pointPos = getpointPos(point);

		queue.offer(point);

		visited[pointPos] = 1;

		while (!queue.isEmpty()) {

			for (int i = 0; i < vertexs.length; i++) {
				if (visited[i] == 0 && graph[pointPos][i] > 0) {
					queue.offer(vertexs[i]);
					visited[i] = 1;
				}
			}

			System.out.println(queue.poll());
			if (queue.isEmpty()) {
				continue;
			}
			pointPos = getpointPos(queue.peek());

		}
	}

	/**
	 * 获取指定起始点及结束点的权重
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private int getWeight(char start, char end) {

		int startPos = getpointPos(start);
		int endPos = getpointPos(end);
		if (graph[startPos][endPos] > 0) {
			return graph[startPos][endPos];
		}
		return -1;
	}

	/**
	 * prim算法，求最小生成树
	 * 
	 * @param starPoint
	 * @return 生成树的总 路径
	 */
	public int[][] prim(char starPoint) {

		ArrayList<Character> tree = new ArrayList<>();
		// 图上未访问过的点
		ArrayList<Character> graph = new ArrayList<>();
		int startPointPos = getpointPos(starPoint);

		tree.add(starPoint);
		for (int i = 0; i < vertexs.length; i++) {
			if (i == startPointPos) {
				continue;
			}
			graph.add(vertexs[i]);
		}

		int minTreePath = 0;

		//最小生成树
		int[][] minTree = new int[vertexs.length][vertexs.length];
		
		while (tree.size() < vertexs.length) {
			int index = 0;
			int cal = 0;
			int minWeight = 99999;
			for (int j = 0; j < tree.size(); j++) {
				for (int i = 0; i < graph.size(); i++) {
					int weight = getWeight(tree.get(j), graph.get(i));
					if (weight > 0 && weight < minWeight) {
						minWeight = weight;
						index = i;
						cal = j;
					}
				}
				
				
			}
			
			//组装成最小生成树
			int n = getpointPos(graph.get(index));
			int m =getpointPos(tree.get(cal));
			minTree[n][m] = minWeight;
			minTree[m][n] = minWeight;
			
			minTreePath += minWeight;
			tree.add(graph.get(index));
			graph.remove(index);
		}
		System.out.println("最小生成树的总路径为："+minTreePath);
		return minTree;
	}

	/**
	 * 迪杰斯特拉算法
	 * 返回最短路径的数组值
	 */
	public int[] dijkstra(char point) {
		
		int pointIndex = getpointPos(point);
		
		//标记已访问
		visited[pointIndex] = 1;
		
		//最短路径结果
		int[] result = new int[vertexs.length];
		
		//设置最大值未9999
		int INF = 9999;
		
		for(int i = 0;i<vertexs.length;i++) {
			if(graph[pointIndex][i] == 0) {
				result[i] = INF;
			}else {
				result[i] = graph[pointIndex][i];
			}
		}
		
		//最小点的位置
		int k = 0;
		for(int i = 1;i<vertexs.length;i++) {
			
			//找到剩余中的最小值
			int min = INF;
			for(int j = 0;j<vertexs.length;j++) {
				if(visited[j] == 0 && result[j] < min) {
					min = result[j];
					k = j;
				}
			}
			
			//标记访问点
			visited[k] = 1;
			
			//更改原数组中的距离值
			for(int j = 0;j<vertexs.length;j++) {
				int temp = 0;
				if(graph[k][j] == 0) {
					temp = INF;
				}else {
					temp = min+graph[k][j];
				}
				
				if(visited[j] == 0 && temp < result[j]) {
					result[j] = temp;
				}
			}
		}
		result[pointIndex] = 0;
		
		return result;
	}
	
	
	/**
	 * 	floyd算法 (插点法)
	 * @param point 起始点
	 * @return  最短路径结果集
	 */
	public int[][] floyd(char point) {
		
		int INF = 9999;
		
		//结果集
		int[][] result = new int[vertexs.length][vertexs.length];
		
		//初始化距离矩阵
		for(int i = 0 ; i<vertexs.length;i++) {
			for(int j = 0 ;j<vertexs.length;j++) {
				if(graph[i][j] == 0) {
					result[i][j] = INF;
				}else {
					result[i][j] = graph[i][j];
				}
			}
		}
		
		//插入点
		for(int i = 0 ;i<vertexs.length;i++) {
			
			for(int j = 0;j<vertexs.length;j++) {
				for(int k = 0;k<vertexs.length;k++) {
					if(j == k) {
						result[j][k] = 0;
					}else {
						int temp = result[k][i]+result[i][j];
						if(temp < result[j][k]) {
							result[j][k] = temp;
						}
					}
				}
			}
		}
		return result;
	}
	
	
	
	
	/***
	 * 克鲁斯卡尔算法
	 * @param point 起始点
	 * @return 最小生成树矩阵
	 */
	public int[][] kruskal(char point){
		//输出排完序的边集合
		Collections.sort(linesList);
		
		/*
		 * 先将边排序 System.out.println(linesList);
		 */

		
		LinkedList<Graph.Edge> linesQueue = new LinkedList<Graph.Edge>(linesList);
		
		int[][] minTree = new int[vertexs.length][vertexs.length];
		
		//父节点的集合
		int[] parent = new int[vertexs.length];
		for(int i=0;i<vertexs.length;i++) {
			parent[i] = i+1;
		}
		
		int min = 0;
		while(!linesQueue.isEmpty()) {
			
			Graph.Edge poll = linesQueue.poll();
			
			//一条边上的起点位置
			int p1 = getpointPos(poll.getStart());
			//一条边上的终点位置
			int p2 = getpointPos(poll.getEnd());
			
			if(!isCacle(parent,p1,p2)) {
				minTree[p1][p2] = poll.getWeight();
				minTree[p2][p1] = poll.getWeight();
				min+=poll.getWeight();
			}
		}
		System.out.println("最小生成树的总长度为："+min);
		return minTree;
	}
	
	/**
	 * 使用并查集判断是否形成环
	 * @param parent:父节点集合 
	 * @param pointIndex:点的位置
	 * @return true：形成环了
	 */
	private boolean isCacle(int[] parent,int startIndex,int endIndex) {
		
		if(parent[startIndex] != parent[endIndex]) {
			//这里就是并查集算法
			int temp = parent[startIndex];
			for(int i = 0;i<parent.length;i++) {
				if(parent[i] == temp) {
					parent[i] = parent[endIndex];
				}
			}
			return false;
		}
		return true;
	}
	
	/**
	 * 	内部类，用于表示边并排序
	 * @author LvWenJin
	 *
	 */
	private class Edge implements Comparable<Graph.Edge>{
		private char start;
		private char end;
		private int weight;
		
		public char getStart() {
			return start;
		}


		public char getEnd() {
			return end;
		}


		public int getWeight() {
			return weight;
		}


		public Edge(char start,char end,int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		//输出边的信息
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.start+"->"+this.end+"-"+weight;
		}

		//升序排序
		@Override
		public int compareTo(Graph.Edge o) {
			if(this.weight < o.weight) {
				return -1;
			}else if(this.weight > o.weight) {
				return 1;
			}
			return 0;
		}
	}
	
	
}
