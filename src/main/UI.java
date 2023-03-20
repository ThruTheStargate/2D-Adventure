package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import object.ObjCoinsBronze;
import object.ObjHeart;
import object.ObjManaCrystal;

public class UI {
	
	GamePanel gp;
	Graphics2D g2; 
	
	Font maruMonica, blackarrad, chiller;
	BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
	public boolean messageOn = false;
	public ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // state 0 is 1st screen 1 is second snd so on.	
	public int playerSlotCol = 0;
	public int playerSlotRow = 0; 	
	public int npcSlotCol = 0;
	public int npcSlotRow = 0; 
	int subState = 0;
	int counter = 0;
	public Entity npc;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/maruMonica.TTF");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/ITCEDSCR.TTF");
			blackarrad = Font.createFont(Font.TRUETYPE_FONT, is);
			is =getClass().getResourceAsStream("/font/CHILLER.TTF");
			chiller = Font.createFont(Font.TRUETYPE_FONT, is);
			
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Create Heart oject
		Entity heart = new ObjHeart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystlal = new ObjManaCrystal(gp);
		crystal_full = crystlal.image;
		crystal_blank = crystlal.image2;
		Entity bronzeCoin = new ObjCoinsBronze(gp);
		coin = bronzeCoin.down1;
		
	}	
	public void setGameFinished(boolean end) {
		gameFinished = end;
	}
	public void addMessage(String text) {

		message.add(text);
		messageCounter.add(0);
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		//g2.setFont(chiller);
		g2.setFont(maruMonica);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		//Title State
		if(gp.gameState==gp.titleState) {
			drawTitleScreen();
		}
				
		
		//PLAY STATE
		if(gp.gameState == gp.playState) {  //Do playState Stuff
			drawPlayerLife();
			drawMessage();
		}
		
		//PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		
		//Dialogue State
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		
		//Character State
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		
		//OPTION State
		if(gp.gameState == gp.optionsState) {
			drawOptionsScreen();
		}
		//Game Over State
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		//Transition State
		if(gp.gameState == gp.transitionState) {
			drawTransition();
		}
		//Trade State
		if(gp.gameState == gp.tradeState) {
			drawTradeScreen();
		}	

	}
	
	
	public void drawPlayerLife() {
		
		int x = gp.tileSize /2;
		int y = gp.tileSize /2;
		int i=0;
		//draw blank max life
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		//Draw life
		x = gp.tileSize /2;
		y = gp.tileSize /2;
		i=0;
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i< gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		i=0;
		//Draw Max Mana
		x = gp.tileSize /2-5;
		y = (int)(gp.tileSize*1.5);
		while(i < gp.player.maxMana) {
			g2.drawImage(crystal_blank, x, y, null);
			i++;
			x += 35;
		}
		
		//Draw Mana
		i=0;
		x = gp.tileSize /2-5;
		y = (int)(gp.tileSize*1.5);
		while(i < gp.player.mana) {
			g2.drawImage(crystal_full, x, y, null);
			i++;
			x += 35;
		}
		
		
		
		
	}
	public void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		for(int i = 0; i <  message.size(); i++) {
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+5);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if(messageCounter.get(i)> 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
				
			}
		}
		
	}
	public void drawTitleScreen() {
		if(titleScreenState == 0) {
		//title
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
		String text = "Madiso and the Spirit Stick";
		int x = getXforCenteredText(text); 
		int y = gp.tileSize*3;
		
		//shadow color
		g2.setColor(Color.gray);
		g2.drawString(text,x+5,y+5);
		
		//main color
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
		
		//player image
		x= gp.screenWidth/2 - gp.tileSize;
		y+= gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2,gp.tileSize*2,null );
		
		//menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,45F));
		
		
		
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y+=gp.tileSize*3;
		g2.drawString(text, x, y);
		
		if(commandNum == 0) {
			g2.drawString(">",x-gp.tileSize,y);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y+=gp.tileSize*1;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">",x-gp.tileSize,y);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y+=gp.tileSize*1;
		g2.drawString(text, x, y);
		
		if(commandNum == 2) {
			g2.drawString(">",x-gp.tileSize,y);
		}
		
		}
		else if(titleScreenState == 1) {
			// story or character 
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "Select your Class";
			int x= getXforCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x,y);
			
			text = "Fighter";
			x= getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x,y);
			if(commandNum==0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Thiet";
			x= getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x,y);
			if(commandNum==1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Sorcerer";
			x= getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x,y);
			if(commandNum==2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Back";
			x= getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x,y);
			if(commandNum==3) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
		}
		
	}
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x= getXforCenteredText(text);
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);
	}
	public void drawDialogueScreen() {
		
		// WINDOW 
		int x = gp.tileSize*3;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*6);
		int height = gp.tileSize*4;
		drawSubWindow(x,y,width,height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
		x+=gp.tileSize;
		y+=gp.tileSize;
		for(String line : currentDialogue.split("\n")) {
		g2.drawString(line, x, y);
		y +=40;
		}
		
	}
	public void drawCharacterScreen() {
		// create a frame
		final int frameX = gp.tileSize*2;
		final int frameY = (int)(gp.tileSize * 0.5);
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*11;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight );
		
		//TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight =  35;
		
		// Names
		g2.drawString("Level: ", textX,textY);
		textY += lineHeight;
		g2.drawString("Life: ", textX,textY);
		textY += lineHeight;
		g2.drawString("Mana: ", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength: ", textX,textY);
		textY += lineHeight;
		g2.drawString("Dexterity: ", textX,textY);
		textY += lineHeight;
		g2.drawString("Attack: ", textX,textY);
		textY += lineHeight;
		g2.drawString("Defense: ", textX,textY);
		textY += lineHeight;
		g2.drawString("Exp: ", textX,textY);
		textY += lineHeight;
		g2.drawString("Next Level: ", textX,textY);
		textY += lineHeight;
		g2.drawString("coin: ", textX,textY);
		textY += lineHeight + 15;
		g2.drawString("Weapon: ", textX,textY);
		textY += lineHeight + 10;
		g2.drawString("Sheild: ", textX,textY);
		textY += lineHeight + 10;
		g2.drawString("Magic: ", textX,textY);
		
		textY = frameY + gp.tileSize;
		int tailX = (frameX + frameWidth) - 30;
		String value;
		
		// Values
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX,textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX,textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX,textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX,textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX,textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX,textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX,textY);
		textY += lineHeight - 20 ;
		g2.drawImage(gp.player.currentWeapon.down1, tailX-gp.tileSize, textY-10, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX-gp.tileSize, textY-10, null);
		textY += gp.tileSize;
		if(gp.player.currentMagic != null ) {
			g2.drawImage(gp.player.currentMagic.down1, tailX-gp.tileSize, textY-10, null);	
		}
		
	}
	
	
	
	public void drawInventory(Entity entity, boolean cursor) {// int slotCol, int slotRow) {
		
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if (entity == gp.player) {
			frameX = gp.tileSize *12;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize *6;
			frameHeight = gp.tileSize *5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		} else {
			frameX = gp.tileSize *2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize *6;
			frameHeight = gp.tileSize *5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		//frame
			drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//slot 
		final int slotXStart = frameX+20;
		final int slotYStart = frameY+20;
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gp.tileSize +3;
		
		//Draw Players Inventory 
		for(int i=0; i < entity.inventory.size(); i++) {
			
			if(entity.inventory.get(i) == entity.currentWeapon ||
				entity.inventory.get(i) ==  entity.currentShield || entity.inventory.get(i) == entity.currentMagic || entity.inventory.get(i) == entity.currentLight) {
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			
			//Display Amount 
			
			if(entity == gp.player && entity.inventory.get(i).amount > 1) {
				g2.setFont(g2.getFont().deriveFont(32f));
				int amountX;
				int amountY;
				String s = "" + entity.inventory.get(i).amount;
				amountX = getXforAlignToRightText(s, slotX + 44);
				amountY = slotY + gp.tileSize;
				
				//shadow of number
				g2.setColor(new Color(60,60,60));
				g2.drawString(s, amountX, amountY);
				
				// draw Number
				
				g2.setColor(Color.white);
				g2.drawString(s, amountX-3, amountY-3);
			}
			slotX += slotSize;
			if((i + 1 )% 5 == 0) {
				slotY += slotSize;
				slotX = slotXStart;
			}
		}
		
		//Cursor 
		if(cursor == true) {
			int cursorX = slotXStart + (slotSize * slotCol) ;
			int cursorY = slotYStart + (slotSize * slotRow) ;
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			
			//draw cursor
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
			// Description Frame
			int dFrameX = frameX;;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize *3;
			
			
			
			//draw Description text;
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(28F));
			int itemIndex = getItemDescription(slotCol, slotRow);
			if(itemIndex < entity.inventory.size()) {
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				for(String line : entity.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line,textX,textY);
				textY += 32;
				}
			}
		}
		
	}
	
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		int x;
		int y;
		String text;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		text = "GAME OVER";
		//Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*5;
		g2.drawString(text,x,y );
		
		//Main
		g2.setColor(Color.white);
		g2.drawString(text,x-4,y-4 );
		
		
		// Retry
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y += gp.tileSize*3;
		g2.drawString(text,x,y );
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}
		
		//Back to the title screen
		text = "Quit";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text,x,y );
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
		
	}
	
	public void drawOptionsScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// sub window
		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: option_top(frameX, frameY); break;
		case 1: options_fullScreenNotification(frameX,frameY);break;
		case 2: options_control(frameX,frameY); break;
		case 3: options_endGameConfirmation(frameX,frameY); break;
		}
		
		gp.keyH.enterPressed = false;
	}
	
	public void option_top(int frameX, int frameY) {
		int textX;
		int textY;
		
		//title
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		
		// Full Screen ON/OFF
		textX = frameX + gp.tileSize;
		textY += gp.tileSize*2;
		g2.drawString("Full Screen", textX, textY );
		
		if(commandNum == 0 ) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.fullScreenOn == false){
					gp.fullScreenOn = true;
					//gp.setFullScreen();
				}
				else if(gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
					}
				  subState = 1;
				}
			
		}
		
		// Music 
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY );
		if(commandNum == 1 ) {
			g2.drawString(">", textX-25, textY);
		}
		
		//SE
		textY += gp.tileSize;
		g2.drawString("SE", textX, textY );
		if(commandNum == 2 ) {
			g2.drawString(">", textX-25, textY);
		}
		
		//Control
		textY += gp.tileSize;
		g2.drawString("Control", textX, textY );
		if(commandNum == 3 ) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		//End Game
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY );
		if(commandNum == 4 ) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		//Back
		textY += gp.tileSize*2;
		g2.drawString("Back", textX, textY );
		if(commandNum == 5 ) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		//FULL SCREEN CHECK BOX
		
		textX = (int) (frameX + gp.tileSize * 4.5);
		textY =  (frameY + gp.tileSize * 2 + 24);
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		
		if(gp.fullScreenOn) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		//Music Volume SLider
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24); //120/5 = 24
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		// Sound Effects
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.sound.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
							
		gp.config.saveConfig();
		
		
	}
	
	public void options_fullScreenNotification(int frameX, int frameY) {
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "The change will take \neffect after restarting \nthe game.";
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
			
		}
		
		//Back
		textY = frameY + gp.tileSize*9;
		g2.drawString("BacK", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed) {
				subState = 0;
			}
		}
		
		
	}
	
	public void options_control(int frameX, int frameY) {
		int textX;
		int textY;
		
		// Title  Font maruMonica, blackarrad, chiller;
		
		String text = "Control";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		//g2.setFont(maruMonica);
		g2.setFont(g2.getFont().deriveFont(20F));
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY); textY += gp.tileSize;
		g2.drawString("Attack", textX, textY); textY += gp.tileSize;
		g2.drawString("Confirm", textX, textY); textY += gp.tileSize;
		g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
		g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
		g2.drawString("Pause", textX, textY); textY += gp.tileSize;
		g2.drawString("Options", textX, textY); textY += gp.tileSize;
		
		textX = frameX + gp.tileSize*4;
		textY = frameY + gp.tileSize*2;
		
		
		
		g2.drawString("UP DOWN LEFT RIGHT", textX, textY);textY += gp.tileSize;
		g2.drawString("SPACE ", textX, textY);textY += gp.tileSize;
		g2.drawString("ENTER ", textX, textY);textY += gp.tileSize;
		g2.drawString("F", textX, textY);textY += gp.tileSize;
		g2.drawString("C", textX, textY);textY += gp.tileSize;
		g2.drawString("P", textX, textY);textY += gp.tileSize;
		g2.drawString("ESC", textX, textY);textY += gp.tileSize;
		
		//back
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*9;
		
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString("<", textX-25, textY);
			if(gp.keyH.enterPressed) {
				subState = 0;
				commandNum = 3;
			}
		}
		
	}
	
	public void options_endGameConfirmation(int frameX, int frameY) {
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 3;
		
		currentDialogue = "Quit the Game \nand return to the \ntitle Screen?";
		for (String line: currentDialogue.split("\n")) {
			g2.drawString(line,textX, textY);
			textY+=40;
		}
		
		//Yes
		
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed) {
				subState = 0;
				gp.gameState = gp.titleState;
			}
		}
		
		//no
		text = "No";
		textX = getXforCenteredText(text);
		textY += 40;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed) {
				subState = 0;
				commandNum = 4;
			}
		}
		
	}
	
	public void drawTransition() {
		counter++;
		g2.setColor(new Color(0,0,0,counter*5));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		if( counter >= 50) {
			counter = 0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.tileSize*gp.eHandler.tempCol;
			gp.player.worldY = gp.tileSize*gp.eHandler.tempRow;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
		}
	}
	
	public void drawTradeScreen() {
		
		switch(subState) {
		case 0: trade_select();break;
		case 1: trade_buy(); break;
		case 2: trade_sell(); break;
		}
		
		gp.keyH.enterPressed = false;
		
	}
	public void trade_select() {
		
		drawDialogueScreen();
		int x = gp.tileSize *15;
		int y = gp.tileSize*4;
		int width = gp.tileSize*3;
		int height = (int) (gp.tileSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		//draw text
		x+= gp.tileSize;
		y+= gp.tileSize;
		g2.drawString("Buy", x, y);
		
		if(commandNum == 0) {
			g2.drawString(">", x-25, y);
			if(gp.keyH.enterPressed) {
			subState = 1;
			}
		}
		y+= gp.tileSize;
		g2.drawString("Sell", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-25, y);
			if(gp.keyH.enterPressed) {
				subState = 2;
			}
		}
		y+= gp.tileSize;
		g2.drawString("Leave", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-25, y);
			if(gp.keyH.enterPressed) {
				commandNum = 0;
				gp.gameState = gp.dialogueState;
				currentDialogue = "Come again soon hehe!!";
			}
		}
		
	
		
	}
	public void trade_buy() {
		// draw player inventory
		drawInventory(gp.player,false);
		//npc inventory 
		drawInventory(npc, true);
		
		//Draw Hint Window  
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height );
		g2.drawString("[ESC] Back", x + 24, y + 60);
		
		//Draw Player coins
		x = gp.tileSize*12;
		y = gp.tileSize*9;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x,y,width,height );
		g2.drawString("Your Coin: " + gp.player.coin, x + 24, y + 60);
		
		//Draw Price SubWindow
		int itemIndex = getItemDescription(npcSlotCol,npcSlotRow);
		if(itemIndex < npc.inventory.size()) {
			x = (int)(gp.tileSize * 5.5);
			y = (int)(gp.tileSize * 5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x,y,width,height );
			g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
			int price = npc.inventory.get(itemIndex).price;
			String text = "" + price;
			x = getXforAlignToRightText(text, gp.tileSize * 8 - 20);
			g2.drawString(text, x, y + 34);
		}
		
		//Buy Item 
		if(gp.keyH.enterPressed) {
			if(npc.inventory.get(itemIndex).price > gp.player.coin) {
				subState = 0;
				gp.gameState = gp.dialogueState;
				currentDialogue = "You need more coin to buy that!";
				drawDialogueScreen();
				
			}
			else { 
				if (gp.player.canObtainItem(npc.inventory.get(itemIndex))) {
					gp.player.coin -= npc.inventory.get(itemIndex).price;
				}
				else {
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "You can not carry any more!";
				}
			}
//			else if(gp.player.inventory.size() == gp.player.maxInventorySize) {
//				subState = 0;
//				gp.gameState = gp.dialogueState;
//				currentDialogue = "You can not carry any more!";
//				drawDialogueScreen();
//			} else {
//				gp.player.coin -= npc.inventory.get(itemIndex).price;
//				gp.player.inventory.add(npc.inventory.get(itemIndex));
//			}
		}
		
		
	}
	public void trade_sell() {
		//Draw Player Inventory
		drawInventory(gp.player,true);
		
		int x;
		int y;
		int width;
		int height;
		//Draw Hint Window  
		x = gp.tileSize*2;
		y = gp.tileSize*9;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x,y,width,height );
		g2.drawString("[ESC] Back", x + 24, y + 60);
		
		//Draw Player coins
		x = gp.tileSize*12;
		y = gp.tileSize*9;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x,y,width,height );
		g2.drawString("Your Coin: " + gp.player.coin, x + 24, y + 60);
		
		//Draw Price SubWindow
		int itemIndex = getItemDescription(playerSlotCol,playerSlotRow);
		if(itemIndex < gp.player.inventory.size()) {
			x = (int)(gp.tileSize * 15.5);
			y = (int)(gp.tileSize * 5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x,y,width,height );
			g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
			
			int price = gp.player.inventory.get(itemIndex).price/2;
			String text = "" + price;
			x = getXforAlignToRightText(text, gp.tileSize * 18 - 20);
			g2.drawString(text, x, y + 34);
		
		
			//Sell Item 
			if(gp.keyH.enterPressed) {
				if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
						gp.player.inventory.get(itemIndex) == gp.player.currentShield || gp.player.inventory.get(itemIndex) == gp.player.currentMagic) {
					commandNum = 0; 
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "You cannot sell an equipped item!";
				} else {
					if(gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount --;
					}else { 
						gp.player.inventory.remove(itemIndex);
					}
					gp.player.coin += price;
				}
			}
		}
		
		
	}
	
	public int getItemDescription(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(255,255,255);
		g2.setStroke(new BasicStroke(5));
		g2.setColor(c);
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return gp.screenWidth/2 - length/2;
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return tailX - length;
	}
}
