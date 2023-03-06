package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;


public class Entity {
	 GamePanel gp;
	 //the many, many instance variables of this class
	 //images
	 public BufferedImage up1, up2, down1, down2, left1, left2,       //regular movement
	 right1, right2;
	 public BufferedImage upSit, downSit, leftSit, rightSit;          //four-legged animal sitting
	 public BufferedImage attackUp1, attackUp2, attackDown1,          //sword movement
	 attackDown2, attackLeft1, attackLeft2, attackRight1,
	 attackRight2; 
	 public BufferedImage axeUp1, axeUp2, axeDown1, axeDown2,         //ax movement
	 axeLeft1, axeLeft2, axeRight1, axeRight2;
	 public BufferedImage staffUp1, staffUp2, staffDown1, staffDown2, //spirit stick movement
	 staffLeft1, staffLeft2, staffRight1, staffRight2;
	 public BufferedImage image, image2, image3;
	 public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
	 public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	 public int solidAreaDefaultX, solidAreaDefaultY;
	 public boolean collisionOn =  false;
	 String dialogues[] = new String[20];
	 
	 //state
	 public int worldX,worldY;
	 public String direction = "down";
	 public int spriteNum = 1;
	 int dialogueIndex = 0;
	 public boolean collision = false;
	 public boolean invincible = false;
	 boolean attacking = false;
	 public boolean alive = true;
	 public boolean dying = false;
	 public boolean hpBarOn = false;
	 public boolean onPath = false;
	 public boolean knockBack = false;
	 
	 //counters
	 public int spriteCounter = 0;
	 public int actionBlockCounter;
	 public int invincibleCounter = 0;
	 public int shotAvailableCounter;
	 public int dyingCounter = 0;
	 public int hpBarCounter = 0;
	 public int knockBackCounter  = 0;

	 //character attributes
	 public String name;
	 public int defaultSpeed;
	 public int speed;
	 public int maxLife;
	 public int life;
	 public int maxMana;
	 public int mana;
	 public int maxAmmo;
	 public int ammo;
	 public int level;
	 public int strength;
	 public int dexterity;
	 public int attack;
	 public int defense;
	 public int exp;
	 public int nextLevelExp;
	 public int coin; 
	 public Entity currentWeapon;
	 public Entity currentShield;
	 public Entity currentMagic;
	 public Entity currentLight;
	 public Projectile projectile;
	 
	 
	 //item attributes
	 public int value;
	 public int attackValue;
	 public int defenseValue;
	 public String description = "";
	 public int useCost;
	 public ArrayList<Entity> inventory = new ArrayList<>();
	 public int maxInventorySize = 20;
	 public int price;
	 public int knockBackPower = 0;
	 public boolean stackable = false;
	 public int amount = 1;
	 public int lightRadius;
	 
	 //type, or the numeric identity of each kind of thing
	 public int type; 
	 public final int type_player = 0; 
	 public final int type_npc = 1;
	 public final int type_monster = 2;
	 public final int type_sword = 3;
	 public final int type_axe = 4;
	 public final int type_shield = 5;
	 public final int type_consumable = 6;
	 public final int type_pickupOnly = 7;
	 public final int type_magic = 8;
	 public final int type_obstacle = 9;
	 public final int type_light = 10;
	 
	 //the class constructor. Finally. 
	 public Entity(GamePanel gp) {
		 this.gp = gp;
		}

	//allow other parts of the program to get the variables
	 public int getLeftX() {
		 return worldX + solidArea.x;
	 }
	 public int getRightX() {
		 return worldX + solidArea.x + solidArea.width;
	 }
	 public int getTopY() {
		 return worldY + solidArea.y;
	 }
	 public int getBottomY() {
		 return worldY + solidArea.y + solidArea.width;
	 }
	 public int getCol() {
		 return (worldX + solidArea.x)/gp.tileSize;
	 }
	 public int getRow() {
		 return (worldY + solidArea.y)/gp.tileSize;
	 }

	 //blank methods, to be filled in each class that uses this class
	 public void setAction() {}       //what does it do?
	 public void damageReaction() {}  //if hurt, how does it bleed?
	 public void interact() {}        //how does it interact with other things?
	 public void checkDrop(){}        //if you kill it, does it drop loot?

	 public void speak() {
	   if(dialogues[dialogueIndex] == null ) {
		   dialogueIndex = 0;
	   }

	   gp.ui.currentDialogue = dialogues[dialogueIndex];
	   dialogueIndex++;
	 
	   switch(gp.player.direction) {
	   case "up": direction = "down";  break;
	   case "down": direction = "up";  break;
	   case "left":  direction = "right";  break;
	   case " right":  direction = "left";  break;
	   }
	 }

	 public boolean use(Entity entity) {return false;}

	 public void dropItem(Entity droppedItem) {
		 for(int i = 0; i < gp.obj[1].length; i++ ) {
			 if(gp.obj[gp.currentMap][i] == null) {
				 gp.obj[gp.currentMap][i] = droppedItem;
				 gp.obj[gp.currentMap][i].worldX = worldX;
				 gp.obj[gp.currentMap][i].worldY = worldY;
				 break;
			 }
		 }
	 }
	 
