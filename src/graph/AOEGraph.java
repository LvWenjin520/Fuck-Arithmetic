package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * AOE图的结构与算法 工程时间节点控制
 * 
 * @author user 关键路径算法，可以理解为对工程的时间节点的控制 以节点作为事件，以弧作为时间
 */
public class AOEGraph {
	// 节点数组
	private String[] vertexs;

	// 图的邻接矩阵
	private int[][] graph;

	// 判断是否访问过
	private int[] visited;

	// 图的边数
	public int lineNum;
	
	//图的所有边集合
	private List<AOEGraph.GraphEdge> edges;

	/**
	 * 初始化整个邻接表
	 * 
	 * @param vertexs 节点数 组
	 */
	public AOEGraph(String[] vertexs) {
		this.vertexs = vertexs;
		visited = new int[vertexs.length];
		int vertexsNum = vertexs.length;
		this.graph = new int[vertexsNum][vertexsNum];
		edges = new ArrayList<>();
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
	private int getpointPos(String point) {
		for (int i = 0; i < vertexs.length; i++) {
			if (vertexs[i].equals(point)) {
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
	public void insertLines(String star, String end,int weight) {
		lineNum++;
		int starPos = getpointPos(star);
		int endPos = getpointPos(end);
		this.graph[starPos][endPos] = weight;
		edges.add(new AOEGraph.GraphEdge(star,end,weight));
	}

	
	/**
	 * 求节点最晚开始时间
	 * @return
	 */
	private int[] getVl(int[][] graph,int[] ve) {
		
		int[] minPath = new int[vertexs.length];
		
		minPath[vertexs.length-1] = ve[ve.length-1];
		
		Stack<String> stack = new Stack<>();
		
		for(int i = 0;i<vertexs.length-1;i++) {
			stack.push(vertexs[i]);
		}
		
		while(!stack.isEmpty()) {
			int index = 0;
			int min = 9999;
			
			int pointPos = getpointPos(stack.peek());
			for(int i = 0;i<vertexs.length;i++) {
				if(graph[i][pointPos]!=0 && minPath[i]-graph[i][pointPos]<min) {
					//这里的是新数组中的数据（节点事件最晚发生时间）
					min = minPath[i]-graph[i][pointPos]; 
					index = pointPos;
				}
			}
			minPath[index] = min;
			stack.pop();
		}
		return minPath;
	}
	
	/**
	 * 求节点最早开始时间
	 * @return
	 */
	private int[] getVe(int[][] graph) {
		// 事件节点的最早于最迟发生时间
		int[] pointMax = new int[vertexs.length];
		// 初始化
		for (int i = 0; i < vertexs.length; i++) {
			pointMax[i] = graph[0][i];
		}
		visited[0] =1;
		int index = 0;
		for (int j = 0; j < vertexs.length; j++) {
			int max = 0;
			for (int i = 0; i < vertexs.length; i++) {
				if (visited[i] == 0) {
					if (max < pointMax[i]) {
						max = pointMax[i];
						index = i;
					}
				}
			}
			visited[index] = 1;

			for (int n = 0; n < vertexs.length; n++) {
				if (graph[index][n] != 0 && visited[n] == 0) {
					pointMax[n] = graph[index][n] + max;
				}
			}
		}
		//重置一下访问列表
		for(int i = 0;i<vertexs.length;i++) {
			visited[i] = 0;
		}
		return pointMax;
	}
		
	/**
	 * 置转原图，首位交换
	 */
	private int[][] overTren(int[][] graph) {
		
		int[][] newGraph = new int[vertexs.length][vertexs.length];
		
		for(int i = 0;i<vertexs.length;i++) {
			for(int j = 0;j<vertexs.length;j++) {
				newGraph[j][i] = graph[i][j];
			}
		}
		return newGraph;
	}

	/**
	 * 	求事件的最早开始时间
	 * @param ve
	 * @return
	 */
	private int[] getEi(int[] ve) {
		//事件最早开始时间数组
		int[] ei = new int[lineNum];
		for(int i = 0;i<lineNum;i++) {
			AOEGraph.GraphEdge edge = edges.get(i);
			String start = edge.getStartPoint();
			int startPointPos = getpointPos(start);
			ei[i] = ve[startPointPos];
		}
		return ei;
	}
	
	/**
	 *	 求事件最晚发生时间
	 * @param vl
	 * @return
	 */
	private int[] getLi(int[] vl) {
		int[] li = new int[lineNum];
		for(int i = 0;i<lineNum;i++) {
			AOEGraph.GraphEdge graphEdge = edges.get(i);
			String endPoint = graphEdge.getEndPoint();
			int endPos = getpointPos(endPoint);
			li[i] = vl[endPos] - graphEdge.getWeight();
		}
		return li;
	}
	
	/**
	 * 关键路径的方法
	 */
	public void keyPath() {
		//求得节点的最早开始事件
		int[] ve = getVe(graph);
		//置转AOE图，用于求节点的最晚开始时间
		int[][] newGraph = overTren(graph);
		//求得最晚开始时间
		int[] vl = getVl(newGraph,ve);
		
		//求活动的最早开始时间
		int[] ei = getEi(ve);
		//System.out.println(Arrays.toString(ei));
		
		//求活动的最晚开始时间
		int[] li = getLi(vl);
		//System.out.println(Arrays.toString(li));
		int[] keyPath = new int[lineNum];
		for(int i = 0 ;i<lineNum;i++) {
			keyPath[i] = li[i] - ei[i];
		}
		
		//System.out.println(Arrays.toString(keyPath));
		System.out.println("关键路径为:");
		//输出关键路径
		for(int i = 0;i<lineNum;i++) {
			if(keyPath[i] == 0) {
				AOEGraph.GraphEdge edge = edges.get(i);
				System.out.println(edge.getStartPoint()+"-->"+edge.getEndPoint());
			}
		}
		
		
		/*System.err.println(Arrays.toString(ve));
		System.err.println(Arrays.toString(vl));*/
	}
	
	/**
	 * 边的类
	 * @author user
	 *
	 */
	class GraphEdge{
		private String startPoint;
		private String endPoint;
		private int weight;
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		public String getStartPoint() {
			return startPoint;
		}
		public void setStartPoint(String startPoint) {
			this.startPoint = startPoint;
		}
		public String getEndPoint() {
			return endPoint;
		}
		public void setEndPoint(String endPoint) {
			this.endPoint = endPoint;
		}
		
		public GraphEdge(String startPoint,String endPoint,int weight) {
			this.startPoint = startPoint;
			this.endPoint = endPoint;
			this.weight = weight;
		}
		
	}
	
}
