package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

 public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	boolean drawPath = false;
	ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();
	ArrayList<String> projectilePass = new ArrayList<>();
	
	

	//public String currentMap = "/map/worldmap.txt";
	public TileManager(GamePanel gp) {
		this.gp = gp;
		String initialTileData = "/map/tiledata3.txt";
		//Read Tile Data file
		InputStream is = getClass().getResourceAsStream(initialTileData);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		//Getting Tile Name and Collision INFO From the file  
		String line; 
		try {
			while((line = br.readLine()) != null) {
				fileNames.add(line);
				collisionStatus.add(br.readLine());
				if(initialTileData.equals("/map/tiledata3.txt")) {
				projectilePass.add(br.readLine());
				}else {
					projectilePass.add(null);
				}
				
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tile = new Tile[fileNames.size()];
		getTileImage();
		
		is = getClass().getResourceAsStream("/map/worldmap.txt");
		br = new BufferedReader(new InputStreamReader(is));
		
		try {
			String line2 = br.readLine();
			String maxTile[] = line2.split(" ");
			
			gp.maxWorldCol = maxTile.length;
			gp.maxWorldRow = maxTile.length;
			mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
			
			br.close();
			
		}catch(IOException e) {
			System.out.println("Exception!");
		}
		
		loadMap("/map/worldmap.txt",0);  
		loadMap("/map/indoor01.txt",1);
		loadMap("/map/dungeon01.txt",2);
		loadMap("/map/dungeon02.txt",3);
	}
	public void setTile(int num, boolean pass) {
		tile[num].collision = pass;
	}
	
	public void getTileImage() {
		
		for (int i = 0; i < fileNames.size(); i++){
			String fileName; 
			boolean collision;
			boolean projectileCollision;
			
			// get file name
			fileName = fileNames.get(i);
			
			
			//get collision status
			if(collisionStatus.get(i).equals("true")) {
				collision = true;
			} else {
				collision = false;
			}
			
			
			if(!projectilePass.get(i).equals(null) && projectilePass.get(i).equals("true")) {
				projectileCollision = true;
			} else {
				projectileCollision = false;
			}
			
			
			//System.out.println(i + " " + "  " + fileName + "  " +  collision);
			setup(i,fileName, collision, projectileCollision);
		}
	}
	
	public void setup(int index, String imageName, boolean collision, boolean projectileCollision ) {
		UtilityTool uTool = new UtilityTool();
	
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
			tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			tile[index].projectileCollision = projectileCollision;
			//System.out.println(index + ": "+imageName+".png");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void loadMap(String filePath, int map) {
		
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
						
				String line = br.readLine();
				//System.out.println(line);
						
				while(col < gp.maxWorldCol) {
						
					String numbers[] = line.split(" ");
					for (int i = 0; i < numbers.length; i++) {
					//System.out.print(numbers[i] + ": ");
					}
					int num = Integer.parseInt(numbers[col]);
					//System.out.println(col + ", " +  row + ", " + num);
					mapTileNum[map][col][row] = num;
					col++;
							
							
						}
						if(col == gp.maxWorldCol) {
							col = 0; 
							row++;
						}
						
						
					}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
	
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol *gp.tileSize;
			int worldY = worldRow *gp.tileSize;
			int ScreenX = worldX - gp.player.worldX + gp.player.screenX;
			int ScreenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX > gp.player.worldX-gp.player.screenX - gp.tileSize&& 
			   worldX < gp.player.worldX+gp.player.screenX + gp.tileSize&&
			   worldY > gp.player.worldY-gp.player.screenY - gp.tileSize&& 
			   worldY < gp.player.worldY+gp.player.screenY + gp.tileSize ){
				g2.drawImage(tile[tileNum].image, ScreenX, ScreenY, null);
				
			}
				worldCol++;
				
				if(worldCol == gp.maxWorldCol) {
					worldCol = 0;
					
					worldRow++;
					
				
			}
		}
		
		if(drawPath) {
			g2.setColor(new Color(255,0,0,70));
			for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
				int worldX = gp.pFinder.pathList.get(i).col *gp.tileSize;
				int worldY = gp.pFinder.pathList.get(i).row *gp.tileSize;
				int ScreenX = worldX - gp.player.worldX + gp.player.screenX;
				int ScreenY = worldY - gp.player.worldY + gp.player.screenY;
				
				g2.fillRect(ScreenX, ScreenY, gp.tileSize, gp.tileSize);
			}
		}
	}
}
