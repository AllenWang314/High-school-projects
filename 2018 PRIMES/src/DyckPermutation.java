import java.util.*;

public class DyckPermutation {

	private ArrayList<Integer> perm;
	private ArrayList<Integer> sig;
	private ArrayList<Integer> partial;
	private int sumSig;
	private int last;
	private ArrayList<Integer> descentSet;
	
	public DyckPermutation(ArrayList<Integer> perm) {
		this.perm = perm;
		this.last = this.perm.get(perm.size()-1);
		this.sig = new ArrayList<Integer>();
		this.partial = new ArrayList<Integer>();
		this.descentSet = new ArrayList<Integer>();
		for (int i = 0; i < this.perm.size()-1; i++) {
			if (this.perm.get(i+1)-this.perm.get(i) > 0){
				this.sig.add(1);
			}
			else {
				this.sig.add(-1);
			}
//			this.sig.add((this.perm.get(i+1)-this.perm.get(i))/(Math.abs(this.perm.get(i+1)-this.perm.get(i))));
		}
		this.partial.add(sig.get(0));
		for (int i = 1; i < this.perm.size()-1; i++) {
			this.partial.add(this.partial.get(i-1) + this.sig.get(i));
		}
		for (int i = 0; i<= this.perm.size()-2;i++) {
			if (sig.get(i) < 0) {
				descentSet.add(i+1);
			}
		}
		this.sumSig = this.partial.get(this.partial.size()-1);
	}
	
	public boolean sumSigZero() {
		return (sumSig == 0);
	}
	
	public boolean valid() {
		for (int sum : partial) {
			if (sum < 0) {
				return false;
			}
		}
		return true;
	}

	public boolean almostValid() {
		for (int i = 0; i < perm.size()-2; i++) {
			if (partial.get(i) < 0) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Integer> getPerm() {
		return perm;
	}

	public void setPerm(ArrayList<Integer> perm) {
		this.perm = perm;
	}

	public ArrayList<Integer> getSig() {
		return sig;
	}

	public void setSig(ArrayList<Integer> sig) {
		this.sig = sig;
	}

	public ArrayList<Integer> getPartial() {
		return partial;
	}

	public void setPartial(ArrayList<Integer> partial) {
		this.partial = partial;
	}

	public Integer getSumSig() {
		return sumSig;
	}

	public void setSumSig(Integer sumSig) {
		this.sumSig = sumSig;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}
	
	public ArrayList<Integer> getDescentSet(){
		return descentSet;
	}

	public String toString() {
		return perm.toString();
	}
	
	public int size() {
		return this.getPerm().size();
	}
	
	public DyckPermutation inverse() {
		ArrayList<Integer> inverse = new ArrayList<Integer>();
		for(int i = 1; i<= this.size(); i++) {
			inverse.add(i-1, this.getPerm().indexOf(i)+1);
		}
		return new DyckPermutation(inverse);
	}
	

	public static ArrayList<ArrayList<Integer>> listOfPerm(ArrayList<Integer> list) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		if (list.size() <2) {
			ans.add(list);
			return ans;
		}
		for (int i = 0; i < list.size(); i++) {
			ans.addAll(addDeep(list, i));
		}
		return ans;
	}
		
	public static ArrayList<ArrayList<Integer>> addDeep(ArrayList<Integer> list, int index) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer> newList =  (ArrayList<Integer>) list.clone();
		int element = newList.remove(index);
		ArrayList<ArrayList<Integer>> permList = listOfPerm(newList);
		for (int i = 0; i < permList.size(); i++) {
			permList.get(i).add(0, element);
		}
		return permList;
	}
	
	public int major() {
		int ans = 0;
		for (int i: this.getDescentSet()) {
			ans += i;
		}
		return ans;
	}
	
	public int inversion() {
		int ans = 0;
		for (int i = 0; i< this.size(); i++) {
			for (int j =i+1; j< this.size(); j++) {
				if(this.getPerm().get(i) > this.getPerm().get(j)) {
					ans ++;
				}
			}
		}
		return ans;
	}
	
	/**
	 * Precondition: i must be in the correct range or else it returns -1
	 * @param i
	 * @return the last element that was at height i
	 */
	public int lastAtHeight(int i) {
		int currentH = this.getSumSig();
		int index = this.size()-1;
		ArrayList<Integer> perm = this.getPerm();
		while(currentH != i && index >= 0) {
			if(perm.get(index) > perm.get(index-1)) {
				currentH--;
			}
			else {
				currentH++;
			}
			index--;
		}
		if (currentH == i) {
			return index;
		}
		return -1;
	}
	
