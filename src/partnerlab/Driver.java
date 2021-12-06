package partnerlab;

	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.event.MouseMotionListener;
	import java.awt.geom.Point2D;
	import java.io.BufferedInputStream;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.InputStream;
	import java.util.ArrayList;
	import java.util.Vector;

	import javax.sound.sampled.AudioInputStream;
	import javax.sound.sampled.AudioSystem;
	import javax.sound.sampled.Clip;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.Timer;

	import java.awt.image.*;
	import java.awt.geom.AffineTransform;

	import javax.sound.midi.InvalidMidiDataException;
	import javax.sound.midi.MidiSystem;
	import javax.sound.midi.MidiUnavailableException;
	import javax.sound.midi.Sequence;
	import javax.sound.midi.Sequencer;

	public class Driver extends JPanel implements ActionListener, KeyListener,
			MouseListener, MouseMotionListener {
		public static int life = 3;

		int screen_width = 1400;
		int screen_height = 800;
		int enemycount = 0;
		int wave = 0;
		int countLives = 5;
		
		//starter game objects
		String src = new File("").getAbsolutePath() + "/src/"; // path to image
		Clip hop;
		Ship s = new Ship("shrek gif final final.gif", 100, 475);
		
		Background b = new Background("background1.jpg", 0,0);
		Background b2 = new Background("background1.jpg", 1400, 0);
		Background start = new Background("start screen.jpg", 0, 0);
		Background died = new Background("unnamed.jpg", 0, 0);
		Background end = new Background("end screen.png", 0, 0);
		
		Enemy[] citizens = new Enemy[15];
		Enemy[] knights = new Enemy[15];
		Enemy[] fire = new Enemy[15];
		Enemy dragon = new Enemy("dragon.gif", 1180, 450, 0);
		Enemy donkey = new Enemy("donkey2.gif", 1480, 500, 0);
	
		Extra[] lives = new Extra[5];
		Extra[] deaths = new Extra[45];
		
		// clip.open(audioInputStream);s
		
		Sequencer sequencer;
		// Background bg;
		int my_variable = 0; // example

		String lost = "";

		//example use of new class derived from the Ship class
	
		public void paint(Graphics g) {

			super.paintComponent(g);

//			g.drawString(("my_variable:") + Integer.toString(my_variable), 0,870);
//			g.setFont(font2);
//			g.setColor(Color.CYAN);
			
			
			b.paint(g);
			b2.paint(g);
			s.paint(g);
			donkey.paint(g);
			
			for(int i = 0; i < lives.length; i++) {
				lives[i].paint(g);
			}
			
			start.paint(g);
			
			
			for(int i = 0; i < citizens.length; i++) {
					citizens[i].paint(g);	
			}
			
			for(int i = 0; i < knights.length; i++) {
					knights[i].paint(g);
			}
			
			for(int i = 0; i < fire.length; i++) {
					fire[i].paint(g);
			}
			
			
			for(int i = 0; i < deaths.length; i++) {
				deaths[i].paint(g);
			}
			
			
			if(wave == 2) {
				dragon.paint(g);
			}
			
			if(countLives==0 || s.collided(donkey)) {
				died.paint(g);
			}
			
			if(enemycount >= 15 || s.collided(dragon)) {
				end.paint(g);
			}
			// g.drawString(life+"", 400, 400);
		}

		Font font = new Font("Courier New", 1, 50);
		Font font2 = new Font("Courier New", 1, 30);

		
		//
	
		public void update() {
			
			s.getX();
			
			if(s.getX()<=0){
				s.setX(0);
			}
			if(s.getX()>=1400-200){
				s.setX(1400-200);
			}
			if(s.getY()<=0){
				s.setY(0);
			}
			if(s.getY()>=775-200){
				s.setY(775-175);
			}
			
			b.move(-3);
			b2.move(-3);
			
			if(b.getBX()+1400<=0){
				
				b.setBX(1395); 	
			}
			if(b2.getBX()+1400<=0){
				b2.setBX(1395);
			}
			

			for(int i = 0; i < citizens.length-1; i++) {
				citizens[i].setX(citizens[i].getX()+citizens[i].getVx());
			
				if(s.collided(citizens[i])) {
					//System.out.println("ow");
					deaths[i].setX(citizens[i].getX());
					citizens[i].setX(-500);
					citizens[i].setY(0);
					enemycount++;	
					//System.out.println(enemycount);
					} else if(citizens[i].getX() ==300) {
					citizens[i].setX(-500);
					citizens[i].setY(0);
					lives[countLives-1].setX(-200);
					countLives--;
					//System.out.println(enemycount);
				}
				
			}
			
			for(int i = 0; i < knights.length; i++) {
				knights[i].setX(knights[i].getX()+knights[i].getVx());
				
				if(s.collided(knights[i])) {
					//System.out.println("ow");
					deaths[i].setX(knights[i].getX());
					knights[i].setX(-500);
					knights[i].setY(0);
					enemycount++;
				}else if(knights[i].getX() == 380) {
					knights[i].setX(-500);
					knights[i].setY(0);
					lives[countLives-1].setX(-200);
					countLives--;
					System.out.println(enemycount);
				}
			
			}
			
			for(int i = 0; i < fire.length; i++) {
				fire[i].setX(fire[i].getX()+fire[i].getVx());
				
				if(s.collided(fire[i])) {
					//System.out.println("ow");
				//	deaths[i].setX(fire[i].getX());
					fire[i].setX(-500);
					fire[i].setY(0);
					enemycount++;
					//System.out.println(enemycount);
				}else if(fire[i].getX() == 500) {
					fire[i].setX(-500);
					fire[i].setY(0);
					lives[countLives-1].setX(-200);
					countLives--;
				}
			}
			
			if(enemycount >= 5+(wave*5)) {
				b.setImage("background" + (wave+2) + ".jpg");
				b2.setImage("background" + (wave+2) + ".jpg");
				if(wave == 0) {
					for(int j = 0; j < citizens.length; j++) {
						citizens[j].nextLevel();
						knights[j].setX(1400+(j*800));
						knights[j].setVx(-5);
						for(int l = 0; l < enemycount; l++) {
							deaths[l].setX(-300);
						}
					}
				} else if (wave == 1) {
					for(int k = 0; k < knights.length; k++){
						knights[k].nextLevel();
						fire[k].setX(1150+(k*1150));	
						fire[k].setVx(-6);
						for(int l = 0; l < enemycount; l++) {
							deaths[l].setX(-300);
						}
						
					}
					
				}
				wave++;
			}
			
			if(countLives == 1) {
				donkey.setVx(-10);
				donkey.setX(donkey.getX()+donkey.getVx());
				
			}
			if(donkey.getX() == 530) {
				donkey.setX(-500);
				donkey.setY(0);	
				lives[countLives].setX(475+(countLives*75));
				countLives++;
			}
			
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			update();
			repaint();
		}

		public static void main(String[] arg) {
			Driver d = new Driver();
		}

		public Driver() {

			JFrame f = new JFrame();
			f.setTitle("Shrek");
			f.setSize(screen_width, screen_height);
			f.setResizable(false);
			f.addKeyListener(this);
			f.addMouseListener(this);
			f.addMouseMotionListener(this);
			
			
			for(int i = 0; i < citizens.length; i++) {
				citizens[i] = new Enemy("person1.gif", 1400+(i*700), 500, 0);
			}
			
			for(int i = 0; i < knights.length; i++) {
				knights[i] = new Enemy("knight.gif", 4000+(i*700), 500, 0);
			}
		
			for(int i = 0; i < fire.length; i++) {
				fire[i] = new Enemy("fireball.gif", 8000+(i*300), 540, 0);
			}
			
			for(int i = 0; i < lives.length; i++) {
				lives[i] = new Extra("health.png", 475+(i*75), 0);
			}
			
			for(int i = 0; i < deaths.length; i++) {
				deaths[i] = new Extra("skull.gif", 7000, 500);
			}
			
			// Obtains the default Sequencer connected to a default device.
			try {
				sequencer = MidiSystem.getSequencer();
				// Opens the device, indicating that it should now acquire any
				// system resources it requires and become operational.
				sequencer.open();

				// create a stream from a file
				
//				InputStream is = new BufferedInputStream(new FileInputStream(
//						new File("Thelazysong.mid").getAbsoluteFile()));

				// Sets the current sequence on which the sequencer operates.
				// The stream must point to MIDI file data.
//				sequencer.setSequence(is);

				// Starts playback of the MIDI data in the currently loaded
				// sequence.
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// player.addMouseListener(this);
			// bg = new Background("background.png");
			// do not add to frame, call paint on
			// these objects in paint method

			f.add(this);
			t = new Timer(16, this);
			t.start();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
		}

		Timer t;
		

		@Override
		public void keyPressed(KeyEvent e) {
			//System.out.println(e.getKeyCode());
			if(e.getKeyCode()== 32) {
				start.setBX(-1500);
				for(int i = 0; i < citizens.length; i++) {
					citizens[i].setVx(-5);
				}
			}
			
			if(e.getKeyCode() == 82) {
				reset();
				died.setBX(-1500);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			//System.out.println(e.getKeyCode());

		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		public void reset() {
			enemycount = 0;
			wave = 0;
			countLives = 5;
			b.setImage("background1.jpg");
			b2.setImage("background1.jpg");
			for(int i = 0; i < citizens.length; i++) {
				citizens[i].setX(1400+(i*700));
				citizens[i].setY(500);
				citizens[i].setVx(-4);
				knights[i].setX(4000+(i*700));
				knights[i].setY(500);
				knights[i].setVx(0);
				fire[i].setX(8000+(i*300));
				fire[i].setY(540);
				fire[i].setVx(0);
				
			}
			
			for(int j = 0; j < deaths.length; j++) {
				deaths[j].setX(7000);
			}
			
			for(int k = 0; k < lives.length; k++) {
				lives[k].setX(475+(k*75));
			}
		}
		
		boolean on = false;
		@Override
		public void mousePressed(MouseEvent e) {
			
			//shoot a projectile on mouse press
			//invoke the fire() method that we wrote
			s.fire();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
			
			
		}

	}


