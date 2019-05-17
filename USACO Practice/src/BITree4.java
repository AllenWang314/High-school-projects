// official fenwick tree written from USACO
public class BITree4 {

	public int[] tree;

	public BITree4(int n) {
		tree = new int[n + 5];
	}

	public void update(int index, int val) {
		index++;
		while (index < tree.length) {
			tree[index] += val;
			index += index & -index;
		}
	}

	public int query(int index) {
		int ret = 0;
		index++;
		while (index > 0) {
			ret += tree[index];
			index -= index & -index;
		}
		return ret;
	}
}
