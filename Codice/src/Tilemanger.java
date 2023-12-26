import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class Tilemanger {
	GameEngine gp;
	Obstacole[] tile;
	public BufferedImage imagecfu ;
	public int mapTilenum[][];
	static int conteggio;
	public  Tilemanger(GameEngine gp) {
		this.gp = gp;
		tile= new Obstacole[10];
		mapTilenum = new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		conteggio=0;
		loadMap("/pacman/mappa/mappa01.txt");
	}
	
	public void getTileImage() {
		
		try {
			tile[0] = new Obstacole();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/pacman/tile/sfondo.jpg"));
			
			tile[1] = new Obstacole();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/pacman/border/bordo_ver.png"));
			tile[1].collision = true;
			
			tile[2] = new Obstacole();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/pacman/border/bordo_orz.png"));
			tile[2].collision = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadMap(String S) {
		try {
			InputStream is = getClass().getResourceAsStream(S);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col =0;
			int row=0;
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
					String line = br.readLine();
					while(col < gp.maxScreenCol) {
						
						String numbers[] = line.split(" ");
						int num = Integer.parseInt(numbers[col]);
						mapTilenum[col][row] = num;
						if(mapTilenum[col][row]==0) {
							//g2.drawImage(imagecfu, x, y, gp.titleSize, gp.titleSize, null);
							conteggio++;
						}
						col++;
					}
					if(col == gp.maxScreenCol) {
						col =0;
						row++;
					}
				
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static int getPalliniTotali() {
		
		return conteggio;
	}
	public void draw(Graphics g2) {

		int col = 0;
		int row = 0;
		int x =0;
		int y=0;

		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			int tileNum = mapTilenum[col][row];
			g2.drawImage(tile[tileNum].image, x, y, gp.titleSize, gp.titleSize, null);
			
			
			
			col++;
			x +=gp.titleSize;
			
			if(col==gp.maxScreenCol){
				col =0;
				x=0;
				row++;
				y +=gp.titleSize;
				
			}
		}
		
	}
	
}