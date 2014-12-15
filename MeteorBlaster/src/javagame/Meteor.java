package javagame;

public class Meteor {

	private float metPosY;
	private float metPosX;
	
	public Meteor(float posY, float posX){
		metPosY = posY;
		metPosX = posX;
	}
		
	public void setMetPositionY(float posY){
		metPosY = posY;
	}
	
	public float getMetPositionY(){
		return metPosY;
	}
	
	public float getMetPositionX(){
		return metPosX;
	}
	public void moveMeteorite(float shift){
		metPosX -= shift;
	}
}