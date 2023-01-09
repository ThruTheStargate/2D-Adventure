package object;

import entity.Entity;
import main.GamePanel;

public class ObjShieldBlue extends Entity{

	 public ObjShieldBlue(GamePanel gp) {
		super(gp);
		type = type_shield;
		name = "Blue Sheild";
		down1 =setup("/objects/shield_blue",gp.tileSize,gp.tileSize);
		defenseValue = 2;
		description = "(" + name + ")" + "\nA shinny blue sheild.";
		price = 150;
	}

	
		 
}
