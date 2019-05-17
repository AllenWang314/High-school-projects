import java.io.*;
import java.util.*;

public class HashBrown2 {

	public static int N;
	public static HashMap<Integer, HashSet<Integer>> ones;
	public static HashMap<HashSet<Integer>, HashSet<Integer>> twos;
	public static HashMap<HashSet<Integer>, HashSet<Integer>> threes;
	public static HashMap<HashSet<Integer>, HashSet<Integer>> fours;
	public static HashMap<HashSet<Integer>, HashSet<Integer>> fives;

	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.in")));
		pw.write("4\n" + "1 2 3 4 5\n" + "1 2 3 10 8\n" + "10 9 8 7 6\n" + "50 60 70 80 90");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("cowpatibility.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));

		N = Integer.parseInt(br.readLine());
		ones = new HashMap<Integer, HashSet<Integer>>();
		twos = new HashMap<HashSet<Integer>, HashSet<Integer>>();
		threes = new HashMap<HashSet<Integer>, HashSet<Integer>>();
		fours = new HashMap<HashSet<Integer>, HashSet<Integer>>();
		fives = new HashMap<HashSet<Integer>, HashSet<Integer>>();
		
		// first we parse each individual cow
		int[][] cows = new int[N][5];

		for (int i = 0; i < N; i++) {
			StringTokenizer tkn = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				cows[i][j] = Integer.parseInt(tkn.nextToken());
			}
		}

		// create singletons
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 5; j++) {
				if (ones.containsKey(cows[i][j])) {
					ones.get(cows[i][j]).add(i);
				}
				else {
					HashSet<Integer> h = new HashSet<Integer>();
					h.add(i);
					ones.put(cows[i][j], h);
				}
			}
		}
		
		ArrayList<Integer> flavors = new ArrayList<Integer>();
		int numFlav = flavors.size();
		// create doubles
		for (int i = 0; i < numFlav; i++) {
			for (int j = i; j < numFlav; j ++) {
				
			}
		}

		for (int i = 0; i < N; i++) {

		}

		for (int i = 0; i < N; i++) {

		}

		for (int i = 0; i < N; i++) {

		}

		br.close();
		pw.close();
	}

}
