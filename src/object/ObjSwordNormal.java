package object;

import entity.Entity;
import main.GamePanel;

public class ObjSwordNormal extends Entity{

	public ObjSwordNormal(GamePanel gp) {
		super(gp);
		type = type_sword;
		name = "Normal Sword";
		down1 = setup("/objects/sword_normal", gp.tileSize,gp.tileSize);
		attackValue = 1;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "(" + name + ")" +"\nAn old sword."; 
		price = 15;
		knockBackPower = 2;
	
	}

}


/*
	I am thinking about creating a weapons class in between the weapons and the entity class to handle weapon specific stuff. 
*/