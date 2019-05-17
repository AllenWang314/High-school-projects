import java.util.*;

public class Determinant {

	public static final ArrayList<Integer> empty = new ArrayList<Integer>(); 

	public static int determinant (int[][] matrix) {
		int temporary[][];
		int result = 0;

		if (matrix.length == 1) {
			result = matrix[0][0];
			return (result);
		}

		if (matrix.length == 2) {
			result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
			return (result);
		}

		for (int i = 0; i < matrix[0].length; i++) {
			temporary = new int[matrix.length - 1][matrix[0].length - 1];

			for (int j = 1; j < matrix.length; j++) {
				for (int k = 0; k < matrix[0].length; k++) {
					if (k < i) {
						temporary[j - 1][k] = matrix[j][k];
					} else if (k > i) {
						temporary[j - 1][k - 1] = matrix[j][k];
					}
				}
			}

			result += matrix[0][i] * Math.pow (-1, (int) i) * determinant (temporary);
		}
		return (result);
	}
	
	public static int factorial(int n) {
		if (n == 0) {
			return 1;
		}
		return n*factorial(n-1);
	}
	
	public static int b(int n, int k){
		if (k>n) {
			return 0;
		}
		return factorial(n) / factorial(k) / factorial(n-k);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<Integer>> addElement(ArrayList<ArrayList<Integer>> list, int element){
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> l: list) {
			ArrayList<Integer> m = ((ArrayList<Integer>)l.clone());
			m.add(element);
			ans.add(m);
		}
		return ans;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> list){
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		if (list.size() == 0) {
			ans.add(empty);
			return ans;
		}
		int first = list.get(0);
		ArrayList<Integer> list2 = ((ArrayList<Integer>) list.clone());
		list2.remove(0);
		ans.addAll(addElement(subsets((ArrayList<Integer>) list2.clone()),first));
		ans.addAll(subsets((ArrayList<Integer>) list2.clone()));
		return ans;
	}
	
	public static void main(String[] args) {
//		System.out.println(determinant(new int[][] {{3,1,4,6,7,45,54543,45435},{4545,456,6564,-1,-5,9,-100,-345},{777,77,777,235,-65,53,45,0},{1,-678,-624, 20202, 3423,-545,-656,-77}, {-3423,9,99,-9999,-99,9999,343,3423},{345,45634,1,7,8,8,2345,56},{-5678,-234,436,-982,4467,-74,890,76457}, {-67,-234,-4367,-536,36,16,7,8}}));
//		the determinant method actually runs reasonably fast
		
//		System.out.println(determinant(new int[][] {{8, 56, 1},{1, 35, 1},{0,1,1}}));
//		System.out.println(determinant(new int[][] {{b(6,2), b(6,0)},{b(2,2),b(2,0)}}));
//		System.out.println(determinant(new int[][] {{b(6,4), b(6,2), b(6,0)},{b(4,4),b(4,2),b(4,0)},{b(2,4),b(2,2),b(2,0)}}));
//		System.out.println(1+5+14+19+14+35+26+10+40+61);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		System.out.println("Testing subsets");
		ArrayList<ArrayList<Integer>> subsetList7 = subsets(list);
		list.add(8);
		
		ArrayList<ArrayList<Integer>> S8 = DyckPermutation.listOfPerm(list);

		ArrayList<DyckPermutation> Z8 = new ArrayList<DyckPermutation>();

		Map<ArrayList<Integer>, Integer> freq = new HashMap<ArrayList<Integer>, Integer>();
		
		for (ArrayList<Integer> perm: S8) {
			DyckPermutation current = new DyckPermutation(perm);
			if (current.valid()){
				Z8.add(current);
			}
		}
		
		for (ArrayList<Integer> set: subsetList7) {
			freq.put(set, 0);
		}
		
		Set<ArrayList<Integer>> setOfDescents = new HashSet<ArrayList<Integer>>();
		
		for(DyckPermutation d: Z8) {
			if (!setOfDescents.contains(d.getDescentSet())) {
				setOfDescents.add(d.getDescentSet());
			}
		}
		

		for(ArrayList<Integer> a: setOfDescents) {
			ArrayList<ArrayList<Integer>> asubsets = subsets(a);
			if (a.size() % 2 == 0) {
				for (ArrayList<Integer> asub: asubsets) {
					freq.replace(asub, freq.get(asub) +1);
				}
			}
			else {
				for (ArrayList<Integer> asub: asubsets) {
					freq.replace(asub, freq.get(asub) -1);
				}
			}

		}
		System.out.println(setOfDescents);
		System.out.println(freq);
		
		Set<ArrayList<Integer>> f = new HashSet<ArrayList<Integer>>();
		
		for (ArrayList<Integer> sub : freq.keySet()) {
			f.add(sub);
		}
		
		for (ArrayList<Integer> sub : f) {
			if(freq.get(sub) == 0) {
				freq.remove(sub);
			}
		}
		System.out.println(freq);
	}
}
