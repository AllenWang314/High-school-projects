import java.io.*;
import java.util.*;

public class Solution2 {

	static int N;
	static int firstStack;
	static Stack<Integer> washed;
	static ArrayList<MiniStack> ministacks;

	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dishes.in")));
		pw.write("5\n" + "4\n" + "5\n" + "2\n" + "3\n" + "1");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("dishes.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));
		Solution2 s = new Solution2();
		N = Integer.parseInt(br.readLine());
		ministacks = new ArrayList<MiniStack>();
		washed = new Stack<Integer>();
		washed.add(0);
		int numWashed = 1;
		
		// we now wash the first dish!
		ministacks.add(s.new MiniStack(Integer.parseInt(br.readLine())));
		while (numWashed <= N) {
			int dish = Integer.parseInt(br.readLine());
			// ministack empty is a problem???
			int i = canInsert(dish);
			if (i == 0) {
				if (i < washed.peek()) break;
				else add(dish, i);
			}
			else if (i == -1) {
				
			}
			else if (i == ministacks.size()) {
				
			}
			else {
				
			}
		}
		// testing
//		MiniStack a = s. new MiniStack(1,3);
//		MiniStack b = s. new MiniStack(5,7);
//		ministacks.add(a);
//		ministacks.add(b);
//		System.out.println(ministacks);
//		System.out.println(canInsert(7));
		pw.write("");
		br.close();
		pw.close();
	}

	private static void add(int dish, int i) {
		// TODO Auto-generated method stub
		
	}

	private static int canInsert(int dish) {
		if (dish < ministacks.get(0).bottom) {
			if (dish > ministacks.get(0).top) {
				return -1; // we need some washing
			}
			return 0;
		}
		if (dish > ministacks.get(ministacks.size() - 1).top) {
			return ministacks.size(); // we need a new pile
		}
		// else we do a binary search
		int lo = 0;
		int hi = ministacks.size();
		while (hi > lo + 1) {
			int mid = (lo + hi) / 2;
			if (ministacks.get(mid).insert(dish)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}
		return lo;
	}

	class MiniStack {

		int top;
		int bottom;

		MiniStack(int dish) {
			top = dish;
			bottom = dish;
		}

		MiniStack(int top, int bottom) {
			this.top = top;
			this.bottom = bottom;
		}

		boolean insert(int dish) {
			return dish <= top;
		}
		
		public String toString() {
			return "top: " + top + " bottom: " + bottom;
		}

	}
}
