package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
	
	Image playButton;
	Image loadButton;
	Image exitButton;
	
	public Menu(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		playButton = new Image("res/PlayButton.png");
		exitButton = new Image("res/ExitButton.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		playButton.draw(50, 50);
		exitButton.draw(50, 117);
		gc.setShowFPS(false);
		gc.setVSync(true);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		
		//play button
		if((xpos > 50 && xpos < 139) && (ypos > 250 && ypos < 310)){
			if(input.isMouseButtonDown(0)){
				sbg.enterState(1);
			}
		}
		
		//exit button
		if((xpos > 50 && xpos < 127) && (ypos > 145 && ypos < 182 )){
			if(input.isMouseButtonDown(0)){
				System.exit(0);
			}
		}		
	}
	
	public int getID(){	
		return 0;
	}
	
}