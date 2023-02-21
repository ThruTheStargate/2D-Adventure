package monster; //file name

import java.util.Random; 
import entity.Entity;        //importing the entity class
import main.GamePanel;       //importing GamePanel class
import object.ObjChest;      //loot classes
import object.ObjCoinsBronze;
import object.ObjHeart;
import object.ObjManaCrystal;
import object.ObjRock;

public class Trojan extends Monster{

	public Trojan(GamePanel gp) {
		super(gp);
		direction = "right";
		speed = 2;
		
		getImage();
	}
    public void getImage(){
        
   	 	up1 = setup("/monsters/Trojan/Trojan_up_01", gp.tileSize, gp.tileSize);
        up2 = setup("/monsters/Trojan/Trojan_up_02", gp.tileSize, gp.tileSize);
        down1 = setup("/monsters/Trojan/Trojan_down_01", gp.tileSize, gp.tileSize);
        down2 = setup("/monsters/Trojan/Trojan_down_02", gp.tileSize, gp.tileSize);
        right1 = setup("/monsters/Trojan/Trojan_right_01", gp.tileSize, gp.tileSize);
        right2 = setup("/monsters/Trojan/Trojan_right_02", gp.tileSize, gp.tileSize);
        left1 = setup("/monsters/Trojan/Trojan_left_01", gp.tileSize, gp.tileSize);
        left2 = setup("/monsters/Trojan/Trojan_left_02", gp.tileSize, gp.tileSize);   
   }

    public void setAction() {
    	
    	if(onPath) {
    		int goalCol = 12;
    		int goalRow = 9;
    		
    		// uncomment the below if you want the NPC to follow the character. 
//    		int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
//    		int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
    		
    		searchPath(goalCol, goalRow);
    	}
    	else {
	    	actionBlockCounter ++;
	    	if(actionBlockCounter == 120 ) {
	    		
	    	
	    	Random random = new Random();
	    	int i = random.nextInt(100) + 1; // picks a number from 1-100
	    	if(i <= 25) {
	    		direction = "up";
	    	}
	    	if(i> 25 && i<=50) {
	    		direction = "down";
	    		
	    	}
	    	if (i>50 && i<=75) {
	    		direction = "left";
	    	}
	    	if(i>75 && i<=100) {
	    		direction = "right";
	    	}
	    	actionBlockCounter = 0;
	    	}
    	}
    }
	
}
