package arkanoid;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
* Wyświetla animowaną piłkę
* 
* @authors Cay Horstmann, Leszek Otkała
*/
public class Bounce{

	public static void main(String[] args){
	EventQueue.invokeLater(new Runnable(){
		public void run(){
			JFrame frame = new BounceFrame();
			frame.setTitle("Arkanoid");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			}
		});
	}
}
/**
* Ramka zawierająca komponent piłki i przyciski
*/

class BallRunnable implements Runnable{
	private Ball ball;
	private Component component;
	//public static final int STEPS = 1000;
	public static final int DELAY = 7;
	public static boolean cont=true;
	
	/**
	* Tworzy obiekt Runnable
	* @param aBall piłka
	* @param aComponent komponent, w którym odbija się piłka
	*/
	public BallRunnable(Ball aBall, Component aComponent){
		ball = aBall;
		component = aComponent;
		 
	}
	public void run(){
		try{
			while(cont){
				
				cont=ball.move(cont,component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
				
			}	
			}
			catch (InterruptedException e){
			}
	}
}


class BarRunnable implements Runnable, MouseMotionListener{
	private Bar bar;
	private Component component;
	
		
	/**
	* Tworzy obiekt Runnable
	* @param abar - paletka odbijajaca pilke
	* @param aComponent komponent, w którym odbija się piłka
	*/
	public BarRunnable(Bar aBar, Component aComponent) {
		bar = aBar;
		component = aComponent;
		component.addMouseMotionListener(this);
	}
	public void run(){
		
		try{
			while(BallRunnable.cont){
				component.repaint();
				Thread.sleep(10);
				
				}
			}
			catch (InterruptedException e){
				
			}
	}
	public void mouseMoved(MouseEvent e) {                                    
    bar.setX(e.getX()-30);              
                          
 }                                    

 public void mouseDragged(MouseEvent e) 
 {                                      

 }

}

class BounceFrame extends JFrame {
	public static  BallComponent comp;
	public static final int STEPS = 1000;
	public static final int DELAY = 15;
	//private static int x;
/**
* Tworzy ramkę z komponentem zawierającym odbijającą się piłkę oraz przyciskami
* Start i Zamknij.
*/
	public BounceFrame(){
	
	comp = new BallComponent();
	startBar();
	add(comp, BorderLayout.CENTER);
	JPanel buttonPanel = new JPanel();
	addButton(buttonPanel, "Start", new ActionListener(){
		public void actionPerformed(ActionEvent event){
			startGame();
		}
	});
	addButton(buttonPanel, "Zamknij", new ActionListener(){
		public void actionPerformed(ActionEvent event){
	System.exit(0);
	}
	});
	add(buttonPanel, BorderLayout.SOUTH);
	
	pack();
}
/**
* Dodaje przycisk do kontenera.
* @param c kontener
* @param title tytuł przycisku
* @param listener słuchacz akcji przycisku
*/
public void addButton(Container c, String title, ActionListener listener){
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
}
/**
* Dodanie w osobnym wątku paletki do odbijania piłki
*/
	public void startBar() {
		Bar p= new Bar(100,BallComponent.DEFAULT_HEIGHT-50);
		comp.add(p);
		
		Runnable r = new BarRunnable(p, comp);
		Thread t = new Thread(r);
		t.start();
	}
	
	/**
	 * @param x - polozenie poczatkowe klocka
	 * dodawanie klocka do planszy
	 */
	
	public void addBlock(int x){ 
		Block k = new Block(x,50);
		comp.add(k);
	}
		
	
	
	/**
	 * dodawanie nowej piłki poruszającej się w osobnym wątku
	 */
	public void addBall(){
	BallRunnable.cont=true;
	Ball b = new Ball();
	comp.add(b);
	Runnable r = new BallRunnable(b, comp);
	Thread t = new Thread(r);
	t.start();
	}

	public void startGame() {
		if(!BallRunnable.cont) {
			BounceFrame.comp.removeAllBalls();
			BounceFrame.comp.removeAllBlocks();
			}
		if(BounceFrame.comp.blocks.size()==0) {
		for(int i=0;i<6;i++){
			addBlock(i*70+20);
			}
		}
			addBall();
	}
}
