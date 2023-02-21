package monster; //file name

import java.util.Random;
import entity.Entity;        //all our classes
import main.GamePanel;
import object.ObjChest;      //loot classes
import object.ObjCoinsBronze;
import object.ObjHeart;
import object.ObjManaCrystal;
import object.ObjRock;

public class MonGreenSlime extends Monster{  //using the monster class
 GamePanel gp;
	public MonGreenSlime(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = "greenSlime";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 10;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		
		
		solidArea.x = 3;
		solidArea.y =18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
	}
	public void getImage() {
		up1 = setup("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
		
	}
	
	public void update() {
		super.update();
		
		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY-gp.player.worldY);
		int tileDistance = (xDistance + yDistance)/gp.tileSize;
		
		if(!onPath && tileDistance < 5) {
			int i = new Random().nextInt(100) + 1;
			if(i > 50 ) {
				onPath = true;
			}
		}
		
		if(onPath && tileDistance > 20) {
			onPath = false;
		}
	}
	public void setAction() {
		if(onPath) {
    		
    		// uncomment the below if you want the NPC to follow the character. 
    		int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
    		int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
    		
    		searchPath(goalCol, goalRow);
    		
    		int i = new Random().nextInt(200) + 1;
    		if(i > 197 && projectile.alive == false && shotAvailableCounter == 30) {
    			projectile.set(worldX, worldY, direction, true, this);
    			
    			for( int ii = 0 ; ii < gp.projectile[1].length; ii++) {
        			if( gp.projectile[gp.currentMap][ii] == null) {
        				gp.projectile[gp.currentMap][ii] = projectile;
        				break;
        			}
        		}
    			
    		}
    		
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
	public void damageReaction() {
		actionBlockCounter = 0;
		//direction = gp.player.direction;
		onPath = true;
		
		
	}
	

	
}
