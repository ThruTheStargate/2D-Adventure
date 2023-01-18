package entity;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.ObjBoots;
import object.ObjKey;
import object.ObjLantern;
import object.ObjPotionRed;
import object.ObjShieldWood;
import object.ObjSwordNormal;
import object.ObjAxe;
import object.ObjFireBall;
import object.ObjFireBallBlue;
import object.ObjRock;

public class Player extends Entity{
    
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    boolean hasFireBall = false;
    public boolean lightUpdated = false;
    
    public Player(GamePanel gp, KeyHandler keyH){
    	super(gp);
        
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2- (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 25;
        solidArea.height = 25;
        
//        attackArea.width = 36;
//        attackArea.height = 36;
        
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        
    }
    public void setDefaultValues(){
        
    	worldX = gp.tileSize*23;
    	worldY = gp.tileSize*21;
//    	worldX = gp.tileSize*12;
//        worldY = gp.tileSize*11; 
        //gp.currentMap = 1;
    	defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";
        
        //Player status 
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        //ammo = 10;
        strength = 1; // the more strength he has, the more damage he gives
        dexterity = 1; // The more desterity he has, the less damage he recives. 
        exp = 0 ;
        nextLevelExp = 5;
        coin = 500;
        currentWeapon = new ObjSwordNormal(gp);
        //currentWeapon = new ObjAxe(gp);
        currentShield = new ObjShieldWood(gp);
        projectile = new ObjFireBall(gp);
        //projectile = new ObjRock(gp);
        attack = getAttack(); // The total attack value is decided by strength and weapon.
        defense = getDefense(); // The toal defense value is decided by dexterity and durrent shield.
        setItems();
    }
    public void setDefualtPositions() {
    	worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        direction = "down";
        
    }
    
    public void restoreLifeAndMana() {
    	life = maxLife;
    	mana = maxMana;
    	invincible = false;
    }
    public void setItems() {
    	inventory.clear();
    	inventory.add(currentWeapon);
    	inventory.add(currentShield);
    	inventory.add(new ObjKey(gp));
//    	inventory.add(new ObjFireBall(gp));
//    	inventory.add(new ObjLantern(gp));
//    	inventory.add(new ObjKey(gp));
//    	inventory.add(new ObjKey(gp));
//    	inventory.add(new ObjFireBallBlue(gp));
//    	inventory.add(new ObjPotionRed(gp));
//    	inventory.add(new ObjKey(gp));
//    	inventory.add(new ObjKey(gp));
//    	inventory.add(new ObjFireBallBlue(gp));
//    	inventory.add(new ObjFireBallBlue(gp));
//    	inventory.add(new ObjPotionRed(gp));
//    	inventory.add(new ObjKey(gp));
//    	inventory.add(new ObjKey(gp));
//    	inventory.add(new ObjFireBallBlue(gp));
//    	inventory.add(new ObjPotionRed(gp));
//    	inventory.add(new ObjKey(gp));
//    	inventory.add(new ObjKey(gp));
    	
    	
    	
    }
    
    public int getAttack() {
    	attackArea = currentWeapon.attackArea;
    	return attack = strength * currentWeapon.attackValue;
    }
    

    public int getDefense() {
    	
    	return defense = dexterity * currentShield.defenseValue;
    	
    }
    public void getPlayerImage(){
        
    	 up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
         up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
         down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
         down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
         right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
         right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
         left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
         left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);          
    }  
    public void getPlayerAttackImage() {
    	
    	if(currentWeapon.type == type_sword ) {
	    	attackUp1 = setup("/attack/boy_attack_up_1", gp.tileSize, gp.tileSize*2);   
	        attackUp2 = setup("/attack/boy_attack_up_2", gp.tileSize, gp.tileSize*2);     
	        attackDown1 = setup("/attack/boy_attack_down_1", gp.tileSize, gp.tileSize*2);   
	        attackDown2 = setup("/attack/boy_attack_down_2", gp.tileSize, gp.tileSize*2);  
	        attackRight1 = setup("/attack/boy_attack_right_1", gp.tileSize*2, gp.tileSize);   
	        attackRight2 = setup("/attack/boy_attack_right_2", gp.tileSize*2, gp.tileSize);   
	        attackLeft1 = setup("/attack/boy_attack_left_1", gp.tileSize*2, gp.tileSize);   
	        attackLeft2 = setup("/attack/boy_attack_left_2", gp.tileSize*2, gp.tileSize); 
    	} else if(currentWeapon.type == type_axe) {
    		attackUp1 = setup("/attack/boy_axe_up_1", gp.tileSize, gp.tileSize*2);   
    		attackUp2 = setup("/attack/boy_axe_up_2", gp.tileSize, gp.tileSize*2); 
    		attackDown1 = setup("/attack/boy_axe_down_1", gp.tileSize, gp.tileSize*2);   
    		attackDown2 = setup("/attack/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
    		attackLeft1 = setup("/attack/boy_axe_left_1", gp.tileSize*2, gp.tileSize);   
    		attackLeft2 = setup("/attack/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
    		attackRight1 = setup("/attack/boy_axe_right_1", gp.tileSize*2, gp.tileSize);   
    		attackRight2 = setup("/attack/boy_axe_right_2", gp.tileSize*2, gp.tileSize);
    	}
    }

    public void update(){
    	
    	if(attacking) { 
    		attacking();
    	}
    	else if(keyH.upPressed == true || keyH.downPressed == true ||
    			keyH.leftPressed == true || keyH.rightPressed == true || keyH.debug == true || keyH.spacePressed == true) {
    		 if(keyH.upPressed == true){
    	            direction = "up";
    	        }else if( keyH.downPressed == true){
    	            direction = "down";    	            
    	        }else if( keyH.leftPressed == true){
    	            direction = "left";    	            
    	        }else if(keyH.rightPressed == true){
    	            direction = "right";    	            
    	        }

    		 
    		 
    		 	//Check Tile Collision
    		 	collisionOn = false;
    		 	gp.cCheck.checkTile(this);
    		 	
    		 	//check object collision
    		 	int objIndex = gp.cCheck.checkObject(this, true);
    		 	pickUpObject(objIndex);
    		 	
    		 	
    		 	//Check NPC Collison
    		 	int npcIndex = gp.cCheck.checkEntity(this, gp.npc);
    		 	interactNPC(npcIndex);
    		 	
    		 	
    		 	// check nmonster collision
    		 	int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
    		 	contactMonster(monsterIndex);
    		 	
    		 	//Check interactive tile collision
    		 	int iTileIndex = gp.cCheck.checkEntity(this, gp.iTile);
    		 	
    		 	//Check event colision
    		 	gp.eHandler.checkEvent();
    		 	
    		 	
    		 	
    		 	//IF colliosn is false, player can move
    		 	if(collisionOn == false && keyH.spacePressed == false ) {
    		 		switch(direction) {
    		 		case "up": worldY -= speed; break;
    		 		case "down": worldY += speed; break;
    		 		case "left": worldX -= speed; break;
    		 		case "right": worldX += speed; break; 
    		 		case "none":  worldX += 0; worldY +=0; break;
    		 		}
    		 	}
    		 
    		 	if(keyH.spacePressed && !attackCanceled) {
    		 		gp.playSE(7);
    		 		attacking = true;
    		 		spriteCounter = 0;
    		 	}
    		 	
    		 	attackCanceled = false;
    		 	gp.keyH.spacePressed = false;
    		 	
    	        spriteCounter++;
    	        if(spriteCounter > 12) {
    	        	if(spriteNum ==1) {
    	        		spriteNum = 2;
    	        	}
    	        	else if(spriteNum == 2) {
    	        		spriteNum = 1;
    	        	}
    	        	spriteCounter = 0;
    	        }
    	}else{
    		spriteNum = 1;
    	}
    	
    	
    	
    	///this is outSide the if statement
    	if(this.projectile != null) {
    	if(gp.keyH.shotKeyPressed == true  && !projectile.alive
    			&& shotAvailableCounter == 100 && projectile.haveResource(this) == true && hasFireBall) {
    		// Set Default Coordinates.  Direction and user
    		projectile.set(worldX, worldY, direction, true, this);
    		
    		//Subtract the cost
    		projectile.subtractResource(this);
    		
    		//add to array list.
    		//gp.projectileList.add(projectile);
    		//Check Vacancy  
    		for( int i = 0 ; i < gp.projectile[1].length; i++) {
    			if( gp.projectile[gp.currentMap][i] == null) {
    				gp.projectile[gp.currentMap][i] = projectile;
    				break;
    			}
    		}
    		shotAvailableCounter = 0;
    		gp.playSE(10);
    		
    	}
    	}
       if(invincible) {
    	   invincibleCounter++;
    	   if(invincibleCounter > 60) {
    		   invincible = false;
    		   invincibleCounter = 0;
    	   }
       }
       
       if(shotAvailableCounter < 100) {
    	   shotAvailableCounter++;
       }
       
       if(life > maxLife) {
			life = maxLife;
		}
       if(mana > maxMana) {
			mana = maxMana;
		}
       
       if(life <= 0) {
    	   gp.gameState = gp.gameOverState;
    	   gp.ui.message.clear();
    	   gp.stopMusic();
    	   gp.playSE(12);
    	   //gp.playMusic(gameOverMusic);
       }
    }
    
    
    public void attacking() {
    	spriteCounter++;
    	
    	if(spriteCounter <= 5) {
    		spriteNum = 1;
    	}
    	if(spriteCounter > 5 && spriteCounter <= 25) {
    		spriteNum = 2;
    		//save current x and  y 
    		int currentWorldX = worldX;
    		int currentWorldY = worldY;
    		int solidAreaWidth = solidArea.width;
    		int solidAreaHeight = solidArea.height;
    		//adjust players location
    		
    		switch(direction) {
    		case "up": worldY -= attackArea.height; break;
    		case "down": worldY += attackArea.height; break;
    		case "left": worldX -= attackArea.width; break;
    		case "right": worldX += attackArea.width; break;  		
    		}
    		
    		//attackArea becomes solid area
    		solidArea.width = attackArea.width;
    		solidArea.height = attackArea.height;
    		
    		// Check monster collision with updated possion and area.
    		int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
    		damageMonster(monsterIndex, attack, currentWeapon.knockBackPower);
    		
    		// check interactive tile collision
    		
    		int iTileIndex = gp.cCheck.checkEntity(this, gp.iTile);
    		damageInteractiveTile(iTileIndex);
    		
    		
    		int projectileIndex = gp.cCheck.checkEntity(this, gp.projectile);
    		damageProjectile(projectileIndex);
    		
    		//after attack return to original position and arear
    		worldX = currentWorldX;
    		worldY = currentWorldY;
    		solidArea.width = solidAreaWidth;
    		solidArea.height = solidAreaHeight;
    	}
    	if(spriteCounter > 25) {
    		spriteNum = 1;
    		spriteCounter = 0;
    		attacking = false;
    	} 
    	
    	
    }
    
    public void knockBack(Entity entity, int knockBackPower) {
    	entity.direction = direction;
    	entity.speed += knockBackPower;
    	entity.knockBack = true;
    }
    private void damageInteractiveTile(int i) {
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible 
				&& gp.iTile[gp.currentMap][i].isCorrectItem(this) && gp.iTile[gp.currentMap][i].invincible == false) {
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			
			//Generates Particles
			
			generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i] );
			
			if(gp.iTile[gp.currentMap][i].life <= 0){
			gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
		
	}
	public void pickUpObject(int i) {
    	if(i != 999) {
    		
    		
    		//PickUP Only
    		
    		if(gp.obj[gp.currentMap][i].type == type_pickupOnly){
    			 gp.obj[gp.currentMap][i].use(this);
    			 gp.obj[gp.currentMap][i] = null;
    		}
    		
    		else if( gp.obj[gp.currentMap][i].type == type_obstacle){
    			if (keyH.spacePressed) {
    				attackCanceled = true;
    				gp.obj[gp.currentMap][i].interact();
    			}
    		}
    		
    		// inventory Items
    		else {
    		String text;
    		if(canObtainItem(gp.obj[gp.currentMap][i])) {
    			//inventory.add(gp.obj[gp.currentMap][i]);
    			gp.playSE(1);
    			text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
    		}
    		else {
    			text = " You can not carry any more items";
    		}
    		gp.ui.addMessage(text);
    		gp.obj[gp.currentMap][i] = null;
    	}
    		
    	}
    	
    }
        
    public void interactNPC(int i) {
    	
    	if(gp.keyH.spacePressed == true) {
    		
	    	if(i != 999) {
	    		attackCanceled = true;
		    		gp.gameState = gp.dialogueState;
		        	gp.npc[gp.currentMap][i].speak();
	    		}	
    	}
    }
     
   public void contactMonster(int i) {
	   if (i != 999) {
		   if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
			   gp.playSE(6);
			   
				  int damage = gp.monster[gp.currentMap][i].attack - defense;
				  if(damage < 0) {
					  damage = 0;
				  }
			   life -= damage;
			   invincible = true;
		   }
	   }
   }
   
