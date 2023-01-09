package object;

import entity.Entity;
import main.GamePanel;

public class ObjShieldWood extends Entity{

	public ObjShieldWood(GamePanel gp) {
		super(gp);
		type = type_shield;
		name = "Wood Sheild";
		down1 = setup("/objects/shield_wood",gp.tileSize,gp.tileSize);
		defenseValue =1;
		description = "(" + name + ")" + "\nAn old wood sheild."; 
		price = 10;
	}

}
