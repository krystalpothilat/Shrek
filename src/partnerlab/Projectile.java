package partnerlab;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/* class to represent a Ship object in a game */
public class Projectile {

	//attributes
	public boolean active = false;
	private int x, y; 	 //position	
	private boolean alive; 	
	private int width, height; //size
	private Image img; 			//Image for object
	private int vx, vy; 		//velocities
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	
	Projectile proj[] = new Projectile[10];
	//constructor -takes in the filename
	public Projectile(String fileName) {
		x = 200;
		y = 200;
		vx = 0;
		vy = 0;
		width = 100;
		height = 50;
		
		//add projectile objects in the array of projectiles
		
		for(int i = 0; i < proj.length; i++){
			proj[i] = new Projectile("onion2.png"+ "", x, y, 5, 0);
		}
				

		//do not touch
		img = getImage(fileName);
		updateShip();
	}

		//2nd constructor - allows placement (location) of the object
	public Projectile(String fileName, int paramX, int paramY, int paramVX, int paramVY) {
		//allows setting of velocity for projectiles
		
		x = paramX;
		y = paramY;
		vx = paramVX;
		vy = paramVY;

		
		
		//helper functions to handle image drawing
		img = getImage(fileName);
		//helper function for location of image
		//if you update x and y, call updateShip
		updateShip();
	}
	
	public boolean collided(Enemy citizens){
		//Check protagonist to enemy collision
		Rectangle pRect = new Rectangle(x,y,50,50); //width and height of projectile
		Rectangle sRect = new Rectangle(citizens.getX(),citizens.getY(),200,200); //width and height of ship
		//System.out.println("projectile");
		return pRect.intersects(sRect);
		
	}
	
	

	public void setVx(int vx) {
		vy = 0;
		this.vx = vx;
	}

	public void setVy(int vy) {
		vx=0;
		this.vy = vy;
	}

	// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		x+=vx;
		updateShip(); //call every time
	}

	private void updateShip() {
		tx.setToTranslation(x, y);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Projectile.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		updateShip(); //must be calling 
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
}

