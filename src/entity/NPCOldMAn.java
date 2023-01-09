package entity;



import java.util.Random;

import main.GamePanel;

public class NPCOldMAn extends Entity{

	public NPCOldMAn(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 2;
		
		getImage();
		setDialogue();
	}
    public void getImage(){
        
   	 	up1 = setup("/NPC/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/oldman_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/oldman_right_2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/oldman_left_2", gp.tileSize, gp.tileSize);   
   }
    public void setDialogue() {
    	
    	dialogues[0] = "Hello lad.";
    	dialogues[1] = "So, you've come to the island to \nfind the teasure";
    	dialogues[2] = "I used to be a great Wizard \nbut now... I'm a bit too old for \ntaking an adventure.";
    	dialogues[3] = "Well, good luck on your journey.";
    	
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
   public void speak() {
	   
	   //Do this charageter specific stuff
	   super.speak();
	   onPath = true;
   }
   
   
   
	
}