	 //particle physics?
	 public Color getParticleColor() {
			Color color = null;		
			return color;
		}
		public int getParticleSize() {
			int size = 0; // 6 pixels
			return size;
		}
		
		public int getParticleSpeed() {
			int speed = 0;
			return speed;
		}
		
		public int getParticleMaxLife() {
			int maxLife = 0;
			return maxLife;
		} 
	 
	 public void generateParticle(Entity generator, Entity target) {
		 Color color = generator.getParticleColor();
		 int size = generator.getParticleSize();
		 int speed = generator.getParticleSpeed();
		 int maxLife = generator.getParticleMaxLife();
		//Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd )
		 Particle pl = new Particle(gp, target, color, size, speed, maxLife,-2, -1);
		 Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
		 Particle p3 = new Particle(gp, target, color, size, speed, maxLife,-2, 1);
		 Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
		 gp.particleList.add(pl);
		 gp.particleList.add(p2);
		 gp.particleList.add(p3);
		 gp.particleList.add(p4);
		 
	 }
	 
	 public boolean getCollision() {
		return collisionOn;
	 }

	 //have I collided with something?
	 public void checkCollision() {
		 collisionOn = false;                                 //set collision to false
		 gp.cCheck.checkTile(this);                           //is the tile solid?
		 gp.cCheck.checkObject(this, false);
		 gp.cCheck.checkEntity(this, gp.npc);
		 gp.cCheck.checkEntity(this, gp.monster);	
		 gp.cCheck.checkEntity(this, gp.iTile);
		 boolean contactPlayer = gp.cCheck.checkPlayer(this);
		 
		 if(this.type == type_monster && contactPlayer == true) {
			 damagePlayer(attack);
		 }
	 }
	 
	 public void update() {
		 
		 if (knockBack) {
			 
			 checkCollision();
			 
			 if( collisionOn) {
				 knockBackCounter = 0;
				 knockBack = false;
				 speed = defaultSpeed;
			 } else if( !collisionOn) {
				 switch(gp.player.direction) {
				 	case "up": worldY -= speed; break;
			 		case "down": worldY += speed; break;
			 		case "left": worldX -= speed; break;
			 		case "right": worldX += speed; break; 
			 		case "none":  worldX += 0; worldY +=0; break;
				 }
			 }
			 knockBackCounter++;
			 if (knockBackCounter > 10) {
				 knockBackCounter = 0;
				 knockBack = false;
				 speed = defaultSpeed;
			 }
			 
		 } else {
			 
			 setAction();
			 checkCollision(); 
			 //If collision is false , can move
			 	if(collisionOn == false ) {
			 		switch(direction) {
			 		case "up": worldY -= speed; break;
			 		case "down": worldY += speed; break;
			 		case "left": worldX -= speed; break;
			 		case "right": worldX += speed; break; 
			 		case "none":  worldX += 0; worldY +=0; break;
			 		}
			 	}
			 
		 }
		 
	        spriteCounter++;
	        if(spriteCounter > 24) {
	        	if(spriteNum ==1) {
	        		spriteNum = 2;
	        	}
	        	else if(spriteNum ==2) {
	        		spriteNum = 1;
	        	}
	        	spriteCounter = 0;
	        }
	        
	        if(invincible) {
	     	   invincibleCounter++;
	     	   if(invincibleCounter > 40) {
	     		   invincible = false;
	     		   invincibleCounter = 0;
	     	   }
	        }
	        if(shotAvailableCounter < 30) {
	     	   shotAvailableCounter++;
	        }
		 
	 }
	 
	//how to hurt the player
	public void damagePlayer(int attack) {
		 if(gp.player.invincible == false) {
			 gp.playSE(6);

			  int damage = attack - gp.player.defense;
			  if(damage < 0) {
				  damage = 0;
			  }
			 gp.player.life -= damage;
			 gp.player.invincible = true;
		 }
	}
	 
