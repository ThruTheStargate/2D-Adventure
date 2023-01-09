package main;

import entity.NPCMerchant;
import entity.NPCOldMAn;
import monster.MonGreenSlime;
import monster.MonPinkSlime;
import monster.MonRedSlime;
import object.ObjAxe;
import object.ObjChest;
import object.ObjCoinsBronze;
import object.ObjDoor;
import object.ObjHeart;
import object.ObjKey;
import object.ObjLantern;
import object.ObjManaCrystal;
import object.ObjPotionRed;
import object.ObjShieldBlue;
import tileInteractive.ITDrayTree;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void setObject() {
	
		int mapNum = 0;
		int i = 0;
		gp.obj[mapNum][i] = new ObjCoinsBronze(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		
		//gp.obj[mapNum][i] = new ObjLantern(gp);
		//gp.obj[mapNum][i].worldX = gp.tileSize * 23;
//		gp.obj[mapNum][i].worldY = gp.tileSize * 23;
//		i++;
		
		gp.obj[mapNum][i] = new ObjCoinsBronze(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 23;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		gp.obj[mapNum][i] = new ObjDoor(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 14;
		gp.obj[mapNum][i].worldY = gp.tileSize * 29;
		i++;
		gp.obj[mapNum][i] = new ObjPotionRed(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 23;
		i++;
		gp.obj[mapNum][i] = new ObjPotionRed(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 24;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		gp.obj[mapNum][i] = new ObjPotionRed(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 23;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		gp.obj[mapNum][i] = new ObjDoor(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 12;
		gp.obj[mapNum][i].worldY = gp.tileSize * 13;
		i++;
		gp.obj[mapNum][i] = new ObjHeart(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 36;
		gp.obj[mapNum][i].worldY = gp.tileSize * 44;
		i++;
		gp.obj[mapNum][i] = new ObjPotionRed(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 28;
		i++;
		gp.obj[mapNum][i] = new ObjChest(gp, new ObjKey(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 32;
		gp.obj[mapNum][i].worldY = gp.tileSize * 32;
//		i++;
//		gp.obj[mapNum][i] = new ObjHeart(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 23;
//		gp.obj[mapNum][i].worldY = gp.tileSize * 27;
//		i++;
//		gp.obj[mapNum][i] = new ObjManaCrystal(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
//		gp.obj[mapNum][i].worldY = gp.tileSize * 26;
	}
	public void setNPC() {
		
		// map 0
		int mapNum = 0;
		int i = 0 ;
		gp.npc[mapNum][i] = new NPCOldMAn(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*21;
		gp.npc[mapNum][i].worldY = gp.tileSize*21;
		
		
		// map 1
		mapNum = 1;
		i = 0;
		gp.npc[mapNum][i] = new NPCMerchant(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*12;
		gp.npc[mapNum][i].worldY = gp.tileSize*7;
		
	}
	public void setMonster() {
		int mapNum = 0;
		int i = 0;
		
		gp.monster[mapNum][i] = new MonGreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*40;
		
		i++;
		
		gp.monster[mapNum][i] = new MonGreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*39;
		gp.monster[mapNum][i].worldY = gp.tileSize*10;	

		i++;
		
		gp.monster[mapNum][i] = new MonGreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*39;
		gp.monster[mapNum][i].worldY = gp.tileSize*11;	
		
		i++;
		
		gp.monster[mapNum][i] = new MonGreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*40;
		gp.monster[mapNum][i].worldY = gp.tileSize*10;	
		
		i++;
		if(gp.player.level < 3) {
			gp.monster[mapNum][i] = new MonGreenSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*12;
			gp.monster[mapNum][i].worldY = gp.tileSize*31;
	
			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*10;
			gp.monster[mapNum][i].worldY = gp.tileSize*55;
	
			i++;
			
			gp.monster[mapNum][i] = new MonGreenSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*23;
			gp.monster[mapNum][i].worldY = gp.tileSize*39;
	
			i++;
			
			gp.monster[mapNum][i] = new MonGreenSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*38;
			gp.monster[mapNum][i].worldY = gp.tileSize*10;
	
			i++;
			
			gp.monster[mapNum][i] = new MonGreenSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*39;
			gp.monster[mapNum][i].worldY = gp.tileSize*10;
	
			i++;
			
			gp.monster[mapNum][i] = new MonGreenSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*37;
			gp.monster[mapNum][i].worldY = gp.tileSize*10;
	
			i++;
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*10;
			gp.monster[mapNum][i].worldY = gp.tileSize*57;
	
			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*40;
			gp.monster[mapNum][i].worldY = gp.tileSize*12;
			
		} else if (gp.player.level < 6) {
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*10;
			gp.monster[mapNum][i].worldY = gp.tileSize*39;

			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*10;
			gp.monster[mapNum][i].worldY = gp.tileSize*55;

			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*23;
			gp.monster[mapNum][i].worldY = gp.tileSize*39;

			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*38;
			gp.monster[mapNum][i].worldY = gp.tileSize*10;

			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*10;
			gp.monster[mapNum][i].worldY = gp.tileSize*57;

			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*40;
			gp.monster[mapNum][i].worldY = gp.tileSize*12;
		} else {
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*10;
			gp.monster[mapNum][i].worldY = gp.tileSize*11;

			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*10;
			gp.monster[mapNum][i].worldY = gp.tileSize*55;

			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*23;
			gp.monster[mapNum][i].worldY = gp.tileSize*39;

			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*38;
			gp.monster[mapNum][i].worldY = gp.tileSize*10;

			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*10;
			gp.monster[mapNum][i].worldY = gp.tileSize*57;

			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*40;
			gp.monster[mapNum][i].worldY = gp.tileSize*12;
		}
		
//		mapNum = 1;
//		
//		gp.monster[mapNum][i] = new MonPinkSlime(gp);
//		gp.monster[mapNum][i].worldX = gp.tileSize*40;
//		gp.monster[mapNum][i].worldY = gp.tileSize*12;
//		
	}
	
	public void setInteractiveTiles() {
		
		int mapNum = 0;
		
		for(int i = 0; i < 34; i++) {
			if(i < 10) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, (i + 24), 13);
			}
			else if(i < 14) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, (i + 12), 25);
			}
			else if(i < 18) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, (i - 4), 42);
			}
			else if(i < 23) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, (i - 4), 41);
			}
			else if(i < 28) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, 19, (42 - (i-23)));
			}
			else if(i < 30) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, 20, (37 + (i-28)));
			}
		}
		gp.iTile[mapNum][13] = new ITDrayTree(gp, 10, 41);
		gp.iTile[mapNum][13] = new ITDrayTree(gp, 13, 41);
		gp.iTile[mapNum][13] = new ITDrayTree(gp, 21, 37);
		

		
	}
}
