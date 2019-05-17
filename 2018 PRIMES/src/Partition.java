
import java.util.*;

public class Partition {

	private ArrayList<Integer> rows;
	private int size;
	private ArrayList<ArrayList<Point>> rim;
	private int numRows;
	
	/**
	 * Constructs partition on rows, assumed in non-increasing order
	 * @param rows
	 */
	public Partition(int[] rows) {
		this.numRows = rows.length;
		this.size = 0;
		this.rows = new ArrayList<Integer>();
		for(int i = 0; i < this.numRows; i++) {
			this.rows.add(rows[i]);
			this.size += rows[i];
		}
		constructRim();
	}
	
	public Partition(int[] rows, int n) {
		this.numRows = n;
		this.size = 0;
		this.rows = new ArrayList<Integer>();
		for(int i = 0; i < this.numRows; i++) {
			this.rows.add(rows[i]);
			this.size += rows[i];
		}
		constructRim();
	}
	
	
	@SuppressWarnings("unchecked")
	public Partition(ArrayList<Integer> rows) {
		this.numRows = rows.size();
		this.size = 0;
		this.rows = (ArrayList<Integer>) rows.clone();
		for(int i = 0; i < this.numRows; i++) {
			this.size += rows.get(i);
		}
		constructRim();
	}

	/**
	 * Constructs an ArrayList of Points in the p rim
	 * Note that the corner box is (1,1)
	 */
	private void constructRim() {
		this.rim = new ArrayList<ArrayList<Point>>();
		for(int row = 1; row <= this.rows.size(); row++) {
			ArrayList<Point> current = new ArrayList<Point>();
			int col = this.rows.get(row-1);
			while(col > 0 && (row == this.rows.size() || col >= this.rows.get(row))) {
				current.add(new Point(row,col));
				col--;
			}
			this.rim.add(current);
		}
	}

	/**
	 * @return the rows
	 */
	public ArrayList<Integer> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(ArrayList<Integer> rows) {
		this.rows = rows;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the rim
	 */
	public ArrayList<ArrayList<Point>> getRim() {
		return rim;
	}

	/**
	 * @param rim the rim to set
	 */
	public void setRim(ArrayList<ArrayList<Point>> rim) {
		this.rim = rim;
	}

	/**
	 * @return the numRows
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * @param numRows the numRows to set
	 */
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	
	public String toString() {
		return this.rows.toString();
	}
	
	public static void main(String[] args) {
		Partition p1 = new Partition(new int[] {6,4,4,3,1});
		System.out.println(p1);
		System.out.println(p1.getSize());
		System.out.println(p1.getRim());
	}
	
	public boolean satConditions(int b) {
		return false;
		
	}
	
	public Partition mullineuxTranspose() {
		return null;
		
	}
	
	public Partition colReg(int a, int b) {
		return null;
		
	}
	
	public boolean equals() {
		return false;
		
	}
}
