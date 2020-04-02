import java.util.*;
import java.io.*;

public class Solution2 {
	
	class BIT {

		public long[] tree;

		public BIT(int n) {
			tree = new long[n + 3];
		}

		public void update(int index, long val) {
			index++;
			while (index < tree.length) {
				tree[index] += val;
				index += index & -index;
			}
		}

		public long query(int index) {
			long ret = 0;
			index++;
			while (index > 0) {
				ret += tree[index];
				index -= index & -index;
			}
			return ret;
		}
	}
	
	static int N;
	static int[] arr;
	
	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haircut.in")));
		pw.write("5\n" + 
				"5 2 3 3 0");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("haircut.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")));
		StringTokenizer tkn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tkn.nextToken());
		StringTokenizer tkn2 = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i ++) {
			arr[i] = Integer.parseInt(tkn2.nextToken());
		}
		Solution2 s = new Solution2();
		BIT tree1 = s.new BIT(N);
		BIT tree2 = s.new BIT(N+1);
		for (int i = 0; i < N; i++) {
			tree1.update(N-arr[i], (long) 1);
			if (arr[i] != N) {
				tree2.update(arr[i], (long) tree1.query(N-arr[i]-1));
			}
		}
		pw.write("0\n");
		for (int i = 0; i < N - 1; i++) {
			pw.write("" + tree2.query(i) + "\n");
		}

		br.close();
		pw.close();
	}
	
}
