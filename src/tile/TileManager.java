package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

 public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	boolean drawPath = false;
	//public String currentMap = "/map/world01b.txt";
	//public String currentMap = "/map/interior01.txt";
	//public String currentMap = "/map/worldV2.txt";
	public String currentMap = "/map/worldV3.txt";
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[100];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		
		loadMap(currentMap, 0);
		loadMap("/map/interior01.txt",1);
	}
	public void setTile(int num, boolean pass) {
		tile[num].collision = pass;
	}
	
	public void getTileImage() {
		
		setup(0, "grass", false);
		setup(1, "wall", true);
		setup(2, "water", true);
		setup(3, "earth", false);
		setup(4, "tree", true);
		setup(5, "sand", false);
		setup(6, "water01", true);
		setup(7, "tree", false);
		setup(8, "road00", false);
		setup(9, "water00", true);	
		
//			setup(0, "grass00", false);
//			setup(1, "grass00", false);
//			setup(2, "grass00", false);
//			setup(3, "grass00", false);
//			setup(4, "grass00", false);
//			setup(5, "grass00", false);
//			setup(6, "grass00", false);
//			setup(7, "grass00", false);
//			setup(8, "grass00", false);
//			setup(9, "grass00", false);	
		
			setup(10,"grass00",false);
			setup(11,"grass01", false);
			setup(12,"water00", true);
			setup(13,"water01", true);
			setup(14,"water02", true);
			setup(15,"water03", true);
			setup(16,"water04", true);
			setup(17,"water05", true);
			setup(18,"water06", true);
			setup(19,"water07", true);
			setup(20,"water08", true);
			setup(21,"water09", true);
			setup(22,"water10", true);
			setup(23,"water11", true);
			setup(24,"water12", true);
			setup(25,"water13", true);
			
			setup(26,"road00", false);
			setup(27,"road01", false);
			setup(28,"road02", false);
			setup(29,"road03", false);
			setup(30,"road04", false);
			setup(31,"road05", false);
			setup(32,"road06", false);
			setup(33,"road07", false);
			setup(34,"road08", false);
			setup(35,"road09", false);
			setup(36,"road10", false);
			setup(37,"road11", false);
			setup(38,"road12", false);
			
			
//			for(int i = 0;i < 14; i++) {
//				if(i<10) {
//				setup(i+12,"water0" + i,true);
//				} else {
//					setup(i+12,"water" + i,true);
//				}
//				
//			}
			
//			for(int i = 0;i < 13; i++) {
//				if(i<10) {
//				setup(i+24,"road0" + i,false);
//				} else {
//					setup(i+24,"road" + i,false);
//				}
//			}
//			setup(37, "grass", false);
//			setup(38, "road12", true);
			setup(39, "earth", false);
			setup(40, "wall", true);
			setup(41, "tree", true);
			setup(42, "hut", false);
			setup(43, "floor01", false);
			setup(44, "table01", true);
			//setup(45, "bridge2", false);
			setup(45,"floor01",false);
			setup(46,"floor01",false);
			setup(47, "fairyRing01", false);
			
	}
	
	public void setup(int index, String imageName, boolean collision ) {
		UtilityTool uTool = new UtilityTool();
	
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
			tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
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
