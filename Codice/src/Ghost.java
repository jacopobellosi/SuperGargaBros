/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/


import java.util.*;

import javax.imageio.ImageIO;

import java.io.IOException;

//fantasmini
public class Ghost extends Entity {
		GameEngine gp;
		int oppMinLastNumber =0;
		int oppMaxLastNumber =100;
		static  Player targetPlayer;
		public Ghost(GameEngine gp, int i) {
			super(gp);
			//
			this.gp=gp;
			name="fanstasma";
			direction="down";
			speed=2;
			type=i;
			solidArea.x=3;
			solidArea.y=7;
			solidArea.height=42;
			solidArea.width=30;
			solidAreaDefaultx = solidArea.x;
			solidAreaDefaulty = solidArea.y;
			
			getImage(i);
			fantasmaVulnerabile();
		}
		public static void setTarget(Player pacman) {
			targetPlayer = pacman;
		}
		public void getImage(int immagine) {
			if(immagine==1) {
				try {
					imageGhost = ImageIO.read(getClass().getResourceAsStream("/pacman/fanstasmi/ROSSO.gif"));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(immagine==2) {
				try {
					imageGhost = ImageIO.read(getClass().getResourceAsStream("/pacman/fanstasmi/TURCHE.gif"));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(immagine==3) {
				try {
					imageGhost = ImageIO.read(getClass().getResourceAsStream("/pacman/fanstasmi/SALMONE.gif"));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(immagine==4) {
				try {
					imageGhost = ImageIO.read(getClass().getResourceAsStream("/pacman/fanstasmi/ROSA.gif"));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		public void fantasmaVulnerabile() {
			try {
				imageFantasma_vunerabile = ImageIO.read(getClass().getResourceAsStream("/pacman/fanstasmi/fantasma_vulnerabile.gif"));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		public int getType() {
			return type;
		}
		public void randomMovement() {
			actionlockCounter++;
			
			if (actionlockCounter == 50) {
			     Random random = new Random();
			     int i = ((Random) random).nextInt(100)+1;
			     
			     while(i<oppMaxLastNumber && i>oppMinLastNumber) {
			    	 i = ((Random) random).nextInt(100)+1;
			     }
			     if(i <= 33 ){
			        direction = "up";
			        oppMaxLastNumber = 50;
			        oppMinLastNumber =25;
			     }
			     if (i > 33 && i <= 66) {
			        direction ="left";
			        oppMaxLastNumber = 100;
			        oppMinLastNumber =66;
			     }
			     if (i > 66 && i <= 100) {
			         direction = "right";
			         oppMaxLastNumber = 66;
				        oppMinLastNumber =33;
			     }
			     
			     actionlockCounter = 0;

			 }
		}
		public void setAction() {
			/*
			actionlockCounter++;
			
			if (actionlockCounter == 50) {
			     Random random = new Random();
			     int i = ((Random) random).nextInt(100)+1;
			     
			     while(i<oppMaxLastNumber && i>oppMinLastNumber) {
			    	 i = ((Random) random).nextInt(100)+1;
			     }
			     if(i <= 25 ){
			        direction = "up";
			        oppMaxLastNumber = 50;
			        oppMinLastNumber =25;
			     }
			     if (i > 25 && i <= 50) {
			         direction = "down";
			         oppMaxLastNumber = 25;
			         oppMinLastNumber =0;
			     }
			     if (i > 50 && i <= 75) {
			        direction ="left";
			        oppMaxLastNumber = 100;
			        oppMinLastNumber =75;
			     }
			     if (i > 75 && i <= 100) {
			         direction = "right";
			         oppMaxLastNumber = 75;
				        oppMinLastNumber =50;
			     }
			     
			     actionlockCounter = 0;

			 }
			*/
			/*
			if(gp.player != null) {
				int diffX = gp.player.x - x;
		        int diffY = gp.player.y - y;

		        // Determina la direzione in base alle differenze
		        if (Math.abs(diffX) > Math.abs(diffY)) {
		            // Spostati lungo l'asse x
		            direction = (diffX > 0) ? "right" : "left";

		            // Aggiungi logica per evitare pareti
		            if (gp.cCheck.checkCollisionWithWalls(this, direction)) {
		                // Se la prossima mossa colpisce una parete, prova a muoverti lungo l'asse y
		                direction = (diffY > 0) ? "down" : "up";
		            }
		        } else {
		            // Spostati lungo l'asse y
		            direction = (diffY > 0) ? "down" : "up";

		            // Aggiungi logica per evitare pareti
		            if (gp.cCheck.checkCollisionWithWalls(this, direction)) {
		                // Se la prossima mossa colpisce una parete, prova a muoverti lungo l'asse x
		                direction = (diffX > 0) ? "right" : "left";
		            }
		        }
			}
			*/
			/*
			actionlockCounter++;
			
			if (actionlockCounter == 50) {
				direction ="left";
				 actionlockCounter = 0;
			}
			*/
			 if (isInCage() ) {
			        // Aggiungi qui la logica per farli uscire dalla gabbia
			        // Ad esempio, muovili verso il basso fino a quando non escono
				 	randomMovement();
			    } else {
			        // Se sono già fuori dalla gabbia, inseguono il giocatore
			        //chasePlayer();
			    	chasePlayer();
			    }
			    
		}
			

		private void chasePlayer() {
			    // Implementa il comportamento per inseguire il giocatore
			    // Ad esempio, modifica la direzione in base alla posizione del giocatore
			    if (gp.player != null) {
			    	
			    	int diffX = gp.player.x - x;
			        int diffY = gp.player.y - y;
					/*
			        // Determina la direzione in base alle differenze
			        if (Math.abs(diffX) > Math.abs(diffY)) {
			            // Spostati lungo l'asse x
			            direction = (diffX > 0) ? "right" : "left";

			            // Aggiungi logica per evitare pareti
			            if (gp.cCheck.checkCollisionWithWalls(this, direction)) {
			                // Se la prossima mossa colpisce una parete, prova a muoverti lungo l'asse y
			                direction = (diffY > 0) ? "down" : "up";
			            }
			        } else {
			            // Spostati lungo l'asse y
			            direction = (diffY > 0) ? "down" : "up";

			            // Aggiungi logica per evitare pareti
			            if (gp.cCheck.checkCollisionWithWalls(this, direction)) {
			                // Se la prossima mossa colpisce una parete, prova a muoverti lungo l'asse x
			                direction = (diffX > 0) ? "right" : "left";
			            }
			        }
			        */
			    	// Se il fantasmino è allineato verticalmente o orizzontalmente con il giocatore,
			        // mantieni la direzione corrente e non cambiare fino a che non si spostano
			        
			        if (diffX == 0 || diffY == 0) {
			        	//System.out.println("Ghost "+this.type+" in posizione");
			            // Mantieni la direzione corrente
			        	if(diffY==0) {
			        		System.out.println("Ghost "+this.type+" in posizione asse Y");
			        		if(diffX>0) {
				        		direction="right";
				        	}else if(diffX<0){
				        		System.out.println("Ghost "+this.type+" in posizione e vado al bersaglio" + diffX);
				        		direction="left";
				        		if (gp.cCheck.checkCollisionWithWalls(this, direction)) {
				        			System.out.println("Ghost "+this.type+" in posizione ma vado giu");
				        			randomMovement();
				        		}
				        		 
				        	}else {
				        		randomMovement();
				        	}
			        	}else {
			        		if(diffY>0){
				        		direction="up";
				        	}else if(diffY<0){
				        		direction="down";
				        		if (gp.cCheck.checkCollisionWithWalls(this, direction)) {
				        			System.out.println("Ghost "+this.type+" in posizione ma vado giu");
				        			randomMovement();
				        		}
				        	}else {
				        		randomMovement();
				        	}
			        	}
			        	
			        } else {
			            // Determina la direzione in base alle differenze
			            if (Math.abs(diffX) > Math.abs(diffY)) {
			                // Spostati lungo l'asse x
			                direction = (diffX > 0) ? "right" : "left";

			                // Aggiungi logica per evitare pareti
			                if (gp.cCheck.checkCollisionWithWalls(this, direction)) {
			                    // Se la prossima mossa colpisce una parete, prova a muoverti lungo l'asse y
			                    direction = (diffY > 0) ? "down" : "up";
			                }
			            } else {
			                // Spostati lungo l'asse y
			                direction = (diffY > 0) ? "down" : "up";

			                // Aggiungi logica per evitare pareti
			                if (gp.cCheck.checkCollisionWithWalls(this, direction)) {
			                    // Se la prossima mossa colpisce una parete, prova a muoverti lungo l'asse x
			                    direction = (diffX > 0) ? "right" : "left";
			                }
			            }
			        }
			        
			        /*
			        if (diffX == 0 && diffY == 0) {
			            // Mantieni la direzione corrente
			        } else {
			            // Determina la direzione in base alle differenze
			            if (Math.abs(diffX) > Math.abs(diffY)) {
			                // Spostati lungo l'asse x
			                direction = (diffX > 0) ? "right" : "left";

			                // Aggiungi logica per evitare pareti
			                if (gp.cCheck.checkCollisionWithWalls(this, direction)) {
			                    // Se la prossima mossa colpisce una parete, prova a muoverti lungo l'asse y
			                    direction = (diffY > 0) ? "down" : "up";
			                }
			            } else {
			                // Spostati lungo l'asse y
			                direction = (diffY > 0) ? "down" : "up";

			                // Aggiungi logica per evitare pareti
			                if (gp.cCheck.checkCollisionWithWalls(this, direction)) {
			                    // Se la prossima mossa colpisce una parete, prova a muoverti lungo l'asse x
			                    direction = (diffX > 0) ? "right" : "left";
			                }
			            }
			        }
			        */
			    }
			        
			}

		private boolean isInCage() {
				boolean isInCage;
				//System.out.println("Ghost "+this.type+" in posizione y="+y/gp.titleSize+" x="+x/gp.titleSize);
				 if ((y/gp.titleSize ==  3 ||y/gp.titleSize ==  4)  && (x/gp.titleSize >5 && x/gp.titleSize <=13)  ) {
					 	//System.out.println("Ghost "+this.type+" ora usciamo");
			            
			            isInCage= true;
			        } else {
			            // Dopo essere usciti, passa al comportamento di inseguimento del giocatore
			        	isInCage= false;
			        }
				return isInCage;
			    // Aggiungi la logica per determinare se il fantasma è ancora nella gabbia
			    // Restituisci true se è nella gabbia, false altrimenti
		}	
		
}