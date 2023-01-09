package object;

import entity.Entity;
import main.GamePanel;

public class ObjPotionRed extends Entity {
 
	GamePanel gp;
	
	public ObjPotionRed(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_consumable;
		stackable = true;
		name = " Red Potion";
		value = 5;
		down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
		description = "[Red Potion] \n Heals your Life by " + value + ".";
		price = 300;
	}
	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState; 
		gp.ui.currentDialogue = "You drink the " + name + "!\nYour life has been restored by " + value + ".";
		entity.life += value;
		
		gp.playSE(2);
		return true;
	}
}
