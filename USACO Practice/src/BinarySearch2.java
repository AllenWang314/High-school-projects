import java.io.*;
import java.util.*;


public class BinarySearch2 {

	private static int N;
	private static PriorityQueue<Mountain> queue;
	private static ArrayList<Mountain> sorted;


	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.in")));
		pw.write("3\n" + 
				"4 6\n" + 
				"7 2\n" + 
				"2 5");
		pw.close();
	}
	
	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		
		BinarySearch2 s = new BinarySearch2();
		N = Integer.parseInt(br.readLine());
		queue = new PriorityQueue<Mountain>(s. new MountainComparatorHeight());

		for (int i = 0; i< N; i++) {
			StringTokenizer tkn = new StringTokenizer(br.readLine());
			queue.add(s.new Mountain(Integer.parseInt(tkn.nextToken()), Integer.parseInt(tkn.nextToken())));

		}

		Mountain dummy1 = s.new Mountain(-1,-1);
		Mountain dummy2 = s.new Mountain(1000000001,-1);
		sorted = new ArrayList<Mountain>();
		sorted.add(dummy1);
		sorted.add(dummy2);
		
		for (int i = 0; i < N; i++) {
			Mountain m = queue.poll();
			int index = findIndex(m);
			if (visible(m, sorted.get(index-1),sorted.get(index))) {
				sorted.add(index, m);
			}
		}
		
// testing for Binary Search	
//		for (int i = 0; i < N-1; i++) {
//		Mountain m = queue.poll();
//
//			sorted.add(m);
//			Collections.sort(sorted, s.new MountainComparatorWidth());
//	}
//		System.out.println(sorted);
//		
//		System.out.println(findIndex(queue.poll()));
		
//		// testing isVisible
//		Mountain m1 = s.new Mountain(1,10);
//		Mountain m2 = s.new Mountain(7,10);
//		Mountain m3 = s.new Mountain(3,9);
//		System.out.println(visible(m3,m1,m2));

		System.out.println(sorted);
		
		pw.write("" + (sorted.size()-2));
		br.close();
		pw.close();
	}

	/*
	 * returns the place where m would be inserted
	 */
	private static int findIndex(Mountain m) {
		int l = 0, r = sorted.size() - 1; 
        while (l <= r) { 
            int mid = l + (r - l) / 2; 
  
            // Check if x is present at mid 
            if (sorted.get(mid).x == m.x) return mid; 
  
            // If x greater, ignore left half 
            if (sorted.get(mid).x < m.x) l = mid + 1; 
  
            // If x is smaller, ignore right half 
            else r = mid - 1; 
        } 
        return l;
	}
	
	private static boolean visible(Mountain m, Mountain m1, Mountain m2) {
		double lslope = (double) (m.y - m1.y)/ (m.x - m1.x);
		double rslope = (double) (m.y - m2.y)/ (m.x - m2.x);
		return !(lslope <= -1 || rslope >= 1);
	}

	class Mountain {
		public int x;
		public int y;
		
		public Mountain(int x, int y) {
			this.x = x;
			this.y = y;
		}
        
		public String toString() {
    			return "(" + x + ", " + y + ")";
        }
	}
	
	class MountainComparatorHeight implements Comparator<Mountain>{ 
        
        // Overriding compare()method of Comparator  
                    // for descending order of cgpa 
        public int compare(Mountain s1, Mountain s2) { 
            return s2.y - s1.y;
        } 
       
    } 
	
	class MountainComparatorWidth implements Comparator<Mountain>{ 
        
        // Overriding compare()method of Comparator  
                    // for descending order of cgpa 
        public int compare(Mountain s1, Mountain s2) { 
            return s1.x - s2.x;
        } 
       
    } 
	
}
