  package object;

import java.util.Random;

//importing classes from other folders
import entity.Entity;
import main.GamePanel;

//the class itself
public class ObjChest extends Entity{  //extends = using parts of the Entity class
	//intitializing variables
	GamePanel gp;
	Entity loot;
	boolean opened = false;

	public ObjChest(GamePanel gp, Entity loot) {
		super(gp);
		this.gp = gp;
		this.loot = loot;
		type = type_obstacle; //types are defined in entity
		name = "Chest";
		
		
		image = setup("/objects/chest", gp.tileSize, gp.tileSize);
		// add and open chest image
				//image2 = setup("/objects/chest_open_1", gp.tileSize, gp.tileSize);
		
		image2 = setup("/objects/chest", gp.tileSize, gp.tileSize);
		down1 = image;
		collision = true;
		
		//The dimensions of the hitbox
		solidArea.x = 4;
		solidArea.y = 16;
		solidArea.width = 40;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
//		
//		down1 = setup("/objects/chest", gp.tileSize, gp.tileSize);
//		description = "(" + name + ")"; 	
	}
	
	
	public void interact() {
		
		gp.gameState = gp.dialogueState;
		if(!opened) {
			gp.playSE(3);
			StringBuilder sb = new StringBuilder();
			sb.append("You open the chest and find a " + loot.name + "!" );
			if(!gp.player.canObtainItem(loot)) {
				sb.append("\n...But you cannot carry any more!");
			}
			else {
				sb.append("\n You obtained the " + loot.name + "!");
				//gp.player.inventory.add(loot);
				down1 = image2;
				opened = true;
			}
			gp.ui.currentDialogue = sb.toString();
		} 
		else {
			gp.ui.currentDialogue = " It's Empty";
		}
	}
	
//	write this function about random loot latter
	
//	public Entity getLoot() {
//		
//		gp.playSE(1);
//		Random random = new Random();
//		int i = random.nextInt(100) + 1;
//	    value = i;
//		//gp.ui.addMessage("You Found " + value  + "bronze coins In the Chest.  Coin: " + value  );
//		//gp.player.coin += value;
//		
//	}
}
