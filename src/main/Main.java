//Search for Castle Keep
//Mr. Dumar's Class of 2022-2023
//January 18, 2023

package main; //file name


import javax.swing.JFrame; //the  java library that enables graphics



public class Main {
	public static JFrame window;
    public static void main(String[] args){
        window = new JFrame();                                 //create the jframe object
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ends program when the window is closed
        window.setResizable(false);                            //makes it so you can't resize the window
        window.setTitle("Search for Castle Keep");
        
       
        GamePanel gamePanel = new GamePanel(); //creating new Game Panel object
        window.add(gamePanel);                 
        
        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn) {
        	window.setUndecorated(true);
        }
        window.pack();
        
        window.setLocationRelativeTo(null);
        
        window.setVisible(true);
                
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    } 
}
