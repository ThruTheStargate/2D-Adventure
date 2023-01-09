package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MonRedSlime extends Monster{
	 GamePanel gp;
		public MonRedSlime(GamePanel gp) {
			super(gp);
			this.gp = gp;
			
			type = type_monster;
			name = "Red Slime";
			defaultSpeed = 2;
			speed = defaultSpeed;
			maxLife = 45;
			life = maxLife;
			attack = 10;
			defense = 4;
			exp = 5;
			
			solidArea.x = 3;
			solidArea.y =18;
			solidArea.width = 42;
			solidArea.height = 30;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
			getImage();
		}
		public void getImage() {
			up1 = setup("/monsters/redslime_down_1", gp.tileSize, gp.tileSize);
			up2 = setup("/monsters/redslime_down_2", gp.tileSize, gp.tileSize);
			down1 = setup("/monsters/redslime_down_1", gp.tileSize, gp.tileSize);
			down2 = setup("/monsters/redslime_down_2", gp.tileSize, gp.tileSize);
			left1 = setup("/monsters/redslime_down_1", gp.tileSize, gp.tileSize);
			left2 = setup("/monsters/redslime_down_2", gp.tileSize, gp.tileSize);
			right1 = setup("/monsters/redslime_down_1", gp.tileSize, gp.tileSize);
			right2 = setup("/monsters/redslime_down_2", gp.tileSize, gp.tileSize);
			
		}
		public void setAction() {
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
		public void damageReaction() {
			actionBlockCounter = 0;
			direction = gp.player.direction;
			
		}
}
