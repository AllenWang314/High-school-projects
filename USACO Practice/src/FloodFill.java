import java.io.*;

public class FloodFill {

	private static int N;
	private static boolean[][] loc;
	private static boolean[][] visited;


	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.in")));
		pw.write("6\n" + 
				"##....\n" + 
				"....#.\n" + 
				".#..#.\n" + 
				".#####\n" + 
				"...###\n" + 
				"....##");
		pw.close();
	}
	
	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		
		N = Integer.parseInt(br.readLine());
		loc = new boolean[N][N];
		visited = new boolean[N][N];


		for(int i = 0; i<N; i++) {
			String nextLine = br.readLine();
			for (int j = 0; j<N; j++) {
				if (nextLine.substring(j,j+1).equals("#")) {
					loc[i][j] = true;
				}
			}
		}
		
		int area = 0, perimeter = 0;
		
		for(int i = 0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				int[] AnP = flood(i,j);
				if (AnP[0] > area) {
					area = AnP[0];
					perimeter = AnP[1];
				}
				else if (AnP[0] == area) {
					perimeter = Math.min(AnP[1],perimeter);
				}		
			}
		}
			
		System.out.println(area);
		System.out.println(perimeter);

		pw.write("" + area + " " + perimeter);

		br.close();
		pw.close();
	}
	
	/*
	 * Method floods from location i,j
	 * Will return area, perimeter of region at that point.
	 */
	public static int[] flood(int i, int j) {
		int[] AnP = new int[2];
		if (i<0 || i>= N || j < 0 || j >= N || !loc[i][j]) {
			//System.out.println("i = " + i + ", j = " + j);
			return new int[] {0,1};
		}
		if(visited[i][j]) {
			return AnP;
		}
		visited[i][j] = true;
		int[] temp1 = flood(i,j+1);
		int[] temp2 = flood(i,j-1);
		int[] temp3 = flood(i-1,j);
		int[] temp4 = flood(i+1,j);

		AnP[0] += 1 + temp1[0] + temp2[0] + temp3[0] + temp4[0];
//		System.out.println(" i = " + i + ", j = " + j + " " + flood(i,j+1)[1]);
//		System.out.println(" i = " + i + ", j = " + j + " " + flood(i,j-1)[1]);
//		System.out.println(" i = " + i + ", j = " + j + " " + flood(i+1,j)[1]);
//		System.out.println(" i = " + i + ", j = " + j + " " + flood(i-1,j)[1]);

		AnP[1] += temp1[1] + temp2[1] + temp3[1] + temp4[1];
//		System.out.println("i = " + i + ", j = " + j + " " + Arrays.toString(AnP));
		return AnP;
	}
	
}