   public void damageMonster(int i, int attack, int knockBackPower ) {
	   if(i != 999) {
		  if(!gp.monster[gp.currentMap][i].invincible) {
			  gp.playSE(5);
			  
			  if(knockBackPower > 0 ) {
			  knockBack(gp.monster[gp.currentMap][i],knockBackPower);
			  }
			  
			  int damage = attack - gp.monster[gp.currentMap][i].defense;
			  if(damage < 0) {
				  damage = 0;
			  }
			  gp.monster[gp.currentMap][i].life -= damage;
			  gp.ui.addMessage(damage + " damage!");
			  gp.monster[gp.currentMap][i].invincible = true;
			  gp.monster[gp.currentMap][i].damageReaction();
			  if(gp.monster[gp.currentMap][i].life <= 0) {
				  gp.monster[gp.currentMap][i].dying = true;
				  gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
				  gp.ui.addMessage("exp " + gp.monster[gp.currentMap][i].exp + "!");
				  exp += gp.monster[gp.currentMap][i].exp;
				  checkLevelUp();
				  
			  }
		  }
		  
	   }
   }
   
   
   public void damageProjectile(int i) {
	   if (i != 999){
		   
		   /*
		   //Entity projectile1 = gp.projectile[gp.currentMap][i];
		   gp.projectile[gp.currentMap][i].alive = false;
		   generateParticle(gp.projectile[gp.currentMap][i], gp.projectile[gp.currentMap][i]);
		   */
		   Entity projectile1 = gp.projectile[gp.currentMap][i];
		   projectile1.alive = false;
		   generateParticle(projectile1, projectile1);
	   }
   }
   public void checkLevelUp() {
	   if(exp >= nextLevelExp) {
		   level++;
		   nextLevelExp = nextLevelExp + 20 * level;
		   if(level % 5 == 0){
		   	maxLife += 2;
		   }
		   strength++;
		   dexterity++;
		   attack = getAttack();
		   defense = getDefense();
		   gp.playSE(8);
		   gp.gameState = gp.dialogueState;
		   gp.ui.currentDialogue = "You are level " + level + " now!\n" 
				   + "You feel stronger!";
	   }
   }
    public void selectItem() {
    	int itemIndex = gp.ui.getItemDescription(gp.ui.playerSlotCol,gp.ui.playerSlotRow);
    	if(itemIndex < inventory.size()) {
    		Entity selectedItem = inventory.get(itemIndex); 
    		if(selectedItem.type == type_sword || selectedItem.type == type_axe) {
    			currentWeapon = selectedItem;
    			attack = getAttack();
    			getPlayerAttackImage();
    		}
    		if(selectedItem.type == type_shield) {
    			currentShield = selectedItem;
    			defense = getDefense();
    		}
    		if(selectedItem.type == type_consumable) {
    			if(selectedItem.use(this)){
    				if(selectedItem.amount > 1) {
    					selectedItem.amount--;
    				}
    				else {
    					inventory.remove(itemIndex);
    				}
    			}
    		}
    		if(selectedItem.type == type_magic) {
    			currentMagic = selectedItem;
    			hasFireBall = true;
    		}
    		if(selectedItem.type == type_light) {
    			if(currentLight == selectedItem) {
    				currentLight = null;
    			} else {
    				currentLight = selectedItem;
    			}
    			lightUpdated = true;
    		}
    		
    		
    	}
    }
    
