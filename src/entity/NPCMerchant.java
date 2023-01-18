package entity;

import java.awt.Font;

import main.GamePanel;
import object.ObjAxe;
import object.ObjFireBall;
import object.ObjKey;
import object.ObjPotionRed;
import object.ObjShieldBlue;
import object.ObjShieldWood;
import object.ObjSwordNormal;

public class NPCMerchant extends Entity{

	public NPCMerchant(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 0;
		
		getImage();
		setDialogue();
		setItem();
		
	}
    public void getImage(){
        
   	 	up1 = setup("/NPC/merchant_down_1", gp.tileSize, gp.tileSize);;
        up2 = setup("/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/merchant_down_2", gp.tileSize, gp.tileSize);   
   }
    public void setDialogue() {
    	
    	dialogues[0] = "You found me; I have some good\n stuff.  Do you want to trade?";
//    	dialogues[1] = "";
//    	dialogues[2] = "" + "";
//    	dialogues[3] = " ";
    	
    }
    public void setItem() {
    	inventory.add(new ObjPotionRed(gp));
    	inventory.add(new ObjKey(gp));
    	inventory.add(new ObjSwordNormal(gp));
    	inventory.add(new ObjAxe(gp));
    	inventory.add(new ObjShieldWood(gp));
    	inventory.add(new ObjShieldBlue(gp));
    	inventory.add(new ObjFireBall(gp));
    }
    
    public void speak() {
    	super.speak();
    	gp.gameState = gp.tradeState;
    	gp.ui.npc = this;
    }

}
