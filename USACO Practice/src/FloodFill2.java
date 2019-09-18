import java.util.*;

public class FloodFill2 {
	
	public static void solve(char[][] board) {
		// for each O we consider a DFS starting there and see if it reaches the outside
		// if not we turn it into O
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				boolean[][] visited = new boolean[board.length][board[0].length];
				if (!flood(i,j,board,visited)){
					board[i][j] = 'X';
				}
			}
		}
	}

	public static boolean flood(int i, int j, char[][] board, boolean[][] visited){
		if (visited[i][j]) return false;
		if (board[i][j] == 'X') return false;
		if (i == 0 || i == board.length-1 || j == 0 || j == board[0].length-1) return true;
		visited[i][j] = true;
		return flood(i+1, j, board, visited) ||
				flood(i-1, j, board, visited) ||
				flood(i, j+1, board, visited) ||
				flood(i, j-1, board, visited);
	}
	
	public static void main(String[] args) {
		char[][] board = {{'X', 'X', 'X', 'X'},
				{'X', 'O', 'O', 'X'},
				{'X', 'X', 'O', 'X'},
				{'X', 'O', 'X', 'X'}};
		solve(board);
		System.out.println(Arrays.deepToString(board));
	}
}