    public int searchItemInventory(String itemName) {
    	int itemIndex = 999; 
    	for(int i = 0; i < inventory.size(); i++) {
    		if(inventory.get(i).name.equals(itemName)) {
    			itemIndex = i;
    			break;
    		}
    	}
    	return itemIndex;
    }
    public boolean canObtainItem(Entity item) {
    	boolean canObtain = false;
    	if(item.stackable) {
    		int index = searchItemInventory(item.name);
    				if(index != 999) {
    					inventory.get(index).amount++;
    					canObtain = true;
    				}
    				else { // new Item do not currently have
    					if(inventory.size() != maxInventorySize) {
    						inventory.add(item);
    						canObtain = true;
    					}
    				}
    	}
    	else {// Not stackable
    		if(inventory.size() != maxInventorySize) {
				inventory.add(item);
				canObtain = true;
			}
    	}
    	return canObtain;
    }
    public void draw(Graphics2D g2){

        
        // g2.setColor(Color.white);
        // g2.fillRect(x,y,gp.tileSize, gp.tileSize);
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        BufferedImage image = null;
        
        switch(direction){
            case "up":
            	if(!attacking) {
            	if(spriteNum == 1) {image = up1;}
            	if(spriteNum == 2) {image = up2;}
            	}
            	if(attacking) {
            		tempScreenY = screenY-gp.tileSize;
                	if(spriteNum == 1) {image = attackUp1;}
                	if(spriteNum == 2) {image = attackUp2;}
                	}
                break;
            case "down":
            	if(!attacking) {
            	if(spriteNum == 1) {image = down1;}
            	if(spriteNum == 2) {image = down2;}
            	}
            	if(attacking) {
                	if(spriteNum == 1) {image = attackDown1;}
                	if(spriteNum == 2) {image = attackDown2;}
                	}
                break;
            case "left": 
            	if(!attacking) {
            	if(spriteNum == 1) {image = left1;}
            	if(spriteNum == 2) {image = left2;}
            	}
            	if(attacking) {
            		tempScreenX = screenX-gp.tileSize;
                	if(spriteNum == 1) {image = attackLeft1;}
                	if(spriteNum == 2) {image = attackLeft2;}
                	}
                break;
            case "right":
            	if(!attacking) {
            	if(spriteNum == 1) {image = right1;}
            	if(spriteNum == 2) {image = right2;}
            	}
            	if(attacking) {
                	if(spriteNum == 1) {image = attackRight1;}
                	if(spriteNum == 2) {image = attackRight2;}
                	}
                break;
        }
        
        if(invincible) {
        	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }
        
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        
        //Reset ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        
        
        //debug
        
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString(" Invincicle: " + invincibleCounter, 10, 400);
//        
        
    }
}
