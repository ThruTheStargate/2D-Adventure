package main; //the folder

//the many imported classes
//foreign classes
import java.awt.Color;
import java.awt.Dimension; 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
//our classes
import Environment.EnvironmentManager;
import ai.PathFinder;
import entity.Entity;
import entity.Player;
import tile.TileManager;
import tileInteractive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable{
  //the million instance variables
  //screen settings
  final int originalTileSize = 16;                        // 16x16 tile
  final int scale = 3;
  public final int tileSize = originalTileSize * scale;   // 48x48 tile
  public final int maxScreenCol = 20;
  public final int maxScreenRow = 12; 
  public final int screenWidth = tileSize * maxScreenCol; //960 pixels
  public final int screenHeight = tileSize * maxScreenRow;//576 pixels
  //world settings
  public final int maxWorldCol = 51;
  public final int maxWorldRow = 64;
  public final int maxMap = 10;     // the number of maps we can create
  public int currentMap = 0;        // the current map we are on. 
  int screenWidth2 = screenWidth;      // for full screen (
  int screenHeight2 = screenHeight;
  BufferedImage tempScreen;
  Graphics2D g2;
  public boolean fullScreenOn = false; // )
  //FPS
  //FrameRate()
  int FPS = 60;
  //System
  public TileManager tileM = new TileManager(this);
  public KeyHandler keyH = new KeyHandler(this);
  Sound sound = new Sound();
  Sound music = new Sound();
  public CollisionCheck cCheck = new CollisionCheck(this);
  public AssetSetter aSetter = new AssetSetter(this);
  public UI ui = new UI(this);
  public EventHandler eHandler = new EventHandler(this);
  Config config = new Config(this);
  public PathFinder pFinder = new PathFinder(this);
  EnvironmentManager eManager = new EnvironmentManager(this);
  Thread gameThread;
  //entity and object
  public Player player = new Player(this, keyH) ;    //Creates player from player class
  public Entity obj[][] = new Entity[maxMap][20];
  public Entity npc[][] = new Entity[maxMap][10];
  public Entity monster[][] = new Entity[maxMap][20];
  public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
  public Entity[][] projectile = new Entity[maxMap][20];
  public ArrayList<Entity> particleList = new ArrayList<>();
  ArrayList<Entity> entityList = new ArrayList<>();
  //game state 
  public int gameState;
  public final int titleState = 0;
  public final int playState = 1;
  public final int pauseState =2;
  public final int dialogueState = 3;
  public final int characterState = 4;
  public final int optionsState = 5;
  public final int gameOverState = 6;
  public final int transitionState = 7;
  public final int tradeState = 8;
  
  
  public GamePanel(){
      this.setPreferredSize(new Dimension(screenWidth, screenHeight));
      this.setBackground(Color.black);
      this.setDoubleBuffered(true);
      this.addKeyListener(keyH);
      this.setFocusable(true);
      
  }
  
  public void setUpGame() {
	  aSetter.setObject();
	  aSetter.setNPC();
	  aSetter.setMonster();
	  aSetter.setInteractiveTiles();
	  
	  eManager.setup();
	 
	  gameState = titleState; 
	  
	  //For different screen resize
	  tempScreen = new BufferedImage(screenWidth,screenHeight, BufferedImage.TYPE_INT_ARGB);
	  g2 = (Graphics2D)tempScreen.getGraphics();
	 if(fullScreenOn) {
	  setFullScreen();
	 }
	  
  }
  
  public void retry() {
	  
	  player.setDefualtPositions();
	  player.restoreLifeAndMana();
	  aSetter.setNPC();
	  aSetter.setMonster();
  }
  public void restart() {
	  player.setDefaultValues();
	  player.setItems();
	  aSetter.setObject();
	  aSetter.setNPC();
	  aSetter.setMonster();
	  aSetter.setInteractiveTiles();
	  
  }
  
  public void setFullScreen() {
	  // get the local screen device
	  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	  GraphicsDevice gd = ge.getDefaultScreenDevice();
	  gd.setFullScreenWindow(Main.window);
	  
	  // Get Full Screen Width and Height
	  screenWidth2 = Main.window.getWidth();
	  screenHeight2 = Main.window.getHeight();
	  
  }
  
  public void startGameThread(){
      gameThread = new Thread(this);
      gameThread.start();
  }
  
 
  
  public void run(){
      double drawInterval = 1000000000/FPS;
      double delta = 0;
      long lastTime = System.nanoTime();
      long currentTime;
      
      while(gameThread != null){
          currentTime = System.nanoTime();
          delta += (currentTime -lastTime) / drawInterval;
           
          lastTime = currentTime;
          if (delta >= 1 ){
           
          update();
          //repaint();
          drawToTempScreen(); //draws to temp screen
          drawToScreen(); // draws to Screen
          delta--;
          }
      }
      
      
  }
  
  public void update(){

	  if(gameState == playState) {
      player.update(); // updates the player
       for(int i = 0; i < npc[1].length; i++) { // NPC update
    	   if(npc[currentMap][i] != null) {
    		   npc[currentMap][i].update();
    	   }
       }
       
       
       for(int i = 0; i < monster[1].length; i++) { // NPC update
    	   if(monster[currentMap][i] != null) {
    		   if(monster[currentMap][i].alive && !monster[currentMap][i].dying) {
    		   monster[currentMap][i].update();
    		   }
    		   if(!monster[currentMap][i].alive) {
    			   monster[currentMap][i].checkDrop();
        		   monster[currentMap][i] = null;
        		   }
    	   }
       }

       
       for(int i = 0; i < projectile[1].length; i++) { // projecttile update
    	   if(projectile[currentMap][i] != null) {
    		   if(projectile[currentMap][i].alive) {
    			   projectile[currentMap][i].update();
    		   }
    		   if(!projectile[currentMap][i].alive) {
    			   projectile[currentMap][i] = null;
        		   }
    	   }
       }
       
       for(int i = 0; i < particleList.size(); i++) { // Particle update
    	   if(particleList.get(i) != null) {
    		   if(particleList.get(i).alive) {
    			   particleList.get(i).update();
    		   }
    		   if(!particleList.get(i).alive) {
    			   particleList.remove(i);
        		   }
    	   }
    	   
       }
       eManager.update();
       for(int i = 0; i < iTile[1].length; i++) {
    	   if(iTile[currentMap][i] != null) {
    		   iTile[currentMap][i].update();
    	   }
       }
       
	  }
	  if(gameState == pauseState) {
		  //nothing
		 
	  }
      
	 
	
  }
  
  public void drawToTempScreen() {
	  //DEbug
      long drawStart = 0;
      if(keyH.showDebugText == true) {    	  
    	  drawStart = System.nanoTime();
      }
      
      
      //Title Screen
      if(gameState == titleState) {
    	  ui.draw(g2);
      }
      else {
      //tile
      tileM.draw(g2);
      
      //interactive Tiles
      for(int i = 0; i < iTile[1].length;i++) {
    	  if(iTile[currentMap][i] != null) {
    		  iTile[currentMap][i].draw(g2);
    	  }
      }
      //player
     //player.draw(g2);
      // Add entities to List 
      //***********************
      
      entityList.add(player);
      for(int i = 0; i < npc[1].length; i++ ) {
    	  if(npc[currentMap][i] != null) {
    		  entityList.add(npc[currentMap][i]);
    	  }
      }
      
      for( int i = 0; i < obj[1].length; i++ ) {
    	  if(obj[currentMap][i] != null) {
    		  entityList.add(obj[currentMap][i]);
    	  }
      }
      
      for( int i = 0; i < monster[1].length; i++ ) {
    	  if(monster[currentMap][i] != null) {
    		  entityList.add(monster[currentMap][i]);
    	  }
      }
      
    // /*   ************************************************************************************ 
      
      
      for( int i = 0; i < projectile[1].length; i++ ) {
    	  if(projectile[currentMap][i] != null) {
    		  entityList.add(projectile[currentMap][i]);
    	  }
      }
      
      
     // ******************************************************************************************
 //     */
      
      
      for( int i = 0; i < particleList.size(); i++ ) {
    	  if(particleList.get(i) != null) {
    		  entityList.add(particleList.get(i));
    	  }
      }
      
      //***********************
      
      //SORT 
      Collections.sort(entityList, new Comparator<Entity>() {
      
      @Override
      public int compare(Entity e1, Entity e2) {
    	  int result = Integer.compare(e1.worldY, e2.worldY);
    	  return result;
      }
      });
      
      //Draw Entities 
      for( int i = 0; i < entityList.size() ; i++ ) {
    	  entityList.get(i).draw(g2);
      }
      // Empty List      
      entityList.clear();
      
      //Environment When we are in the dungeon we need to use the following.
      eManager.draw(g2);
      
      
      //UI
     ui.draw(g2);
     
      }
      
     //Debug
     if(keyH.showDebugText == true) {
     long drawEnd = System.nanoTime();
     long passed = drawEnd - drawStart;
     g2.setFont(new Font("Arial",Font.PLAIN,20));
     g2.setColor(Color.white);
     int x = 10;
     int y = 150;
     int lineHeight = 20 ;
     g2.drawString("WorldX: " + player.worldX, x, y);  y += lineHeight;
     g2.drawString("WorldY: " + player.worldY, x, y);  y += lineHeight;
     g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
     g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y);
     y += lineHeight;
     g2.drawString("Draw Time: " + passed, 10,400);
     }
     
  }
  
  public void drawToScreen() {
	  Graphics g = getGraphics();
	  g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
	  g.dispose();
  }
  
  
 /* 
  * ***********************************
  
  public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      Graphics2D g2 = (Graphics2D)g;
    
      //DEbug
      long drawStart = 0;
      if(keyH.showDebugText == true) {    	  
    	  drawStart = System.nanoTime();
      }
      
      
      //Title Screen
      if(gameState == titleState) {
    	  ui.draw(g2);
      }
      else {
      //tile
      tileM.draw(g2);
      
      //interactive Tiles
      for(int i = 0; i < iTile.length;i++) {
    	  if(iTile[i] != null) {
    		  iTile[i].draw(g2);
    	  }
      }
      //player
     //player.draw(g2);
      // Add entities to List 
      //***********************
      
      entityList.add(player);
      for(int i = 0; i < npc.length; i++ ) {
    	  if(npc[i] != null) {
    		  entityList.add(npc[i]);
    	  }
      }
      
      for( int i = 0; i < obj.length; i++ ) {
    	  if(obj[i] != null) {
    		  entityList.add(obj[i]);
    	  }
      }
      
      for( int i = 0; i < monster.length; i++ ) {
    	  if(monster[i] != null) {
    		  entityList.add(monster[i]);
    	  }
      }
      
      for( int i = 0; i < projectileList.size(); i++ ) {
    	  if(projectileList.get(i) != null) {
    		  entityList.add(projectileList.get(i));
    	  }
      }
      
      
      
      for( int i = 0; i < particleList.size(); i++ ) {
    	  if(particleList.get(i) != null) {
    		  entityList.add(particleList.get(i));
    	  }
      }
      
      //***********************
      
      //SORT 
      Collections.sort(entityList, new Comparator<Entity>() {
      
      @Override
      public int compare(Entity e1, Entity e2) {
    	  int result = Integer.compare(e1.worldY, e2.worldY);
    	  return result;
      }
      });
      
      //Draw Enttities 
      for( int i = 0; i < entityList.size() ; i++ ) {
    	  entityList.get(i).draw(g2);
      }
      // Empty List      
      entityList.clear();
      
      
      
      //UI
     ui.draw(g2);
     
      }
      
     //Debug
     if(keyH.showDebugText == true) {
     long drawEnd = System.nanoTime();
     long passed = drawEnd - drawStart;
     g2.setFont(new Font("Arial",Font.PLAIN,20));
     g2.setColor(Color.white);
     int x = 10;
     int y = 100;
     int lineHeight = 20 ;
     g2.drawString("WorldX: " + player.worldX, x, y);  y += lineHeight;
     g2.drawString("WorldY: " + player.worldY, x, y);  y += lineHeight;
     g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
     g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y);
     y += lineHeight;
     g2.drawString("Draw Time: " + passed, 10,400);
     //System.out.println("Draw Time: " + passed);
     }
     
      
     g2.dispose();
      
  }
  ***************************************************
  */
  
  public void playMusic(int i) {
	  music.setFiles(i);
	  music.play();
	  music.loop();
  }
  
  public void stopMusic() {
	  music.stop();
  }
  
  public void playSE(int i) {
	  
	  sound.setFiles(i);
	  sound.play();
  }
  
}
