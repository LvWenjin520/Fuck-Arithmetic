package graph;

import java.util.List;

public class TestGraph {
	public static void main(String[] args) {
		
		char[] c = new char[]{'a','b','c','d','e','f','g'};
		//测试AOV图的拓扑排序
		AOVGraph g = new AOVGraph(c);
		g.insertLines('a', 'b');
		g.insertLines('c', 'b');
		g.insertLines('b', 'd');
		g.insertLines('b', 'e');
		g.insertLines('f', 'c');
		g.insertLines('f', 'b');
		g.insertLines('f', 'e');
		g.insertLines('b', 'g');
		g.insertLines('g', 'd');
		g.showGraph();
		List<Character> topoSort = g.topoSort();
		System.out.println(topoSort);
		//System.out.println(Arrays.toString(topoSort));
		
		//测试无向网的各种算法
		/*Graph g = new Graph(c);
		g.insertLines('a', 'b', 2);
		g.insertLines('b', 'c', 4);
		g.insertLines('c', 'd', 2);
		g.insertLines('a', 'd', 4);
		g.insertLines('a', 'e', 6);
		g.insertLines('b', 'd', 3);
		g.insertLines('e', 'd', 2);
		g.showGraph();*/
		
		//克鲁斯卡尔算法
		/*
		 * int[][] kruskal = g.kruskal('b'); for(int[] temp : kruskal) {
		 * System.out.println(Arrays.toString(temp)); }
		 */
		
		//g.graphDfs('c');
		//g.garphDfsWithOutRec('c');
		//g.graphBfs('b');
		//System.out.println(g.prim('a'));
		//int[] dijkstra = g.dijkstra('c');
		//System.out.println(Arrays.toString(dijkstra));
		
		
		/*
		 * 弗洛伊德算法
		 * int[][] floyd = g.floyd('a'); for(int[] temp : floyd) {
		 * System.out.println(Arrays.toString(temp)); }
		 */
		
		/* prim算法
		 * int[][] prim = g.prim('a'); for(int[] temp : prim) {
		 * System.out.println(Arrays.toString(temp)); }
		 */
		
	}
}
