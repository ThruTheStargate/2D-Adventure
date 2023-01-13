package main;
 
import javax.swing.JFrame;

public class Main {
	public static JFrame window;
    public static void main(String[] args){
       // Player canvas = new Player();

       //Configures JFrame window
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        
       //Adds GamePanel to window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        //In fullscreen mode, hide title bar
        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn) {
        	window.setUndecorated(true);
        }

        //Sizes and centers window
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        //Sets up and runs game
        gamePanel.setUpGame();
        gamePanel.startGameThread();
        
        //this is a test comment
    } 
}
