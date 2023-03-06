package entity; //the folder

//classes imported
import main.GamePanel;

public class Projectile extends Entity{ //class projectile uses parts of entity

	//the instance variables
	Entity user;
	boolean collision;

	public Projectile(GamePanel gp) { //the constructor
		super(gp);
		
	}

	//sets up the projectile
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
		
	}

	//updates its progress
	public void update() {
		
		if(user == gp.player) {
			int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex, attack, knockBackPower);
				generateParticle(user.projectile,gp.monster[gp.currentMap][monsterIndex]);
				alive = false;
			}
		}
		if(user != gp.player) {
			boolean contactPlayer = gp.cCheck.checkPlayer(this);
			if(gp.player.invincible == false && contactPlayer == true) {
				damagePlayer(attack);
				generateParticle(user.projectile,gp.player);
				alive = false;
			}
		}
		
		checkCollision();

		switch(direction) {
 		case "up": worldY -= speed; break;
 		case "down": worldY += speed; break;
 		case "left": worldX -= speed; break;
 		case "right": worldX += speed; break; 
		}
		
		life--;
		if(life <= 0) {
			alive = false;
		}
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;				
			}
			else if(spriteNum ==2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResoure = false;
		return haveResoure;
	}
	public void subtractResource(Entity user) {}

}