	 public void draw(Graphics2D g2) {
			
		 	BufferedImage image = null;
			int ScreenX = worldX - gp.player.worldX + gp.player.screenX;
			int ScreenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX > gp.player.worldX-gp.player.screenX - gp.tileSize&& 
			   worldX < gp.player.worldX+gp.player.screenX + gp.tileSize&&
			   worldY > gp.player.worldY-gp.player.screenY - gp.tileSize&& 
			   worldY < gp.player.worldY+gp.player.screenY + gp.tileSize ){
				
			        switch(direction){
			            case "up": if(spriteNum == 1) {image = up1;}
			            	if(spriteNum == 2) {image = up2;}
			                break;
			            case "down": if(spriteNum == 1) {image = down1;}
			            	if(spriteNum == 2) {image = down2;} break;
			            case "left": if(spriteNum == 1) {image = left1;}
			            	if(spriteNum == 2) {image = left2;} break;
			            case "right": if(spriteNum == 1) {image = right1;}
			            	if(spriteNum == 2) {image = right2;} break;
			        }
			        
			        // Monister Health Bar
			        if(type ==2 && hpBarOn) {
			        	double oneScale = (double)gp.tileSize/maxLife;
			        	double hpVarValue = oneScale * life;
				        g2.setColor(new Color(35,35,35));
				        g2.fillRect(ScreenX-1, ScreenY-16, gp.tileSize+2, 12);
				        g2.setColor(new Color(255,0,30));
				        g2.fillRect(ScreenX, ScreenY-15, (int)hpVarValue, 10);
				        
				        hpBarCounter++;
				        
				        if(hpBarCounter> 600) {
				        	hpBarCounter = 0;
				        	hpBarOn = false;
				        }
			        
			        }
			        
			        if(invincible) {
			        	hpBarOn = true;
			        	hpBarCounter = 0;
			        	changeAlpha(g2, 0.4f);
			        	
			        }
			        if(dying) {
			        	dyingAnimation(g2);
			        }
				g2.drawImage(image, ScreenX, ScreenY, null);
				
				changeAlpha(g2, 1f);
			}
			
		}
	 
	 public void dyingAnimation(Graphics2D g2) {
		 dyingCounter++;
		 
//		 if(dyingCounter % 8 == 0) {
//			 if (alpha == 0f) {alpha = 1f;}else if(alpha == 1f){alpha = 0f;}
//			 g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
//			 if(dyingCounter > 40) {
//				 dying = false;
//				 alive = false;
//			 }
//		 }
		 
		 int i=5;
		 if(dyingCounter <= i ) { changeAlpha(g2, 0f);}
		 if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
		 if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
		 if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
		 if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
		 if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
		 if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
		 if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);} 
		 if(dyingCounter > i*8) {
		 alive = false;
		 }
	 }
	 public void changeAlpha(Graphics2D g2, float alphaValue) {
		 g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
	 }
	 public BufferedImage setup(String imagePath , int width, int height) {
		   	UtilityTool uTool = new UtilityTool();
		   	BufferedImage image = null;
				try {
					image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
					image = uTool.scaledImage(image, width, height);
					
				}catch(IOException e) {
					e.printStackTrace();
				}
				return image;
		   }
	 public void searchPath(int goalCol, int goalRow) {

		 int startCol = (worldX + solidArea.x)/gp.tileSize;
		 int startRow = (worldY + solidArea.y)/gp.tileSize;
		 gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow,this);
		
		 if(gp.pFinder.search() == true) {
			 //next  wolrdX and wolrdY
			 int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			 int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			 // entity's solid area position
			 int enLeftX = worldX + solidArea.x;
			 int enRightX = enLeftX + solidArea.width;
			 int enTopY = worldY + solidArea.y;
			 int enBottomY = enTopY + solidArea.height;
			 
			 if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize ) {
				 direction = "up";
			 }
			 else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize ) {
				 direction = "down";
			 }
			 else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize ) {
				 //left or right 
				 if(enLeftX > nextX) {
					 direction = "left";
				 }
				 if(enLeftX < nextX) {
					 direction = "right";
				 }

			 }
			 
			 
			 else if(enTopY > nextY && enLeftX > nextX) {
				 // up or left
				 direction = "up";
				 checkCollision();
				 if(collisionOn) {
					 direction = "left";
				 }
			 } 
			 else if(enTopY > nextY && enLeftX < nextX) {
				//up or right
				 direction = "up";
				 checkCollision();
				 if(collisionOn) {
					 direction = "right";
				 }
			 }  
			 else if(enTopY < nextY && enLeftX > nextX) {
				 //down or left
				 direction = "down";
				 checkCollision();
				 if(collisionOn) {
					 direction = "left";
				 }
			 }  
			 else if(enTopY < nextY && enLeftX < nextX) {
				 //down or right
				 direction = "down";
				 checkCollision();
				 if(collisionOn) {
					 direction = "right";
				 }
			 }
			 
			 
			// if he reaches the goal stop the search
			// /* use the comment out if you want the NPC to follow you. 
			 
			 int nextCol = gp.pFinder.pathList.get(0).col;
			 int nextRow = gp.pFinder.pathList.get(0).row;
			 if(nextCol == goalCol && nextRow == goalRow) {
				 onPath = false;
			 }
			
			 // */ 
		 }
	 }
	 
	 public int getDetected(Entity user, Entity target[][], String targetName) {
		int index = 999;
		//Check surrounding object
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		
		switch (user.direction) {
			case "up": nextWorldY = user.getTopY() - 1; break;
			case "down": nextWorldY = user.getBottomY() + 1; break;
			case "left": nextWorldX = user.getLeftX() - 1; break;
			case "right": nextWorldX = user.getRightX() + 1; break;
		}
		
		int col = nextWorldX / gp.tileSize;
		int row = nextWorldY / gp.tileSize;
		
		for( int i = 0; i <  target[1].length ;i++ ) {
			if(target[gp.currentMap][i] != null) {
				if(target[gp.currentMap][i].getCol() == col && 
						target[gp.currentMap][i].getRow() == row && 
						target[gp.currentMap][i].name.equals(targetName)) {
					index = i;
					break;
				}
			}
			
		}
		
		 return index;
	 }
}
