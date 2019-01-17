package arkanoid;


import java.awt.geom.Rectangle2D;
/**
* Klocek przeznaczony do zbicia przez pilke
* 
* @authors Cay Horstmann, Leszek Otkała
*/
public class Block extends Rectangle2D.Float{
	private static float yy, xx;
		   Block(int x, int y)       
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
			public static float getBlockY() 
		   {      
		      return yy;     
		   }
			public static float getBlockX() 
			   {        
			      return xx;     
			   }
			
			public Rectangle2D getShape()
			{
			return new Rectangle2D.Float(x, y, width, height);
			}	
}
