import java.util.*;

public class Solution1 {
	
	/*
	 * given array of tiles that are water marked by false, find the largest peak
	 */
	
	public static int highestPeak(boolean[][] water) {
		Solution1 sol = new Solution1();
		LinkedList<Point> points = new LinkedList<Point>();
		boolean[][] assigned = new boolean[water.length][water[0].length];
		int[][] elevation = new int[water.length][water[0].length];
		for (int i = 0; i < water.length; i++) {
			for (int j = 0; j < water[0].length; j++) {
				if (water[i][j]) {
					points.add(sol.new Point(i,j));
					elevation[i][j] = 0;
					assigned[i][j] = true;
				}
			}
		}
		while(!points.isEmpty()) {
			Point current = points.pop();
			// check if visited
			if (current.x+1 < water.length && !assigned[current.x+1][current.y]) {
				elevation[current.x+1][current.y] = 1 + elevation[current.x][current.y];
				assigned[current.x + 1][current.y] = true;
				points.add(sol.new Point(current.x+1,current.y));
			}
			if (current.x-1 >=0 && !assigned[current.x-1][current.y]) {
				elevation[current.x-1][current.y] = 1 + elevation[current.x][current.y];
				assigned[current.x - 1][current.y] = true;
				points.add(sol.new Point(current.x-1,current.y));
			}
			if (current.y-1 >=0 && !assigned[current.x][current.y-1]) {
				elevation[current.x][current.y-1] = 1 + elevation[current.x][current.y];
				assigned[current.x][current.y-1] = true;
				points.add(sol.new Point(current.x,current.y-1));
			}
			if (current.y+1 < water[0].length && !assigned[current.x][current.y+1]) {
				elevation[current.x][current.y+1] = 1 + elevation[current.x][current.y];
				assigned[current.x][current.y+1] = true;
				points.add(sol.new Point(current.x,current.y+1));
			}
		}
		
		int max = 0;
		
		for (int i = 0; i < water.length; i++) {
			for (int j = 0; j < water[0].length; j++) {
				max = Math.max(max, elevation[i][j]);
			}
		}
		System.out.println(Arrays.deepToString(elevation));
		return max;
	}
	

    public static void main(String[] args) {
    		boolean[][] water = new boolean[8][8];
    		for (int i = 0; i < 8; i++) {
    			for (int j = 0; j < 8; j++) {
    				water[i][j] = true;
        		}
    		}
    		for (int i = 1; i < 7; i++) {
    			for (int j = 1; j < 7; j++) {
    				water[i][j] = false;
        		}
    		}
    		System.out.println(highestPeak(water));
    }
    
    class Point {
    	
    		 int x;
    		 int y;
    		 
    		 Point(int x, int y){
    			 this.x = x;
    			 this.y = y;
    		 }
    		 
    		 
    }
}