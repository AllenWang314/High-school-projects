import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
//		a.add(7);
//		a.add(8);

		
		ArrayList<ArrayList<Integer>> S5 = DyckPermutation.listOfPerm(a);
		// S7 is now the list of DyckPermutations of length 9
		ArrayList<DyckPermutation> Y7 = new ArrayList<DyckPermutation>();
		ArrayList<DyckPermutation> Z7 = new ArrayList<DyckPermutation>();
		for (ArrayList<Integer> perm: S5) {
			DyckPermutation current = new DyckPermutation(perm);
			if (current.valid()){
				Y7.add(current);
				if(current.sumSigZero()) {
					Z7.add(current);
				}
			}
		}

		System.out.println(Y7);
		
		int[] coefficients = new int[30];
		for (DyckPermutation d: Y7) {
			coefficients[d.inversion()]++;
		}
		System.out.println(Arrays.toString(coefficients));


	}
}
