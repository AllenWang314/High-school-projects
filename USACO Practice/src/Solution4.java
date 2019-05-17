import java.util.HashSet;

public class Solution4 {

	public static int solution(int[] A) {
		if(A.length == 0) return 0;
		HashSet<Integer> descent = new HashSet<Integer>();
		HashSet<Integer> ascent = new HashSet<Integer>();
		descent.add(A.length-1);
		ascent.add(A.length-1);
		
		int[] nextLargest = new int[A.length];
		int[] nextSmallest = new int[A.length];
		
		for(int i = 0; i < A.length; i++) {
			int smallIndex = A.length;
			int smallDiff = 101;
			int bigIndex = A.length;
			int bigDiff = 101;
			for (int j = i+1; j < A.length; j++) {
				if (A[j] < A[i] && A[i] - A[j] < smallDiff) {
					smallDiff = A[i] - A[j];
					smallIndex = j;
				}
				else if (A[j] > A[i] && A[j] - A[i] < bigDiff) {
					bigDiff = A[j] - A[i];
					bigIndex = j;
				}
			}
			nextLargest[i] = bigIndex;
			nextSmallest[i] = smallIndex;
		}
//		System.out.println(Arrays.toString(nextLargest));
//		System.out.println(Arrays.toString(nextSmallest));

		
		for (int i = A.length - 2; i >= 0; i--) {
			update(A, descent, ascent, i, nextLargest, nextSmallest);
		}

		return ascent.size();
	}
	
	public static void update(int[] A, HashSet<Integer> descent, HashSet<Integer> ascent, int index, int[] nextLargest, int[] nextSmallest) {
		if (descent.contains(nextLargest[index])) {
			ascent.add(index);
		}
		if (ascent.contains(nextSmallest[index])) {
			descent.add(index);
		}
	}
	
//	class Node {
//		
//		public int value;
//		public int index;
//		
//		public Node(int value, int index){
//			this.value = value;
//			this.index = index;
//		}
//	}
//	
//	class nodeComparator implements Comparator<Node> {
//	    
//	     public int compare(Node a, Node b) {
//	        return a.value - b.value;
//	    }
//	}
	
	public static void main(String[] args) {
		int[] A = new int[] {10, 11, 14, 11, 10};
		System.out.println(solution(A));

	}

}
