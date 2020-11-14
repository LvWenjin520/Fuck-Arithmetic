package graph;

public class TestGraph {
	public static void main(String[] args) {
		
		char[] c = new char[]{'a','b','c','d','e'};
		Graph g = new Graph(c);
		g.insertLines('a', 'b', 2);
		g.insertLines('b', 'c', 4);
		g.insertLines('c', 'd', 2);
		g.insertLines('a', 'd', 4);
		g.insertLines('a', 'e', 6);
		g.insertLines('b', 'd', 3);
		g.insertLines('e', 'd', 2);
		g.showGraph();
		
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
