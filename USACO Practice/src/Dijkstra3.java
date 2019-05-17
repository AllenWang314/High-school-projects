import java.util.*;

/*
 * priorityQueue implementation
 */
public class Dijkstra3 {

	    private int dist[]; 
	    private Set<Integer> settled; 
	    private PriorityQueue<Node> pq; 
	    private int V; // Number of vertices 
	    List<List<Node> > adj; 
	  
	    public Dijkstra3(int V) { 
	        this.V = V; 
	        dist = new int[V]; 
	        settled = new HashSet<Integer>(); 
	        pq = new PriorityQueue<Node>(V, new Node()); 
	    } 
	  
	    public void dijkstra(List<List<Node>> adj, int source) { 
	        this.adj = adj; 
	  
	        for (int i = 0; i < V; i++) {
	            dist[i] = Integer.MAX_VALUE; 
	        }
	        
	        // Add source node to the priority queue 
	        pq.add(new Node(source, 0)); 
	  
	        // Distance to the source is 0 
	        dist[source] = 0; 
	        while (settled.size() != V) { 
	            int u = pq.remove().node;
	            if(!settled.contains(u)) {
		            settled.add(u); 	  
		            eNeighbours(u); 
	            }
	        } 
	    } 
	  
	    public void eNeighbours(int u) { 	  
	        for (int i = 0; i < adj.get(u).size(); i++) { 
	            Node v = adj.get(u).get(i); 
	            if (!settled.contains(v.node)) { 
	                if (dist[u] + v.cost < dist[v.node]) {
	                    dist[v.node] = dist[u] + v.cost; 
	                }
	                pq.add(new Node(v.node, dist[v.node])); 
	            } 
	        } 
	    } 
	  
	    public static void main(String arg[]) { 
	  
	        // Adjacency list representation of the  
	        // connected edges 
	        List<List<Node> > adj = new ArrayList<List<Node>>(); 
	  
	        // Initialize list for every node 
	        for (int i = 0; i < 5; i++) { 
	            List<Node> item = new ArrayList<Node>(); 
	            adj.add(item); 
	        } 
	  
	        // Inputs for the DPQ graph 
	        adj.get(0).add(new Node(1, 9)); 
	        adj.get(0).add(new Node(2, 6)); 
	        adj.get(0).add(new Node(3, 5)); 
	        adj.get(0).add(new Node(4, 3)); 
	  
	        adj.get(2).add(new Node(1, 2)); 
	        adj.get(2).add(new Node(3, 4)); 
	  
	        // Calculate the single source shortest path 
	        Dijkstra3 dpq = new Dijkstra3(5); 
	        dpq.dijkstra(adj, 0); 
	  
	        // Print the shortest path to all the nodes 
	        // from the source node 
	        System.out.println("The shorted path from node :"); 
	        for (int i = 0; i < dpq.dist.length; i++) 
	            System.out.println(0 + " to " + i + " is "
	                               + dpq.dist[i]); 
	    } 
	} 
	  
	// Class to represent a node in the graph 
	class Node implements Comparator<Node> { 
	    public int node; 
	    public int cost; 
	  
	    public Node() {}
	  
	    public Node(int node, int cost)  { 
	        this.node = node; 
	        this.cost = cost; 
	    } 
	  
	    @Override
	    public int compare(Node node1, Node node2)  { 
	        if (node1.cost < node2.cost)  return -1; 
	        if (node1.cost > node2.cost)  return 1; 
	        return 0; 
	    }
	    
}
