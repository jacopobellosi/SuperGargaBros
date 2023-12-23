/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/


import java.util.*;
import java.time.*;

//Rappresenta un singolo livello del gioco, definendo la sua struttura, i personaggi, gli oggetti, ecc.
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GameEngine extends JPanel implements Runnable{
 
	final int originalTitleSize = 16; // 16x16 title
	final int scale=3;
	public final int titleSize = originalTitleSize * scale;
	
	
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = titleSize * maxScreenCol;
	final int screenHeight = titleSize * maxScreenRow;
	int FPS=60;
	InputManager keyH = new InputManager();

	Thread gameThread;
	Player player =new Player(this,keyH);
	int playerX =100;
	int playerY =100;
	int playerSpeed =4;
	
	
	public GameEngine() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}


	
	public void StartGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		double drawInterval = 1000000000/FPS;
		double nextDrawTine = System.nanoTime();
		while(gameThread != null) {
			//System.out.print("ciao");
			//long currentTime = System.nanoTime();
			 
			//UPDATE
			update();
			//DRAW
			repaint();
			try {
				double remainingTime = nextDrawTine - System.nanoTime();
				remainingTime = remainingTime/1000000;
				if(remainingTime<0) {
					remainingTime =0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTine += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update() {
		player.update();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		player.draw(g2);
		g2.dispose();
		
	}
}