package object;

import entity.Entity;
import main.GamePanel;

public class ObjBoots extends Entity{
	GamePanel gp;
	public ObjBoots(GamePanel gp) {
		super(gp);
		type = type_consumable;
		name = "Magical Boot";
		down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
		collision = true;
		description = "(" + name + ")" + "\nA pair of boots that appear \nto be magical."; 
		price = 100;
	}

}
