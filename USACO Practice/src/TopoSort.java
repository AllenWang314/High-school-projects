import java.util.*;

public class TopoSort {
	private int V; // No. of vertices
	private LinkedList<Integer>[] adj; // Adjacency List

	// Constructor
	public class Graph {

		@SuppressWarnings("unchecked")
		public Graph(int v) {
			V = v;
			adj = new LinkedList[v];
			for (int i = 0; i < v; ++i)
				adj[i] = new LinkedList<Integer>();
		}

		public void addEdge(int v, int w) {
			adj[v].add(w);
		}

		public void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
			visited[v] = true;
			Integer i;
			Iterator<Integer> it = adj[v].iterator();
			while (it.hasNext()) {
				i = it.next();
				if (!visited[i])
					topologicalSortUtil(i, visited, stack);
			}
			stack.push(v);
		}
		
		public Stack<Integer> topologicalSort() {
			Stack<Integer> stack = new Stack<Integer>();
			boolean visited[] = new boolean[V];
			for (int i = 0; i < V; i++) {
				visited[i] = false;

			}

			for (int i = 0; i < V; i++) {
				if (visited[i] == false) {
					topologicalSortUtil(i, visited, stack);
				}
			}

			return stack;
		}
	}

	

	// Driver method
	public static void main(String args[]) {
		TopoSort t = new TopoSort();
		Graph g = t.new Graph(6);
		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);

		System.out.println("Following is a Topological " + "sort of the given graph");
		Stack<Integer> s = g.topologicalSort();
		while(!s.empty()) {
			System.out.println(s.pop());
		}
	}
}