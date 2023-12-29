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
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameEngine extends JPanel implements Runnable{
 
	final int originalTitleSize = 16; // 16x16 title
	final int scale=3;
	public final int titleSize = originalTitleSize * scale;
	
	public boolean END = false;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 13;
	public final int screenWidth = titleSize * maxScreenCol;
	public final int screenHeight = titleSize * maxScreenRow;
	int FPS=60;
	InputManager keyH = new InputManager(this);
	public UI ui = new UI(this);
	Tilemanger tileM = new Tilemanger(this,"/pacman/mappa/mappa01.txt");
	public EventHandler eHandler=new EventHandler(this);
	public CollisionChecker cCheck = new CollisionChecker(this);
	public AssetSetter aSetter=new AssetSetter(this,"/pacman/mappa/mappa01.txt");
	Player player =new Player(this,keyH);
	public GameObject obj[]=new GameObject[10000];//numero massimo oggetti
	public GameObject pw[]=new GameObject[1000];
	public Entity[] ghost = new Entity[7];
	public Level livello = new Level();
	Sound sound=new Sound(); 
	Thread gameThread;

	//game state
	int livelloCorrente=1;
	int livelloMax=2;
	public int gameState;
	public final int titleState=0;
	public final int playState=1;
	public final int pauseState=2;
	public final int endState=3;
	public final int nextLevelState=4;
	private Timer invicibilityTimer;
	public gestoreRipristinoImmunita GRI;
	 
	 
	public GameEngine() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		
		
		
	}


	public void setupGame() {
		aSetter.setMonster();
		aSetter.setObject();
		player.setDefaultLife();
		gameState=titleState;
	}
	
	public void StartGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void StopGameThread() {
		gameThread.interrupt();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		double drawInterval = 1000000000/FPS;
		double nextDrawTine = System.nanoTime();
		while(gameThread != null ) {
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
		if(gameState==playState) {	    	
			player.update();
			for(int i=0;i<ghost.length;i++) {
				if(ghost[i]!=null) {
					ghost[i].update();
				}
			}
			
		}else if(gameState==pauseState) {
	    	

		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		if(gameState==titleState) {
			ui.draw(g2);
		}else if(gameState==playState){
			tileM.draw(g2);
			for(int i=0;i< obj.length;i++)		{
				if(obj[i]!=null) {
					obj[i].drawCFU(g2,this);
				}
			}
			
			for(int i=0;i< pw.length;i++)		{
				if(pw[i]!=null) {
					pw[i].drawPW(g2,this);
				}
			}
			player.draw(g2);
			if(player.attacking==true) {
				for(int i=0;i<ghost.length;i++) {
					if(ghost[i]!=null && ghost[i].invincible==false) {
						ghost[i].drawFantasmaVulnerabile(g2);
						ghost[i].setAction();
					}
				}
				for(int i=0;i<ghost.length;i++) {
					if(ghost[i]!=null && ghost[i].invincible==true) {
						ghost[i].setAction();
						ghost[i].draw(g2);
					}
				}
			}else {
				for(int i=0;i<ghost.length;i++) {
					if(ghost[i]!=null) {
						ghost[i].setAction();
						ghost[i].draw(g2);
					}
				}
			}
				
			
				
				
			
			//UI
			ui.drawContaPallini(g2);
			g2.dispose();
		}else if(gameState==endState) {
			ui.draw(g2);

		}else if(gameState==pauseState) {
			ui.draw(g2);
			
		}else if(gameState==nextLevelState) {
			ui.draw(g2);
		}
	}
	
	//music method
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
		
	}
	public void stopMusic(int i) {
		sound.setFile(i);
		sound.stop();		
	}
	/*
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}*/
	
	
	public void killMonster(int i) {
		ghost[i]=null;
		System.out.println("HAI MANGIATO UN FANSTASMA");
		 
	}
	public void spawnMonster(int tipo) {
		boolean flag=false;
		for(int i=0;i<ghost.length && flag==false;i++) {
			if(ghost[i] ==null) {
				ghost[i] = new Ghost(this,tipo);
				ghost[i].x = titleSize *(7+(tipo));
				ghost[i].y = titleSize *4;
				ghost[i].invincible = true;
				GRI = new gestoreRipristinoImmunita(this,i);
				GRI.start();
				//int ultimoFantasmaEliminato = numeroFantasmiEliminati.remove(0);

			}
			
		}
		
	}



	public void restart() {
		livelloCorrente=1;
		tileM = new Tilemanger(this,"/pacman/mappa/mappa0"+livelloCorrente+".txt");
		aSetter=new AssetSetter(this,"/pacman/mappa/mappa0"+livelloCorrente+".txt");
		player.setDefaultValue();
		player.setDefaultLife();
		aSetter.setMonster();
		aSetter.setObject();
		
	}
	public void nextLevel() {
		livelloCorrente++;
		Tilemanger.resetPalliniTotali();
		tileM = new Tilemanger(this,"/pacman/mappa/mappa0"+livelloCorrente+".txt");
		aSetter=new AssetSetter(this,"/pacman/mappa/mappa0"+livelloCorrente+".txt");
		player.setDefaultValue();
		player.setDefaultLife();
		aSetter.setMonster();
		aSetter.setObject();
		player.pallini_totali = Tilemanger.getPalliniTotali();
		
	}


}