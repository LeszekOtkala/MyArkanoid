package arkanoid;


import java.awt.geom.Rectangle2D;
/**
* Paletka odbijajaca pilke
* 
* @authors Cay Horstmann, Leszek Otkała
*/

public class Bar extends Rectangle2D.Float{
	private static float yy, xx;
		   Bar(int x, int y)       
		   {                  
		      this.x=x;       
		      this.y=y;  
		      this.width=60;  
		      this.height=10; 
		      
		   }                  
		   void przypiszXY() {
			   xx=x;
			   yy=y;
		   }
		   void setX(int x) 
		   {                
		      this.x=x;  
		      przypiszXY();
		   }   
			public static float getBarY() 
		   {      
		      return yy;     
		   }
			public static float getBarX() 
			   {        
			      return xx;     
			   }
			
			public Rectangle2D getShape()
			{
			return new Rectangle2D.Float(x, y, width, height);
			}	
}
