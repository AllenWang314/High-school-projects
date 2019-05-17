/*
 * Allen's super inefficient version of a BITree
 * The sum and increment are optimized
 * Get is 2 log n, set is 3 log n
 */
public class BITree2 {

	public static final int maxSize = 1000000;
	public int[] tree;
	
	/*
	 * method returns the sum of indices 0 to index INCLUSIVE
	 */
	public int sum(int index) {
		int sum = 0;
		index++;
		while (index > 0) {
			sum += tree[index];
			index -= index & (-index);
		}
		return sum;
	}

	public void increment(int index, int val) {
		index++;
		while (index <= maxSize) {
			tree[index] += val;
			index += index & (-index);
		}
	}
	
	public int get(int index) {
		return sum(index) - sum(index-1);
	}
	
	public void set(int index, int val) {
		increment(index, val - get(index));
	}

	public BITree2() {
		tree = new int[maxSize];
	}

	public static void main(String args[]) {
		BITree2 b = new BITree2();
		b.increment(0, 1);
		b.increment(1, 3);
		b.increment(2, 5);
		b.increment(3, 7);
		b.increment(4, 9);
		b.increment(5, 11);


		for (int i = 0; i < 6; i++) {
			System.out.println(b.get(i));
		}
		
	}
}
