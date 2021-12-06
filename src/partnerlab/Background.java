package partnerlab;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class Background {
	private int Bx, By; 	 //position	
	//private boolean alive; 	
	private int Bwidth, Bheight; //size
	private Image Bimg; 			//Image for object
	private int Bvx; 		//velocities
	//private AffineTransform Btx = AffineTransform.getTranslateInstance(Bx, By);

	
	public Background(String paramfileName) {
		Bx = 0;
		By = 0;
		Bwidth = 1400;
		Bheight = 800;
		
		//do not touch
		Bimg = getImage(paramfileName);
		updateBackground();
	}

		private void updateBackground() {
		// TODO Auto-generated method stub
		
	}

	//2nd constructor - allows placement (location) of the object
	public Background(String fileName, int paramX, int paramY) {
		Bx = paramX;
		By = paramY;
		Bwidth = 1400;
		Bheight = 800;
		
		//helper functions to handle image drawing
		Bimg = getImage(fileName);
		//helper function for location of image
		//if you update x and y, call updateShip
		updateBackground();
		
	}
	
	public void setImage(String fileName) {
		Bimg = getImage(fileName);
	}
	
	public void move(int paramBvx){
		Bx += paramBvx;
	}
	
	public void setBVx(int vx) {
		Bvx = 0;
		this.Bvx = vx;
	}


	// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(Bimg, Bx,By, Bwidth,Bheight, null);
		g2.drawImage(Bimg, Bx,By, Bwidth,Bheight, null);
	}
	

//	private void updateBackground() {
//		Btx.setToTranslation(Bx, By);
	//}

	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	public int getBX() {
		return Bx;
	}

	public void setBX(int x) {
		this.Bx = x;
		updateBackground(); //must be calling 
	}

	public int getBY() {
		return By;
	}

	public void setBY(int y) {
		this.By = y;
	}

	

}

