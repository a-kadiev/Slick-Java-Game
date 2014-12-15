package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.awt.geom.*;
import java.util.*;

public class Play extends BasicGameState{
	
	Image meteorite;
	Image worldMap;
	Image ship;
	Image beam;
	boolean quit = false;
	float shipPositionX = 0;
	float shipPositionY = 166;
	float midShip = shipPositionY - 14;
	public List<Shoot> shots = new ArrayList<Shoot>();
	public List<Meteor> meteors = new ArrayList<Meteor>();
	public ListIterator<Shoot> shots1 = shots.listIterator();
	float metPosY1 = 200;
	float metPosY2, metPosY3, metPosY4;
	float metPosX1 = 200; 
	float metPosX2, metPosX3, metPosX4;
	Color lineColor = new Color(0,0,139) /*Color.blue*/;
	int score = 0;
	int lives = 3;
	Random randomGenerator = new Random();
	
	
	public Play(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		worldMap = new Image("res/background.jpg");
		meteorite = new Image("res/meteorite3.png");
		ship = new Image("res/ship.png");	
		beam = new Image("res/beam.png");		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{	
		worldMap.draw(0,0);
		g.drawGradientLine(0, 20, lineColor, 640, 20, lineColor);
		g.drawString("Score: " + Integer.toString(score), 5, 0);
		g.drawString("Lives: " + Integer.toString(lives), 120, 0);
		ship.draw(shipPositionX, shipPositionY);
		
		for(Shoot element : shots){			
			beam.draw(element.getBeamPositionX(), element.getBeamPositionY());	
		}
		
		for(Meteor met : meteors){
			meteorite.draw(met.getMetPositionX(), met.getMetPositionY());
		}		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_DOWN)){
			shipPositionY += delta * .2f;			
			if(shipPositionY > 330){
				shipPositionY = 330;
			}
		}
		
		if(input.isKeyDown(Input.KEY_UP)){
			shipPositionY -= delta * .2f;			
			if(shipPositionY < 20){
				shipPositionY = 20;
			}
		}
		
		if(input.isKeyPressed(Input.KEY_F)){
			Shoot newShoot = new Shoot(shipPositionY, shipPositionX + 50);
			shots.add(newShoot);	
		}		
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			gc.exit();
		}
		
		for(Shoot element : shots){
			beam.draw(element.getBeamPositionX(), element.getBeamPositionY());
			element.moveShot(delta * .1f);
			
		}			
		
		for(Meteor element : meteors){
			meteorite.draw(element.getMetPositionX(), element.getMetPositionY());
			element.moveMeteorite(delta * .05f);
		}
		
		checkOutOfBoundary();
		checkCollision();
		createMeteroite();
		
		if(lives <= 0){
			gc.exit();
		}
	}
	
	public void generateMeteorite(){
		Meteor newMeteor = new Meteor(metPosY1, metPosX1);
		meteors.add(newMeteor);
	}
	
	public void checkOutOfBoundary(){
		//shoot pout of boundary
		float tmpPosSh;
		ListIterator<Shoot> shootItr = shots.listIterator();
		
		while(shootItr.hasNext()){			
			tmpPosSh = shootItr.next().getBeamPositionX();
			
			if(tmpPosSh > 640){
				shootItr.remove();
			}
		}
		
		//meteor  out of boundary
		float tmpPosMet;
		ListIterator<Meteor> metItr = meteors.listIterator();
		
		while(metItr.hasNext()){
			tmpPosMet = metItr.next().getMetPositionX();
			
			if(tmpPosMet < 20){
				metItr.remove();
				lives -= 1;
			}
		}
	}
	
	public void checkCollision(){
		//shot & meteor collision
		
		Rectangle2D rectMet;
		Rectangle2D rectSh;	
		Rectangle2D rectShip;
		
		for(Iterator<Shoot> shootItr = shots.listIterator(); shootItr.hasNext();){
			
			Shoot TMPsh = shootItr.next();
			rectSh = new Rectangle2D.Float(TMPsh.getBeamPositionX(), TMPsh.getBeamPositionY(), 25, 5);
			
			for(Iterator<Meteor> metItr = meteors.listIterator(); metItr.hasNext();){
				
				Meteor TMPmet = metItr.next();
				rectMet = new Rectangle2D.Float(TMPmet.getMetPositionX(), TMPmet.getMetPositionY(), 30, 30);
				 
				if(rectSh.intersects(rectMet)){
					
					metItr.remove();
					shootItr.remove();
					score += 1;
					break;
				}					
			}
		}
		
		//meteor & ship
		for(Iterator<Meteor> metItr = meteors.listIterator(); metItr.hasNext();){
			
			Meteor TMPmet = metItr.next();
			rectMet = new Rectangle2D.Float(TMPmet.getMetPositionX(), TMPmet.getMetPositionY(), 30, 30);			
			rectShip = new Rectangle2D.Float(shipPositionX, shipPositionY, 50, 28);
			
			if(rectMet.intersects(rectShip)){
				metItr.remove();
				lives -= 1;
				break;
			}
		}
	}
	
	public void createMeteroite(){
		
		if(meteors.size() <= 10){
			
			float randPosX = randomGenerator.nextInt((330 - 20) +1 ) + 20;
			
			Meteor newMeteor = new Meteor(randPosX, 640);
			meteors.add(newMeteor);
		}		
	}
	
	public int getID(){	
		return 1;
	}
	
}