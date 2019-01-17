package arkanoid;

import java.awt.geom.*;

import javax.swing.JOptionPane;
//import java.util.Random;
/**
* Piłka, która porusza się i odbija od krawędzi prostokąta
* 
* @authors Cay Horstmann, Leszek Otkała
*/
public class Ball{
	private static final int XSIZE = 10;
	private static final int YSIZE = 10;
	private double x = Bar.getBarX();
	private double y = Bar.getBarY();
	private double dx = 1;
	private double dy = -1;
	private Block k1;
	/* Przesuwa piłkę do następnego położenia, odwracając kierunek, jeśli piłka uderzy w krawędź lub w paletkę
	*/
	
	public boolean move(boolean cont,Rectangle2D bounds){
		x += dx;
		y += dy;
		
		if (x < bounds.getMinX()){
			x = bounds.getMinX();
			dx = -dx;
		}
		if (x + XSIZE >= bounds.getMaxX()){
			x = bounds.getMaxX() - XSIZE;
			dx = -dx;
		}
		
		if (y < bounds.getMinY()){
			y = bounds.getMinY();
			dy = -dy;
		}
		if (y + YSIZE >= bounds.getMaxY()){
			y = bounds.getMaxY() - YSIZE;
			//cont=false;
			dy = -dy;
			
			BounceFrame.comp.removeBall(this);
			
			if(BounceFrame.comp.balls.size()==0)
				cont=false;
		}
		if (y ==Bar.getBarY()&&x<=Bar.getBarX()+60&&x>Bar.getBarX()){
			dy = -dy;															//odbicie piłiki od "paletki"
		}
		
			for (Block k : BounceFrame.comp.blocks)
				if(y==k.y&&x<=k.x+60&&x>k.x) {									//zderzenie pilki z blokiem
					dy=-dy;														//odbicie piłki
					k1=k;														//zapisanie bloku do usuniecia
				}
			BounceFrame.comp.removeBlock(k1);									// przekazanie bloku do usuniecia
			if(BounceFrame.comp.blocks.size()==0) {
				cont=false;
				JOptionPane.showMessageDialog(null,"BRAWO \nWYGRAŁEŚ!!!", "Koniec Gry", JOptionPane.INFORMATION_MESSAGE);

			}
		return cont;
	}
	/**
	* Ustawia piłkę w jej aktualnym położeniu
	*/
	public Ellipse2D getShape() {
	
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
}
