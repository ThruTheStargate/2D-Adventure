package object;

import entity.Entity;
import main.GamePanel;

public class ObjAxeTree extends Entity{
	GamePanel gp;
	public ObjAxeTree(GamePanel gp) {
		super(gp);
		name = "deadTree";
			down1 = setup("/tiles/tree", gp.tileSize, gp.tileSize);
		collision = true;
		description = "(" + name + ")" ; 
	}
}
