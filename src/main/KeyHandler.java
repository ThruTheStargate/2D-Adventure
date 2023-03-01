package main; //the folder

import java.awt.event.KeyEvent;   //classes we import
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean debug, axePlus, music = true, enterPressed, spacePressed, shotKeyPressed, aPressed;
    //Debug 
    boolean showDebugText = false;
    
    public KeyHandler(GamePanel gp) {
    	this.gp= gp;
    }
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
       
        //Title State
        if(gp.gameState == gp.titleState) {
        	titleState(code);
        }
        
        //playState
        else  if(gp.gameState == gp.playState) {
	        playState(code);
        }
        
        //Pause State 
        else if(gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        // Dialogue State
        else if(gp.gameState == gp.dialogueState) {
        	dialogueState(code);
        }
        //Character State 
        else if (gp.gameState == gp.characterState) {
        	characterState(code);
        } //Option State 
        else if (gp.gameState == gp.optionsState) {
        	optionsState(code);
        }  //Game Over State 
        else if (gp.gameState == gp.gameOverState) {
        	gameOverState(code);
        } // Trade State
        	else if (gp.gameState == gp.tradeState) {
        	tradeState(code);
        }
        
        
    }
        
     public void titleState(int code) {
     	if(gp.ui.titleScreenState == 0) {
	        	if (code == KeyEvent.VK_DOWN){
	        		gp.ui.commandNum++;
	        		if(gp.ui.commandNum > 2) {
	        			gp.ui.commandNum = 0;
	        		}	
	            }
	        	 if (code == KeyEvent.VK_UP){
	        		
	        		 gp.ui.commandNum--;
	         		if(gp.ui.commandNum < 0) {
	         			gp.ui.commandNum = 2;
	         		}

	             }
	        	 if(code == KeyEvent.VK_ENTER) {
	        		 if(gp.ui.commandNum == 0) {
	        			 gp.gameState = gp.playState;
	        			 gp.playMusic(0);
	        			 //to add characters screen uncomment the next line. and comment out the one above
	        			 //gp.ui.titleScreenState = 1;  
	        			 //System.out.println(gp.ui.titleScreenState);
	         		}
	        		 if(gp.ui.commandNum == 1) {
	        			 
	          			
	          		}
	        		 if(gp.ui.commandNum == 2) {
	        			 
	           			System.exit(0);
	           		}
	             }
	        }
     
     	else if(gp.ui.titleScreenState == 1) {
         	if (code == KeyEvent.VK_DOWN){
                
         		gp.ui.commandNum++;
         		if(gp.ui.commandNum > 3) {
         			gp.ui.commandNum = 0;

         		
         		}
         	}
         	 if (code == KeyEvent.VK_UP){
         		
         		 gp.ui.commandNum--;
          		if(gp.ui.commandNum < 0) {
          			gp.ui.commandNum = 3;
          		}

                  
              }
         	 if(code == KeyEvent.VK_ENTER) {
         		 if(gp.ui.commandNum == 0) {
          			System.out.println("Add code here  fighter");
          			gp.gameState = gp.playState;
          			//fighter 
          			if(music) {
          			gp.playMusic(0);
          			}
          		}
         		 if(gp.ui.commandNum == 1) {
         			 System.out.println("Add code here theif");
         			 gp.gameState = gp.playState;
           			//Theif
         			 if(music) {
           			gp.playMusic(0);
         			 }
           		}
         		 if(gp.ui.commandNum == 2) {
         			 
         			 System.out.println("Add code here sorcerer");
         			 gp.gameState = gp.playState;
           			//sorcerer
         			 if(music) {
           			gp.playMusic(0);
         			 }
            		}
         		 if(gp.ui.commandNum == 3) {            			 
             			gp.ui.titleScreenState = 0;
             			gp.ui.commandNum = 0;
             		}
         		 
              }
         	
     	
     	
     	}
     
     }
     public void pauseState(int code) {
    	 if(code == KeyEvent.VK_P) {
         	gp.gameState = gp.playState;
         }
     }
     public void playState(int code) {
    	 	if (code == KeyEvent.VK_UP){
	            upPressed = true;    
	        }
	        if (code == KeyEvent.VK_LEFT){
	            leftPressed = true;
	        }
	        if (code == KeyEvent.VK_DOWN){
	            downPressed = true;
	        }
	        if (code == KeyEvent.VK_RIGHT){
	            rightPressed = true;
	        }
			if (code == KeyEvent.VK_A){
				aPressed = true;
			}
//	        if(code == KeyEvent.VK_K) {
//	        	debug = true;
//	        }
	        if(code == KeyEvent.VK_A) {
	        	axePlus = true;
	        }
	        if(code == KeyEvent.VK_P) {
	        	gp.gameState = gp.pauseState;
	        }
	        if(code == KeyEvent.VK_C) {
	        	gp.gameState = gp.characterState; 
	        }
	        if(code == KeyEvent.VK_M) {
	        	if(music == true) {
	        		music = false;
	        		gp.stopMusic();
	        	}
	        	else if(music == false) {
	        		music = true;
	        		gp.playMusic(0);
	        	} 
	        }
	        if(code == KeyEvent.VK_ENTER) {
	        	enterPressed = true;
	        }
	        
	        if(code == KeyEvent.VK_SPACE) {
	        	spacePressed = true;
	        }
	        
	        if(code == KeyEvent.VK_F) {
	        	shotKeyPressed = true;
	        	
	        }
	        
	        if(code == KeyEvent.VK_ESCAPE) {
	        	gp.ui.commandNum = 0;
	        	
	        	gp.gameState = gp.optionsState;
	        	
	        }
	        
	         //DEBUG
	        if(code == KeyEvent.VK_T) {
	        	if(showDebugText == false) {
	        		showDebugText = true;
	        	}
	        	else if (showDebugText == true) {
	        		showDebugText = false;
	        	}
	        }
	        if(code == KeyEvent.VK_R) {  // i am cpding this the way he did but I may want to change it. 
	        	switch (gp.currentMap) {
	        	case 0: gp.tileM.loadMap("/map/worldV3.txt", 0); break;
	        	case 1: gp.tileM.loadMap("/map/interior01.txt", 1); break;
	        	}
	        	
	        }
     }
     public void dialogueState(int code) {
    	 if (code == KeyEvent.VK_ENTER) {
     		gp.gameState = gp.playState;
     	}
     }
     public void characterState(int code) {
    	 if(code == KeyEvent.VK_C) {
     		gp.gameState = gp.playState;
     	 }
    	 
        if(code == KeyEvent.VK_ENTER) {
        	gp.player.selectItem();
        }
        
        playerInventory(code);
    	 
     }
     
     public void playerInventory(int code) {
    	 if (code == KeyEvent.VK_UP){
     		if(gp.ui.playerSlotRow > 0) {
 		        gp.ui.playerSlotRow--;
 		        gp.playSE(9);	
     		}else {
     			gp.ui.playerSlotRow = 3;
     		}
         }
         if (code == KeyEvent.VK_LEFT){
         	if(gp.ui.playerSlotCol > 0) {
 	        	gp.ui.playerSlotCol--;
 	        	gp.playSE(9);
         	}else {
         		gp.ui.playerSlotCol  = 4;
         	}
         }
         if (code == KeyEvent.VK_DOWN){
         	if(gp.ui.playerSlotRow < 3) {
 	        	gp.ui.playerSlotRow++;
 	        	gp.playSE(9);
         	} else{
     			gp.ui.playerSlotRow = 0;
     		} 
         }
         if (code == KeyEvent.VK_RIGHT){
         	if(gp.ui.playerSlotCol < 4) {
 	        	gp.ui.playerSlotCol++;
 	        	gp.playSE(9);
         	} else {
         		gp.ui.playerSlotCol = 0;
         	}
         }
     }
     
     public void npcInventory(int code) {
    	 if (code == KeyEvent.VK_UP){
     		if(gp.ui.npcSlotRow > 0) {
 		        gp.ui.npcSlotRow--;
 		        gp.playSE(9);	
     		}else {
     			gp.ui.npcSlotRow = 3;
     		}
         }
         if (code == KeyEvent.VK_LEFT){
         	if(gp.ui.npcSlotCol > 0) {
 	        	gp.ui.npcSlotCol--;
 	        	gp.playSE(9);
         	}else {
         		gp.ui.npcSlotCol  = 4;
         	}
         }
         if (code == KeyEvent.VK_DOWN){
         	if(gp.ui.npcSlotRow < 3) {
 	        	gp.ui.npcSlotRow++;
 	        	gp.playSE(9);
         	} else{
     			gp.ui.npcSlotRow = 0;
     		} 
         }
         if (code == KeyEvent.VK_RIGHT){
         	if(gp.ui.npcSlotCol < 4) {
 	        	gp.ui.npcSlotCol++;
 	        	gp.playSE(9);
         	} else {
         		gp.ui.npcSlotCol = 0;
         	}
         }
     }
     public void optionsState(int code) {
        	if(code == KeyEvent.VK_ESCAPE) {
        		gp.ui.commandNum = 0;
        		gp.ui.subState = 0;
        		gp.gameState = gp.playState;
        	}
        	if(code == KeyEvent.VK_ENTER) {
        		enterPressed = true;
        	}
        	
        	int maxCommandNum = 0;
        	switch(gp.ui.subState) {
        	case 0: maxCommandNum = 5;break;
        	case 3: maxCommandNum = 1; break;
        	}
        	
        	if (code == KeyEvent.VK_DOWN){
                
         		gp.ui.commandNum++;
         		gp.playSE(9);
         		if(gp.ui.commandNum > maxCommandNum) {
         			gp.ui.commandNum = 0;

         		
         		}
         	}
         	 if (code == KeyEvent.VK_UP){
         		
         		 gp.ui.commandNum--;
         		 gp.playSE(9);
          		if(gp.ui.commandNum < 0) {
          			gp.ui.commandNum = maxCommandNum;
          		}

                  
              }
         	 
         	 if (code == KeyEvent.VK_LEFT) {
         		 if(gp.ui.subState == 0) {
         			 if(gp.ui.commandNum == 1  && gp.music.volumeScale > 0){
         				 gp.music.volumeScale--;
         				 gp.music.checkVolume();
         				 gp.playSE(9);
         			 }
         			if(gp.ui.commandNum == 2  && gp.sound.volumeScale > 0){
        				 gp.sound.volumeScale--;
        				 gp.playSE(9);
        				 
        			 }
         		 }
         	 }
         	if (code == KeyEvent.VK_RIGHT) {
         		if(gp.ui.subState == 0) {
         			if(gp.ui.commandNum == 1  && gp.music.volumeScale < 5){
        				 gp.music.volumeScale++;
        				 gp.music.checkVolume();
        				 gp.playSE(9);
        			 }
         			if(gp.ui.commandNum == 2  && gp.sound.volumeScale < 5){
       				 gp.sound.volumeScale++;
       				 gp.playSE(9);
       			 }
        		 }
        	 }
        }
     
     public void gameOverState(int code) {
    	 if(code == KeyEvent.VK_UP) {
    		 gp.ui.commandNum--;
    		 if(gp.ui.commandNum < 0 ) {
    			 gp.ui.commandNum = 1;
    		 }
    		 gp.playSE(9);
    	 }
    	 if(code == KeyEvent.VK_DOWN) {
    		 gp.ui.commandNum++;
    		 if(gp.ui.commandNum > 1 ) {
    			 gp.ui.commandNum = 0;
    		 }
    		 gp.playSE(9);
    	 }
    	 if(code == KeyEvent.VK_ENTER) {
    		 if(gp.ui.commandNum == 0) {
    			 gp.gameState = gp.playState;
    			 gp.retry();
    		 }else if(gp.ui.commandNum == 1){
    			 gp.gameState = gp.titleState;
    			 gp.restart();
    		 }
    	 }
    	 
    	 
     }
     
     public void tradeState(int code) {
    	
    	 if (code == KeyEvent.VK_ENTER) {
    		 enterPressed = true;
    	 }
    	 
    	 if (gp.ui.subState == 0) {
	    	 if(code == KeyEvent.VK_UP) {
	    		 gp.ui.commandNum--;
	    		 if(gp.ui.commandNum < 0 ) {
	    			 gp.ui.commandNum = 2;
	    		 }
	    		 gp.playSE(9);
	    	 }
	    	 if(code == KeyEvent.VK_DOWN) {
	    		 gp.ui.commandNum++;
	    		 if(gp.ui.commandNum > 2 ) {
	    			 gp.ui.commandNum = 0;
	    		 }
	    		 gp.playSE(9);
	    	 }
    	 }
    	 
    	 if(gp.ui.subState == 1) {
    		 npcInventory(code);
    		 if(code == KeyEvent.VK_ESCAPE) {
    			 gp.ui.subState = 0;
    		 }
    	 }
    	 if(gp.ui.subState == 2) {
    		 playerInventory(code);
    		 if(code == KeyEvent.VK_ESCAPE) {
    			 gp.ui.subState = 0;
    		 }
    	 }
    	
     }
    
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
          if (code == KeyEvent.VK_UP){
            upPressed = false;
            
        }
        if (code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
		if (code == KeyEvent.VK_A){
			aPressed = false;
		}
        if(code == KeyEvent.VK_K) {
        	debug = false;
        }
        if(code == KeyEvent.VK_A) {
        	axePlus = false;
        }
        if(code == KeyEvent.VK_F) {
        	shotKeyPressed = false;
        	
        	
        }
        
    }
}
