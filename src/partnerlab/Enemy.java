package partnerlab;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/* class to represent a Ship object in a game */
public class Enemy {

	//attributes
	private int x, y; 	 //position	
	private boolean alive;
	private int width, height; //size
	private Image img; 			//Image for object
	private int vx, vy; 		//velocities
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	private int cntr, rate = 300; //firing automatically
//	Projectile proj[] = new Projectile[2];
	
	//constructor -takes in the filename
	public Enemy(String fileName) {
		x = 1400;
		y = 600;
		vx = -3;
		vy = 0;
		width = 100;
		height = 50;
		
		//do not touch
		img = getImage(fileName);
		updateShip();
		
		//add projectile objects in the array of projectiles
//		for(int i = 0; i < proj.length; i++){
//			proj[i] = new Projectile("fork.png", x, y, -6, 0);
//		}
		
		
		
		
	}
//
//		//shoot method that will activate a projectile in the array
//		public void fire(){
//			
//			for(int i = 0; i < proj.length; i++){
//				if(!proj[i].active){
//					proj[i].active = true; //turn on a projectile
//					
//					//move projectile to location of ship
//					proj[i].setX(x+vx);
//					proj[i].setY(y);
//					
//					//exit immediately after activating one projectile
//					break; //break will exit the nearest loop
//				}
//			}
//			
//		}
//			
//		
	
		//2nd constructor - allows placement (location) of the object
	public Enemy(String fileName, int paramX, int paramY, int paramVx) {
		x = paramX;
		y = paramY;
		vx = paramVx;
		vy = 0;
		width = 100;
		height = 50;
		
		
		
		//helper functions to handle image drawing
		img = getImage(fileName);
		//helper function for location of image

		//add projectile objects in the array of projectiles
//		for(int i = 0; i < proj.length; i++){
//			proj[i] = new Projectile("fork.png", x, y, -6, 0);
//		}
		
		
		//if you update x and y, call updateShip
		updateShip();
	}
	
	public void nextLevel() {
			x = -500;
			y = 0;
			vx = 0;
		
	}
	public void setImage(String fileName) {
	
		img = getImage(fileName);
	}
	
	public void move(int paramBvx){
		x += paramBvx;
	}
	
	public boolean collided(Enemy s){
		//Check protagonist to enemy collision
		Rectangle enemy = new Rectangle(x,y,200,200);
		Rectangle ship = new Rectangle(s.getX(),s.getY(),200,200);
		boolean coll = enemy.intersects(ship); 
		
		// visit all active projectiles 
		//check if they're "colliding" with the Ship object s
//		for(int i = 0; i < proj.length; i++){
//			if(proj[i].active){//active projectile found
//				if(proj[i].collided(s)){
//					
//					//System.exit(-1); //exit -- remove later
//					System.out.println("enemy");
//				
//					coll = true;
//					
//					break;
//				}
//			}
		
		
		return coll; //coll is set to true if ship collides with enemy or projectile
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
		
		
//		cntr++;
//		if(cntr%rate==0){
//			fire();
//		}
//		
//		//draw active projectiles for ship
//		for (int i = 0; i < proj.length; i++){
//			//check to see that the projectile object is instantiated
//			if(proj[i]!=null && proj[i].active){
//				proj[i].paint(g);
//			}
//		}
//		
//		
	}
	

	private void updateShip() {
		tx.setToTranslation(x, y);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Enemy.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public int getX() {
//		if(x <= 1200) {
//			fire();
//		}
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

	public int getVx() {
		return vx;
	}

}

