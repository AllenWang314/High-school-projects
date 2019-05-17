
public class Dijkstra1 {
	
	 public static final int V = 9; 
	    
	    public int minDistance(int dist[], Boolean visited[]) { 
	        int min = Integer.MAX_VALUE, min_index=-1; 
	  
	        for (int v = 0; v < V; v++) {
	        		if (visited[v] == false && dist[v] <= min) { 
	                min = dist[v]; 
	                min_index = v; 
	            } 
	        }
	        return min_index; 
	    } 

	    public void dijkstra(int graph[][], int src) { 
	        int dist[] = new int[V];
	        Boolean visited[] = new Boolean[V]; 
	  
	        for (int i = 0; i < V; i++) { 
	            dist[i] = Integer.MAX_VALUE; 
	            visited[i] = false; 
	        } 
	  
	        dist[src] = 0; 
	        for (int count = 0; count < V-1; count++) { 
	            int u = minDistance(dist, visited); 
	            visited[u] = true; 
	  
	            for (int v = 0; v < V; v++) {
	                if (!visited[v] && graph[u][v]!=0 && 
	                        dist[u] != Integer.MAX_VALUE && 
	                        dist[u]+graph[u][v] < dist[v]) {
	                    dist[v] = dist[u] + graph[u][v]; 
	                }
	            }
	        } 
	    } 
	  
	    public static void main (String[] args) { 

	    } 
}
