package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	GamePanel gp;
	
	public Config(GamePanel gp) {
		this.gp = gp;
	}
	
	public void saveConfig() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
			if(gp.fullScreenOn) {
				bw.write("On");
			}
			if(!gp.fullScreenOn) {
				bw.write("Off");
			}
			
			bw.newLine();
			
			//Music Volume
			bw.write(String.valueOf(gp.music.volumeScale));
			bw.newLine();
			
			//SE volume
			bw.write(String.valueOf(gp.sound.volumeScale));
			bw.newLine();
			
			
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadConfig() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
			String s = br.readLine();
			
			//Full Screen
			if(s.equals("On")) {
				gp.fullScreenOn =true;
			} 
			if(s.equals("Off")) {
				gp.fullScreenOn = false;
			}
			
			//Music Vol
			
			s = br.readLine();
			gp.music.volumeScale = Integer.parseInt(s);
			
			//SE vol
			s = br.readLine();
			gp.sound.volumeScale = Integer.parseInt(s);
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
