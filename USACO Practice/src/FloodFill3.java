class FloodFill3 {
	public static int[][] updateMatrix(int[][] matrix) {
		int[][] answer = new int[matrix.length][matrix[0].length];
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				answer[i][j] = Integer.MAX_VALUE/2;
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				flood(i, j, matrix, answer, visited);
			}
		}
		return answer;
	}

	public static int flood(int i, int j, int[][] matrix, int[][] answer, boolean[][] visited) {
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
			return Integer.MAX_VALUE/2;
		}
		if (visited[i][j]) {
			return answer[i][j];
		}
		visited[i][j] = true;
        if (matrix[i][j] == 0)
			return answer[i][j] = 0;
		int a = 1 + flood(i + 1, j, matrix, answer, visited);
		int b = 1 + flood(i - 1, j, matrix, answer, visited);
		int c = 1 + flood(i, j - 1, matrix, answer, visited);
		int d = 1 + flood(i, j + 1, matrix, answer, visited);
		a = Math.min(a, b);
		a = Math.min(a, c);
		a = Math.min(a, d);
		return answer[i][j] = a;
	}
}