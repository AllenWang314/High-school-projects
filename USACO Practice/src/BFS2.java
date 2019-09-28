import java.util.*;

public class BFS2 {
    public static int shortestBridge(int[][] A) {
        int[] root = findFirst(A);
        boolean[][] visited = new boolean[A.length][A[0].length];
        LinkedList<int[]> verts = new LinkedList<int[]>();
        dfs(A, verts, visited, root[0], root[1]);
        for (int[] a : verts) {
        		System.out.println(Arrays.toString(a));
        }
        int ans = 0;
        LinkedList<int[]> bverts = new LinkedList<int[]>();
        while (verts.size() != 0) {
        		int[] a = verts.pop();
        		bverts.add(new int[] {a[0]-1, a[1], 0});
        		bverts.add(new int[] {a[0]+1, a[1], 0});
        		bverts.add(new int[] {a[0], a[1]-1, 0});
        		bverts.add(new int[] {a[0], a[1]+1, 0});
        }
        while((ans = bfs(A, bverts, visited, bverts.pop())) == 0);
        return ans;
    }
    
    public static int bfs(int[][] A, LinkedList<int[]> bverts, boolean[][] visited, int[] root) {
    		if (root[0] < 0 || root[0] >= visited.length ||
        		root[1] < 0 || root[1] >= visited[0].length ||
        		visited[root[0]][root[1]] == true) return 0;
    		if (A[root[0]][root[1]] == 1) return root[2];
    		visited[root[0]][root[1]] = true;
    		bverts.add(new int[] {root[0]-1, root[1], root[2] + 1});
    		bverts.add(new int[] {root[0]+1, root[1], root[2] + 1});
    		bverts.add(new int[] {root[0], root[1]-1, root[2] + 1});
    		bverts.add(new int[] {root[0], root[1]+1, root[2] + 1});
    		return 0;
    }
    
    public static void dfs(int[][] A, LinkedList<int[]> verts, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= visited.length ||
        		j < 0 || j >= visited[0].length ||
        		visited[i][j] == true ||
        		A[i][j] == 0) return;
        visited[i][j] = true;
        verts.add(new int[] {i,j});
        dfs(A, verts, visited, i+1, j);
        dfs(A, verts, visited, i-1, j);
        dfs(A, verts, visited, i, j+1);
        dfs(A, verts, visited, i, j-1);
    }
    
    public static int[] findFirst(int[][] A){
        for (int i = 0; i < A.length; i++){
            for (int j = 0; j < A[0].length; j++){
                if (A[i][j] == 1) return new int[] {i,j};
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
    		int[][] islands = {{0,1,0},{0,0,0},{0,0,1}};
    		System.out.println(shortestBridge(islands));
    }
}