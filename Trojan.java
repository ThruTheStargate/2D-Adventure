package monster; //file name

import java.util.Random; 
import entity.Entity;    //importing the entity class
import main.GamePanel;   //importing GamePanel class

public class Trojan extends Monster{

	public Trojan(GamePanel gp) {
		super(gp);
		direction = "right";
		speed = 2;
		
		getImage();
		//setDialogue();
	}
    public void getImage(){
        
   	 	up1 = setup("/NPC/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/oldman_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Walk/G_Walk_01", gp.tileSize, gp.tileSize);
        right2 = setup("/Walk/G_Walk_02", gp.tileSize, gp.tileSize);
        left1 = setup("/Walk/G_Walk_06", gp.tileSize, gp.tileSize);
        left2 = setup("/Walk/G_Walk_07", gp.tileSize, gp.tileSize);   
   }
   /*
   public void setDialogue() {
    	
    	dialogues[0] = "Morning, good sir";
    	dialogues[1] = "cheese";
    	dialogues[2] = "string.";
    	dialogues[3] = "fat black widow spiders";
    	
    }
	//*/ 
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
   public void speak() {
	   
	   //Do this charageter specific stuff
	   super.speak();
	   onPath = true;
   }
   
   
   
	
}
