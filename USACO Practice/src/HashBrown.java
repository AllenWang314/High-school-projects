import java.util.*;

public class HashBrown {

	public static void main(String[] args) {
		System.out.println("Testing on Hashsets of integers");
		HashSet<Integer> h = new HashSet<Integer>();
		h.add(1);
		System.out.println(h);
		h.add(2);
		System.out.println(h);
		h.add(1);
		System.out.println(h); // works for ints
		
		System.out.println("Testing on Hashsets of arrays");
		HashSet<int[]> i = new HashSet<int[]>();
		int[] d = new int[1];
		int[] e = new int[1];
		i.add(d);
		i.add(e);
		System.out.println(i); // fails for arrays
		
		System.out.println("Testing on Hashsets of ArrayLists");
		HashSet<ArrayList<Integer>> j = new HashSet<ArrayList<Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		a.add(1);
		b.add(2);
		c.add(1);
		j.add(a);
		j.add(b);
		j.add(c);
		System.out.println(j); // works for ArrayLists
				
	}
}
