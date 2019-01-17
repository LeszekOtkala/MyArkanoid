package arkanoid;


import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
* Komponent rysujący piłki
* 
* @authors Cay Horstmann, Leszek Otkała
*/
public class BallComponent extends JPanel /*implements MouseMotionListener*/ {
	
	private static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
	private Bar p;
	public java.util.List<Ball> balls = new ArrayList<>();
	public java.util.List<Block> blocks = new ArrayList<>();
	
	/**
	* Dodaje piłkę do komponentu
	* @param b piłka, która ma zostać dodana
	*/
	
	public void add(Ball b){
		balls.add(b);
		
	}
	public void removeBall(Ball b) {
		balls.remove(b);
		
	}
	public void removeAllBalls() {
		
		int n=balls.size();
		for(int i=n; i>0;i--) {
			balls.remove(i-1);
		}
		
	}
	
	public void add(Block k){
		blocks.add(k);
		
	}
	public void removeBlock(Block k) {
		blocks.remove(k);
		
	}
public void removeAllBlocks() {
		
		int n=blocks.size();
		for(int i=n; i>0;i--) {
			blocks.remove(i-1);
		}
		
	}
	
	public void add(Bar p){
		this.p=p;
	}
	public void paintComponent(Graphics g){
	
	super.paintComponent(g); // Czyszczenie tła
	
	Graphics2D g2 = (Graphics2D) g;

	for (Ball b : balls){
		g2.fill(b.getShape());
		}
	
	for (Block k : blocks){
		g2.fill(k.getShape());
		}
	
		g2.fill(p.getShape());
	}
	
	
	
	public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH,
	DEFAULT_HEIGHT); }
	
	
	
}