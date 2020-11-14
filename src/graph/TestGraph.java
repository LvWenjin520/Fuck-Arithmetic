package graph;

public class TestGraph {
	public static void main(String[] args) {
		
		char[] c = new char[]{'a','b','c','d','e'};
		Graph g = new Graph(c, 1);
		g.insertLines('a', 'b', 2);
		g.insertLines('b', 'c', 4);
		g.insertLines('c', 'd', 1);
		g.insertLines('a', 'd', 3);
		g.insertLines('a', 'e', 6);
		g.showGraph();
		//g.graphDfs('c');
		//g.garphDfsWithOutRec('c');
		//g.graphBfs('b');
		//System.out.println(g.prim('a'));
		g.dijkstra('a');
	}
}
