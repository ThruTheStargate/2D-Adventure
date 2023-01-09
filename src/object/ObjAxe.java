package object;

import entity.Entity;
import main.GamePanel;

public class ObjAxe extends Entity{
	
	public ObjAxe(GamePanel gp) {
		super(gp);
		type = type_axe;
		name = "Wood Cutter's Axe";
		down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 2;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "(" + name + ")" + "\nThe axe is a bit rusty but it\n can still cut some trees."; 
		price = 75;
		knockBackPower = 5;
	}
	
	
}
