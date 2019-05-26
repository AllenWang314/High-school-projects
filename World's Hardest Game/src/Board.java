import java.util.ArrayList;
import java.util.HashSet;

public class Board {
	
//	public HashSet<Vector> getInterior() {
//		return interior;
//	}
//
//	public void setInterior(HashSet<Vector> interior) {
//		this.interior = interior;
//	}
//
//	private HashSet<Vector> interior;
//	
//	public Board() {
//		interior = new HashSet<Vector>();
//		addRectangle(10,10,400,400);
//	}
//	
//	public Board(HashSet<Vector> interior) {
//		this.interior = interior;
//	}
//	
//	public boolean isInBounds(Sprite s) {
//		for (int i = s.getxCoord(); i < s.getxCoord() + s.getWidth(); i++) {
//			for (int j = s.getyCoord(); i < s.getyCoord() + s.getHeight(); j++) {
//				if (!interior.contains(new Vector(i,j))) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//
//	public boolean isInBounds(Sprite s, Vector v) {
//		for (int i = s.getxCoord()+v.getX(); i < s.getxCoord() + s.getWidth()+v.getX(); i++) {
//			for (int j = s.getyCoord()+v.getY(); i < s.getyCoord() + s.getHeight()+v.getY(); j++) {
//				if (!contains(new Vector(i,j))) {
//					System.out.println("Not in bounds");
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//	
//	public boolean contains(Vector v2) {
//		for (Vector v1 : interior) {
//			if (v1.equals(v2)) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public void addRectangle(int x, int y, int width, int height) {
//		for (int i = x; i < x+width; i++) {
//			for (int j = y; j< y+height; j++) {
//				interior.add(new Vector(i,j));
//			}
//		}
//	}
	
	private ArrayList<Rectangle> rects;
	
	public Board() {
		rects = new ArrayList<Rectangle>();
		addRectangle(10,10,1100,700);
	}
	
	public void addRectangle(int x1, int y1, int x2, int y2) {
		rects.add(new Rectangle(x1,y1,x2,y2));
	}
	
	public boolean isInBounds(Sprite s) {
		for (int i = 0; i < rects.size(); i++) {
			Rectangle rect = rects.get(i);
			if (s.getxCoord() > rect.getX1() && s.getxCoord() + s.getWidth() < rect.getX2() && s.getyCoord() > rect.getY1() && s.getyCoord() + s.getHeight() < rect.getY2()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isInBounds(Sprite s, Vector v) {
		for (int i = 0; i < rects.size(); i++) {
			Rectangle rect = rects.get(i);
			if (s.getxCoord()+v.getX() > rect.getX1() && s.getxCoord()+v.getX() + s.getWidth() < rect.getX2() && s.getyCoord()+v.getY() > rect.getY1() && s.getyCoord()+v.getY() + s.getHeight() < rect.getY2()) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Rectangle> getRects() {
		return rects;
	}

	public void setRects(ArrayList<Rectangle> rects) {
		this.rects = rects;
	}
	
	
}