	public String fancyToString() {
		String ans = "";
		int wt = this.lastAtHeight(this.getSumSig()/2);
		int wb = this.lastAtHeight(this.getSumSig()/2-1);
		ArrayList<Integer> perm = this.getPerm();
		for(int i = 0; i< this.size(); i++) {
			if(i == wt || i== wb) {
				ans += "|"+ perm.get(i) + "|, ";
			}
			else {
				ans += perm.get(i) + ", ";
			}
		}
		return ans;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		ArrayList<ArrayList<Integer>> S1 = listOfPerm(list);
		list.add(2);
		ArrayList<ArrayList<Integer>> S2 = listOfPerm(list);
		list.add(3);
		ArrayList<ArrayList<Integer>> S3 = listOfPerm(list);
		list.add(4);
		ArrayList<ArrayList<Integer>> S4 = listOfPerm(list);
		list.add(5);
		ArrayList<ArrayList<Integer>> S5 = listOfPerm(list);
		list.add(6);
		ArrayList<ArrayList<Integer>> S6 = listOfPerm(list);
		list.add(7);
		ArrayList<ArrayList<Integer>> S7 = listOfPerm(list);
		list.add(8);
		ArrayList<ArrayList<Integer>> S8 = listOfPerm(list);
		list.add(9);
		ArrayList<ArrayList<Integer>> S9 = listOfPerm(list);
//		list.add(10);
//		ArrayList<ArrayList<Integer>> S10 = listOfPerm(list);
		
		ArrayList<DyckPermutation> Z1 = new ArrayList<DyckPermutation>();
		ArrayList<DyckPermutation> Z2 = new ArrayList<DyckPermutation>();
		ArrayList<DyckPermutation> Z3 = new ArrayList<DyckPermutation>();
		ArrayList<DyckPermutation> Z4 = new ArrayList<DyckPermutation>();
		ArrayList<DyckPermutation> Z5 = new ArrayList<DyckPermutation>();
		ArrayList<DyckPermutation> Z6 = new ArrayList<DyckPermutation>();
		ArrayList<DyckPermutation> Y6 = new ArrayList<DyckPermutation>(); // ***
		ArrayList<DyckPermutation> Z7 = new ArrayList<DyckPermutation>();
		ArrayList<DyckPermutation> Z8 = new ArrayList<DyckPermutation>();
		ArrayList<DyckPermutation> Z9 = new ArrayList<DyckPermutation>();
		ArrayList<DyckPermutation> D = new ArrayList<DyckPermutation>();

//		ArrayList<Permutation> Z10 = new ArrayList<Permutation>();
		
		for (ArrayList<Integer> perm: S1) {
			DyckPermutation current = new DyckPermutation(perm);
			if (current.getSumSig() == 0 && current.valid()){
				Z1.add(current);
			}
		}
		for (ArrayList<Integer> perm: S2) {
			DyckPermutation current = new DyckPermutation(perm);
			if (current.getSumSig() == 0 && current.valid()){
				Z2.add(current);
			}
		}
		for (ArrayList<Integer> perm: S3) {
			DyckPermutation current = new DyckPermutation(perm);
			if (current.getSumSig() == 0 && current.valid()){
				Z3.add(current);
			}
		}
		for (ArrayList<Integer> perm: S4) {
			DyckPermutation current = new DyckPermutation(perm);
			if (current.getSumSig() == 0 && current.valid()){
				Z4.add(current);
			}
		}
		for (ArrayList<Integer> perm: S5) {
			DyckPermutation current = new DyckPermutation(perm);
			if(current.valid()) {
				D.add(current);
			}
			if (current.getSumSig() == 0 && current.valid()){
				Z5.add(current);
			}
		}
		
		for (ArrayList<Integer> perm: S6) {
			DyckPermutation current = new DyckPermutation(perm);
			if (current.getSumSig() == 0 && current.valid()){
				Z6.add(current);
			}
		}
		
		for (ArrayList<Integer> perm: S6) {
			DyckPermutation current = new DyckPermutation(perm);
			if (current.almostValid() && current.getSumSig() == -1){
				Y6.add(current);
			}
		}
	
		for (ArrayList<Integer> perm: S7) {
			DyckPermutation current = new DyckPermutation(perm);
			if (current.getSumSig() == 0 && current.valid()){
				Z7.add(current);
			}
		}
		
		for (ArrayList<Integer> perm: S8) {
			DyckPermutation current = new DyckPermutation(perm);
			if (current.getSumSig() == 0 && current.valid()){
				Z8.add(current);
			}
		}
		for (ArrayList<Integer> perm: S9) {
		DyckPermutation current = new DyckPermutation(perm);
		if (current.valid()){
			Z9.add(current);
		}
	}
//		
//		System.out.println(Z1.size());
//		System.out.println(Z2.size());
//		System.out.println(Z3.size());
//		System.out.println(Z4.size());
//		System.out.println(Z5.size());

		for(DyckPermutation d: D) {
			System.out.println(d);
		}
		

	}
}
